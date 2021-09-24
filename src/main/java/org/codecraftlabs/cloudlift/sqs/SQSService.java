package org.codecraftlabs.cloudlift.sqs;

public interface SQSService {
    String sendMessage(String url, String contents) throws SQSException;

    static SQSServiceBuilder builder() {
        return new DefaultSQSServiceBuilder();
    }
}
