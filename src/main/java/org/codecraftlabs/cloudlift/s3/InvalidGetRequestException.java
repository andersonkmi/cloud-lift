package org.codecraftlabs.cloudlift.s3;

public class InvalidGetRequestException extends RuntimeException {
    public InvalidGetRequestException(String message) {
        super(message);
    }
}
