package org.codecraftlabs.cloudlift.s3;

import org.codecraftlabs.cloudlift.AWSException;
import org.codecraftlabs.cloudlift.data.AWSRegion;
import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.util.Optional;
import java.util.Set;

import static software.amazon.awssdk.core.sync.RequestBody.fromString;

final class S3ServiceDefaultImplementation implements S3Service {
    private final S3Client s3Client;

    S3ServiceDefaultImplementation(@Nonnull AWSRegion awsRegion) {
        this.s3Client = S3Client.builder().region(awsRegion.region()).build();
    }

    public void putObject(@Nonnull S3PutRequest request) {
        String bucket = request.bucket().orElseThrow(() -> new InvalidPutRequestException("Missing bucket"));
        String key = request.key().orElseThrow(() -> new InvalidPutRequestException("Missing key"));
        String contents = request.contents().orElseThrow(() -> new InvalidPutRequestException("Missing contents"));

        try {
            s3Client.putObject(PutObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .contentType(request.contentType().code())
                    .contentLength(request.contentLength())
                    .build(), fromString(contents));

        } catch (S3Exception exception) {
            throw new AWSException("Error when calling S3 service", exception);
        }
    }

    public void putObjects(@Nonnull Set<S3PutRequest> requests) {
        if (requests.isEmpty()) {
            return;
        }

        for (S3PutRequest item : requests) {
            putObject(item);
        }
    }

    @Nonnull
    public Optional<S3GetResponse> getObject(@Nonnull S3GetRequest request) {
        String bucket = request.bucket().orElseThrow(() -> new InvalidGetRequestException("Missing bucket"));
        String key = request.key().orElseThrow(() -> new InvalidGetRequestException("Missing key"));

        try {
            ResponseInputStream<GetObjectResponse> response = s3Client.getObject(GetObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build());


            byte[] rawData;
            GetObjectResponse s3ObjectResponse = response.response();
            try {
                rawData = response.readAllBytes();
            } catch (IOException exception) {
                throw new AWSException("Failed to read S3 object contents", exception);
            }

            S3GetResponse result = new S3GetResponse(new String(rawData), rawData, s3ObjectResponse.contentType());
            return Optional.of(result);
        } catch (S3Exception exception) {
            throw new AWSException("Error when calling S3 service", exception);
        }
    }
}
