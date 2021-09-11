package org.codecraftlabs.cloud.sqs;

import org.codecraftlabs.cloud.data.AWSRegion;

class DefaultSQSServiceBuilder implements SQSServiceBuilder {
    private AWSRegion awsRegion;

    DefaultSQSServiceBuilder() {
        // Hide constructor
    }

    @Override
    public SQSServiceBuilder region(AWSRegion awsRegion) {
        this.awsRegion = awsRegion;
        return this;
    }

    @Override
    public SQSService build() {
        if (awsRegion != null) {
            return new SQSDefaultServiceImplementation(awsRegion);
        }
        return new SQSDefaultServiceImplementation();
    }
}
