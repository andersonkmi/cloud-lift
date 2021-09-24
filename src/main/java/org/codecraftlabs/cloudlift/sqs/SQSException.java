package org.codecraftlabs.cloudlift.sqs;

public class SQSException extends Exception {
    public SQSException(String message) {
        super(message);
    }

    public SQSException(String message, Throwable exception) {
        super(message, exception);
    }
}
