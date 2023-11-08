package org.codecraftlabs.cloudlift.s3;

public class InvalidPutRequestException extends RuntimeException {
    public InvalidPutRequestException(String message) {
        super(message);
    }
}
