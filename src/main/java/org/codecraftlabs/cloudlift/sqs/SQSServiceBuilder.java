package org.codecraftlabs.cloudlift.sqs;

import org.codecraftlabs.cloudlift.data.AWSRegion;

public interface SQSServiceBuilder {
    SQSServiceBuilder region(AWSRegion awsRegion);
    SQSService build();
}
