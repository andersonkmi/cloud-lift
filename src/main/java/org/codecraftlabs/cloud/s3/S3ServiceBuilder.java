package org.codecraftlabs.cloud.s3;

import org.codecraftlabs.cloud.data.AWSRegion;

public interface S3ServiceBuilder {
    S3ServiceBuilder region(AWSRegion awsRegion);
    S3Service build();
}
