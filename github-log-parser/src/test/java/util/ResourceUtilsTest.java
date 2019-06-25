package util;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ResourceUtilsTest {

    @Test
    void prepareQuery_shouldSucceed() {
        //When
        final String query = ResourceUtils.prepareQuery("cristiano", "personal-projects");

        //Then
        assertThat(query, is(ResourceUtils.readText("gitHub/graphQL/expectedQuery.json")));
    }
}