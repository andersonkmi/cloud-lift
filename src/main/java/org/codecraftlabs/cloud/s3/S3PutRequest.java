package org.codecraftlabs.cloud.s3;

import org.codecraftlabs.cloud.data.ContentType;

import java.util.Objects;
import java.util.Optional;

import static org.codecraftlabs.cloud.data.ContentType.TEXT_PLAIN;

public class S3PutRequest {
    private final String bucket;
    private final String key;
    private final String contents;
    private final ContentType contentType;
    private final long contentLength;

    public S3PutRequest(String bucket, String key, String contents) {
        this.bucket = bucket;
        this.key = key;
        this.contents = contents;
        this.contentLength = contents.getBytes().length;
        this.contentType = TEXT_PLAIN;
    }

    public S3PutRequest(String bucket, String key, String contents, ContentType contentType) {
        this.bucket = bucket;
        this.key = key;
        this.contents = contents;
        this.contentType = contentType;
        this.contentLength = contents.getBytes().length;
    }

    public Optional<String> bucket() {
        return Optional.ofNullable(bucket);
    }

    public Optional<String> key() {
        return Optional.ofNullable(key);
    }

    public Optional<String> contents() {
        return Optional.ofNullable(contents);
    }

    public ContentType contentType() {
        return contentType;
    }

    public long contentLength() {
        return contentLength;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bucket, key, contents, contentType);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!getClass().equals(other.getClass())) {
            return false;
        }

        var instance = (S3PutRequest) other;

        return Objects.equals(bucket, instance.bucket) &&
               Objects.equals(key, instance.key) &&
               Objects.equals(contents, instance.contents) &&
               this.contentType == instance.contentType;
    }
}
