package org.codecraftlabs.cloud.s3;

import org.codecraftlabs.cloud.data.AWSRegion;

import static org.codecraftlabs.cloud.data.AWSRegion.US_EAST_1;

final class DefaultS3ServiceBuilder implements S3ServiceBuilder {
    private AWSRegion awsRegion = US_EAST_1;

    DefaultS3ServiceBuilder() {
        // to avoid this class being created outside the S3Service interface
    }

    @Override
    public DefaultS3ServiceBuilder region(AWSRegion awsRegion) {
        if (awsRegion != null) {
            this.awsRegion = awsRegion;
        }
        return this;
    }

    @Override
    public S3Service build() {
        return new S3ServiceDefaultImplementation(awsRegion);
    }
}
