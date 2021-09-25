package org.codecraftlabs.cloudlift.s3;

import org.codecraftlabs.cloudlift.AWSException;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;

import javax.annotation.Nonnull;
import java.util.Optional;
import java.util.Set;

public interface S3Service {
    
    static S3ServiceBuilder builder() {
        return new DefaultS3ServiceBuilder();
    }
    
    void putObject(@Nonnull S3PutRequest request) throws AWSException, InvalidPutRequestException;

    void putObjects(@Nonnull Set<S3PutRequest> requests) throws AWSException, InvalidPutRequestException;

    Optional<S3GetResponse> getObject(@Nonnull S3GetRequest request) throws AWSException, InvalidGetRequestException;
}
