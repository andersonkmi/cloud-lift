package org.codecraftlabs.cloudlift.sqs;

import org.codecraftlabs.cloudlift.data.AWSRegion;

import static org.codecraftlabs.cloudlift.data.AWSRegion.US_EAST_1;

class DefaultSQSServiceBuilder implements SQSServiceBuilder {
    private AWSRegion awsRegion = US_EAST_1;

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
