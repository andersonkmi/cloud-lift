package org.codecraftlabs.cloudlift.sqs;

public record SQSMessage(String id, String receiptHandle, String body) {
}
