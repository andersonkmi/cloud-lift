package org.codecraftlabs.cloudlift.s3;

import java.util.Optional;

public final class S3GetResponse {
    private final String data;
    private final byte[] rawData;
    private final String contentType;

    public S3GetResponse(String data, byte[] rawData, String contentType) {
        this.data = data;
        this.rawData = rawData;
        this.contentType = contentType;
    }

    public Optional<String> getData() {
        return Optional.ofNullable(data);
    }

    public Optional<byte[]> getRawData() {
        return Optional.ofNullable(rawData);
    }

    public Optional<String> getContentType() {
        return Optional.ofNullable(contentType);
    }
}
