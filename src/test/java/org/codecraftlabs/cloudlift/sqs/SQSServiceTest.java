package org.codecraftlabs.cloudlift.sqs;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
public class SQSServiceTest {
    private static final String SQS_QUEUE_URL = "https://sqs.us-east-1.amazonaws.com/XXXXXXXXXXXX/sqs-id";
    @Test
    void sendMessageOk() throws SQSException {
        SQSService sqsService = SQSService.builder().build();

        long timeStamp = System.currentTimeMillis();
        String message = "{'name' : 'unit-test', 'timestamp' : " + timeStamp + "}";

        String messageId = sqsService.sendMessage(SQS_QUEUE_URL, message);
        assertThat(messageId).isNotNull().isNotBlank();
    }
}
