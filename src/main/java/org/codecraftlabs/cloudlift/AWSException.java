package org.codecraftlabs.cloudlift;

public class AWSException extends RuntimeException {
    public AWSException(String message, Throwable exception) {
        super(message, exception);
    }
}
