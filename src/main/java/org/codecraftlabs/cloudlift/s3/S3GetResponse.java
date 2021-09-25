package org.codecraftlabs.cloudlift.s3;

import java.util.Optional;

public class S3GetResponse {
    private String data;
    private byte[] rawData;
    private String contentType;

    public void setData(String data) {
        this.data = data;
    }

    public Optional<String> getData() {
        return Optional.ofNullable(data);
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }

    public Optional<byte[]> getRawData() {
        return Optional.ofNullable(rawData);
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Optional<String> getContentType() {
        return Optional.ofNullable(contentType);
    }
}
