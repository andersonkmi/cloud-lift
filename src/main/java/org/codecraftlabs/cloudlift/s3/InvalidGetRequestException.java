package org.codecraftlabs.cloudlift.s3;

public class InvalidGetRequestException extends Exception {
    public InvalidGetRequestException(String message) {
        super(message);
    }

    public InvalidGetRequestException(String message, Throwable exception) {
        super(message, exception);
    }
}
