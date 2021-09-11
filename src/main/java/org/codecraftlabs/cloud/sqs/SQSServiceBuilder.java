package org.codecraftlabs.cloud.sqs;

import org.codecraftlabs.cloud.data.AWSRegion;

public interface SQSServiceBuilder {
    SQSServiceBuilder region(AWSRegion awsRegion);
    SQSService build();
}
