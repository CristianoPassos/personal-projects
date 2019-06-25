package de.cristiano.client.github;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.io.InputStream;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static util.ResourceUtils.getResourceAsStream;

@ExtendWith(MockitoExtension.class)
class GitHubGraphQLClientTest {
    private GitHubGraphQLClient client;

    @Mock
    HttpClient httpClient;

    @Test
    void getCommitMessages_shouldSucceed() throws IOException, InterruptedException {
        //Given
        HttpResponse<InputStream> response = Mockito.mock(HttpResponse.class);

        when(response.statusCode())
                .thenReturn(200);
        when(response.body())
                .thenReturn(getResourceAsStream("gitHub/graphQL/commitMessagesResponse.json"));

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);


        client = new GitHubGraphQLClient(httpClient);


        //When
        final List<String> commitMessages = client.getCommitMessages("cristiano", "personal-projects", "validToken");

        //Then
        assertThat(commitMessages, is(notNullValue()));
        assertThat(commitMessages.size(), is(5));
    }

    @Test
    void getCommitMessages_invalidRequest() throws IOException, InterruptedException {
        //Given
        HttpResponse<InputStream> response = Mockito.mock(HttpResponse.class);

        when(response.statusCode())
                .thenReturn(401);
        when(response.body())
                .thenReturn(getResourceAsStream("gitHub/graphQL/commitMessagesResponse.json"));

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(response);


        client = new GitHubGraphQLClient(httpClient);


        //When
        //Then
        assertThrows(RuntimeException.class, () ->
                client.getCommitMessages("cristiano", "personal-projects", "invalidToken")
        );
    }
}