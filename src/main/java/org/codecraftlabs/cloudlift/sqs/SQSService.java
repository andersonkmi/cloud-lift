package org.codecraftlabs.cloudlift.sqs;

import javax.annotation.Nonnull;

public interface SQSService {
    @Nonnull
    String sendMessage(@Nonnull String url, @Nonnull String contents) throws SQSException;

    static SQSServiceBuilder builder() {
        return new DefaultSQSServiceBuilder();
    }
}
