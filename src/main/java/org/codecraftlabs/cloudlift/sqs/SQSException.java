package org.codecraftlabs.cloudlift.sqs;

public class SQSException extends RuntimeException {
    public SQSException(String message, Throwable exception) {
        super(message, exception);
    }
}
