package org.codecraftlabs.cloudlift.sqs;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.Set;

public interface SQSService {
    @Nonnull
    String sendMessage(@Nonnull String url, @Nonnull String contents) throws SQSException;

    @Nonnull
    Optional<Set<SQSMessage>> receiveMessages(@Nonnull String url, int waitingTimeInSeconds) throws SQSException;

    @Nonnull
    Optional<Set<SQSMessage>> receiveMessages(@Nonnull String url) throws SQSException;

    void deleteMessages(@Nonnull String url, @Nonnull Set<SQSMessage> messages) throws SQSException;

    static SQSServiceBuilder builder() {
        return new DefaultSQSServiceBuilder();
    }
}
