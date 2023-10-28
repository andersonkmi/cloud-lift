package org.codecraftlabs.cloudlift.sqs;

import java.util.Objects;

public class SQSMessage {
    private final String id;
    private final String receiptHandle;
    private final String body;

    public SQSMessage(String id, String receiptHandle, String body) {
        this.id = id;
        this.receiptHandle = receiptHandle;
        this.body = body;
    }

    public String id() {
        return id;
    }

    public String receiptHandle() {
        return receiptHandle;
    }

    public String body() {
        return body;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, receiptHandle, body);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (this == other) {
            return true;
        }

        if (!this.getClass().equals(other.getClass())) {
            return false;
        }

        SQSMessage instance = (SQSMessage) other;
        return Objects.equals(this.id, instance.id) &&
                Objects.equals(this.receiptHandle, instance.receiptHandle) &&
                Objects.equals(this.id, this.body);
    }

    @Override
    public String toString() {
        return  "[" +
                "'id' = '" + id + "', " +
                "'receiptHandle' = '" + receiptHandle + "'" +
                "]";
    }
}
