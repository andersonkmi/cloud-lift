package org.codecraftlabs.cloudlift.data;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import software.amazon.awssdk.regions.Region;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class AWSRegionTests {

    @ParameterizedTest(name = "Test case {index}: {0} corresponds to {1}")
    @CsvSource({
            "AP_EAST_1, ap-east-1",
            "AP_SOUTH_1, ap-south-1",
            "AP_NORTHEAST_1, ap-northeast-1",
            "AP_NORTHEAST_2, ap-northeast-2",
            "AP_NORTHEAST_3, ap-northeast-3",
            "AP_SOUTHEAST_1, ap-southeast-1",
            "AP_SOUTHEAST_2, ap-southeast-2",
            "EU_WEST_1, eu-west-1",
            "EU_WEST_2, eu-west-2",
            "EU_WEST_3, eu-west-3",
            "EU_NORTH_1, eu-north-1",
            "EU_CENTRAL_1, eu-central-1",
            "ME_SOUTH_1, me-south-1",
            "CA_CENTRAL_1, ca-central-1",
            "SA_EAST_1, sa-east-1",
            "US_WEST_1, us-west-1",
            "US_WEST_2, us-west-2",
            "US_EAST_1, us-east-1",
            "US_EAST_2, us-east-2",
            "AF_SOUTH_1, af-south-1"
    })
    public void validateRegionItems(String entry, String code) {
        var value = AWSRegion.valueOf(entry);
        assertThat(value.code(), is(code));
        assertDoesNotThrow(() -> Region.of(entry));
    }

    @Test
    @Disabled
    public void validateIfHasAllRegions() {
        assertThat(AWSRegion.values().length, is(Region.regions().size()));
    }
}
