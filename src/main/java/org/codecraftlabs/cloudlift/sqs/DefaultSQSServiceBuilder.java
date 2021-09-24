package org.codecraftlabs.cloudlift.sqs;

import org.codecraftlabs.cloudlift.data.AWSRegion;

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
