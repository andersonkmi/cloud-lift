package org.codecraftlabs.cloudlift.sqs;

import org.codecraftlabs.cloudlift.data.AWSRegion;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageBatchRequest;
import software.amazon.awssdk.services.sqs.model.DeleteMessageBatchRequestEntry;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SendMessageResponse;
import software.amazon.awssdk.services.sqs.model.SqsException;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

final class SQSDefaultServiceImplementation implements SQSService {
    private static final int DEFAULT_WAIT_TIME_SECONDS = 20;
    private final SqsClient sqsClient;

    SQSDefaultServiceImplementation() {
        sqsClient = SqsClient.builder().build();
    }

    SQSDefaultServiceImplementation(@Nonnull AWSRegion awsRegion) {
        sqsClient = SqsClient.builder().region(awsRegion.region()).build();
    }

    @Override
    @Nonnull
    public String sendMessage(@Nonnull String url, @Nonnull String contents) {
        try {
            SendMessageRequest request = SendMessageRequest.builder()
                    .queueUrl(url)
                    .messageBody(contents)
                    .build();
            SendMessageResponse response = sqsClient.sendMessage(request);
            return response.messageId();
        } catch (SqsException exception) {
            throw new SQSException("Error when sending a message to SQS", exception);
        }
    }

    @Nonnull
    @Override
    public Optional<Set<SQSMessage>> receiveMessages(@Nonnull String url, int waitingTimeInSeconds) {
        try {
            ReceiveMessageRequest request = ReceiveMessageRequest.builder().queueUrl(url).waitTimeSeconds(waitingTimeInSeconds).build();
            ReceiveMessageResponse response = sqsClient.receiveMessage(request);
            if (! response.hasMessages()) {
                return Optional.empty();
            }
            Set<SQSMessage> sqsMessages = response.messages()
                    .stream()
                    .map(SQSDefaultServiceImplementation::convert)
                    .collect(Collectors.toSet());

            return Optional.of(sqsMessages);
        } catch (SqsException exception) {
            throw new SQSException("Error when retrieving messages from SQS", exception);
        }
    }

    @Nonnull
    @Override
    public Optional<Set<SQSMessage>> receiveMessages(@Nonnull String url) {
        return receiveMessages(url, DEFAULT_WAIT_TIME_SECONDS);
    }

    @Override
    public void deleteMessages(@Nonnull String url, @Nonnull Set<SQSMessage> messages) {
        try {
            DeleteMessageBatchRequest request = createDeleteMessageBatchRequest(url, messages);
            sqsClient.deleteMessageBatch(request);
        } catch (SqsException exception) {
            throw new SQSException("Error when deleting messages from SQS", exception);
        }
    }

    @Nonnull
    private DeleteMessageBatchRequest createDeleteMessageBatchRequest(@Nonnull String url, @Nonnull Set<SQSMessage> messages) {
        List<DeleteMessageBatchRequestEntry> deleteMessageBatchRequestEntries = new ArrayList<>();
        for(SQSMessage message : messages) {
            DeleteMessageBatchRequestEntry deleteMessageBatchRequestEntry = DeleteMessageBatchRequestEntry.builder()
                    .id(message.id())
                    .receiptHandle(message.receiptHandle())
                    .build();
            deleteMessageBatchRequestEntries.add(deleteMessageBatchRequestEntry);
        }

        return DeleteMessageBatchRequest.builder().queueUrl(url).entries(deleteMessageBatchRequestEntries).build();
    }

    @Nonnull
    private static SQSMessage convert(@Nonnull Message message) {
        return new SQSMessage(message.messageId(), message.receiptHandle(), message.body());
    }
}
