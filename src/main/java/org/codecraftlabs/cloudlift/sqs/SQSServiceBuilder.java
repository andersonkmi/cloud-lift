package org.codecraftlabs.cloudlift.sqs;

import org.codecraftlabs.cloudlift.data.AWSRegion;

import javax.annotation.Nonnull;

public interface SQSServiceBuilder {
    @Nonnull
    SQSServiceBuilder region(@Nonnull AWSRegion awsRegion);

    @Nonnull
    SQSService build();
}
