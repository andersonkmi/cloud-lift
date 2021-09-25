package org.codecraftlabs.cloudlift.s3;

import java.util.Objects;
import java.util.Optional;

public class S3GetRequest {
    private final String bucket;
    private final String key;

    public S3GetRequest(String bucket, String key) {
        this.bucket = bucket;
        this.key = key;
    }

    public Optional<String> bucket() {
        return Optional.ofNullable(bucket);
    }

    public Optional<String> key() {
        return Optional.ofNullable(key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bucket, key);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!getClass().equals(other.getClass())) {
            return false;
        }

        var instance = (S3GetRequest) other;

        return Objects.equals(bucket, instance.bucket) &&
                Objects.equals(key, instance.key);
    }
}
