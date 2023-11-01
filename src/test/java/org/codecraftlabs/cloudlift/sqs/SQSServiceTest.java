package org.codecraftlabs.cloudlift.sqs;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SQSServiceTest {
    private static final String SQS_QUEUE_URL = "https://sqs.us-east-1.amazonaws.com/XXX/YYY";
    private static SQSMessage message;

    @Test
    @Order(10)
    void sendMessageOk() throws SQSException {
        SQSService sqsService = SQSService.builder().build();

        long timeStamp = System.currentTimeMillis();
        String messageContents = "{'name' : 'unit-test', 'timestamp' : " + timeStamp + "}";

        String messageId = sqsService.sendMessage(SQS_QUEUE_URL, messageContents);
        assertThat(messageId).isNotNull().isNotBlank();
    }

    @Test
    @Order(20)
    void receiveMessageOk() throws SQSException {
        SQSService sqsService = SQSService.builder().build();

        Optional<Set<SQSMessage>> messages = sqsService.receiveMessages(SQS_QUEUE_URL, 20);
        assertThat(messages).isPresent();
        message = messages.get().iterator().next();
        assertThat(message.body()).isNotNull().isNotBlank();
    }

    @Test
    @Order(30)
    void deleteMessagesOk() throws SQSException {
        SQSService sqsService = SQSService.builder().build();
        sqsService.deleteMessages(SQS_QUEUE_URL, Set.of(message));
    }
}
