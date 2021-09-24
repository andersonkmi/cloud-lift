package org.codecraftlabs.cloudlift.s3;

import org.codecraftlabs.cloudlift.data.AWSRegion;

public interface S3ServiceBuilder {
    S3ServiceBuilder region(AWSRegion awsRegion);
    S3Service build();
}
