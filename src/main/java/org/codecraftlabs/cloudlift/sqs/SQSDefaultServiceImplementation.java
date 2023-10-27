package org.codecraftlabs.cloudlift.sqs;

import org.codecraftlabs.cloudlift.data.AWSRegion;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;
import software.amazon.awssdk.services.sqs.model.SqsException;

import javax.annotation.Nonnull;

final class SQSDefaultServiceImplementation implements SQSService {
    private final SqsClient sqsClient;

    SQSDefaultServiceImplementation() {
        sqsClient = SqsClient.builder().build();
    }

    SQSDefaultServiceImplementation(@Nonnull AWSRegion awsRegion) {
        sqsClient = SqsClient.builder().region(awsRegion.region()).build();
    }

    @Override
    @Nonnull
    public String sendMessage(@Nonnull String url, @Nonnull String contents) throws SQSException {
        try {
            var request = SendMessageRequest.builder()
                    .queueUrl(url)
                    .messageBody(contents)
                    .build();
            var response = sqsClient.sendMessage(request);
            return response.messageId();
        } catch (SqsException | UnsupportedOperationException exception) {
            throw new SQSException("Error when sending a message to SQS", exception);
        }
    }
}
