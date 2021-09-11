package org.codecraftlabs.cloud.s3;

import org.codecraftlabs.cloud.AWSException;

public class InvalidPutRequestException extends AWSException {
    public InvalidPutRequestException(String message) {
        super(message);
    }

    public InvalidPutRequestException(String message, Throwable exception) {
        super(message, exception);
    }
}
