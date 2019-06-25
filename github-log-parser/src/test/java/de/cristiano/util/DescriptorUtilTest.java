package de.cristiano.util;

import de.cristiano.domain.Descriptor;
import de.cristiano.domain.Slack;
import de.cristiano.domain.constants.DataBase;
import de.cristiano.domain.constants.Journey;
import de.cristiano.domain.constants.Type;
import org.junit.jupiter.api.Test;
import util.ResourceUtils;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.text.IsEqualCompressingWhiteSpace.equalToCompressingWhiteSpace;

class DescriptorUtilTest {

    @Test
    public void descriptorCreation_shouldSucceed() {
        //Given
        final Descriptor descriptor = Descriptor.builder()
                .journey(Journey.MERCHANT)
                .journey(Journey.CUSTOMER)
                .name("GitHub-log-parser")
                .team("SRE")
                .jira("SRE")
                .type(Type.TOOL)
                .data_base(DataBase.NONE)
                .slack(Slack.builder().group("sre_group").channel("sre_channel").build())
                .build();


        //When
        final String representation = DescriptorUtil.toYAML(descriptor).get();

        //Then
        assertThat(representation, equalToCompressingWhiteSpace(ResourceUtils.readText("jackson/descriptor.yml")));
    }

}