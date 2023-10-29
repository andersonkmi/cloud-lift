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

    public void putObject(@Nonnull S3PutRequest request) throws AWSException, InvalidPutRequestException {
        var bucket = request.bucket().orElseThrow(() -> new InvalidPutRequestException("Missing bucket"));
        var key = request.key().orElseThrow(() -> new InvalidPutRequestException("Missing key"));
        var contents = request.contents().orElseThrow(() -> new InvalidPutRequestException("Missing contents"));

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

    public void putObjects(@Nonnull Set<S3PutRequest> requests) throws AWSException, InvalidPutRequestException {
        if (requests.isEmpty()) {
            return;
        }

        for (var item : requests) {
            putObject(item);
        }
    }

    @Nonnull
    public Optional<S3GetResponse> getObject(@Nonnull S3GetRequest request) throws AWSException, InvalidGetRequestException {
        var bucket = request.bucket().orElseThrow(() -> new InvalidGetRequestException("Missing bucket"));
        var key = request.key().orElseThrow(() -> new InvalidGetRequestException("Missing key"));

        try {
            ResponseInputStream<GetObjectResponse> response = s3Client.getObject(GetObjectRequest.builder()
                    .bucket(bucket)
                    .key(key)
                    .build());

            S3GetResponse result = new S3GetResponse();
            GetObjectResponse s3ObjectResponse = response.response();
            result.setContentType(s3ObjectResponse.contentType());
            try {
                byte[] rawData = response.readAllBytes();
                result.setRawData(rawData);
                result.setData(new String(rawData));
            } catch (IOException exception) {
                throw new AWSException("Failed to read S3 object contents", exception);
            }

            return Optional.of(result);
        } catch (S3Exception exception) {
            throw new AWSException("Error when calling S3 service", exception);
        }
    }
}
