package de.cristiano.client.github;

import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import javax.annotation.Nonnull;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static java.net.http.HttpClient.Version.HTTP_1_1;
import static util.ResourceUtils.prepareQuery;

@Slf4j
@RequiredArgsConstructor
public class GitHubGraphQLClient {

    private final static String PATH_SELECTOR = "$.data.repository.ref.target.history.edges[*].node.messageHeadline";

    private final static String GIT_HUB_GRAPH_QL_API = "https://api.github.com/graphql";

    private final static int NOT_SUCCESSFUL_STATUS_CODE = 300;

    private final HttpClient httpClient;

    public GitHubGraphQLClient() {
        httpClient = HttpClient.newBuilder()
                .version(HTTP_1_1)
                .build();
    }


    public List<String> getCommitMessages(@Nonnull final String repositoryOwner,
                                          @Nonnull final String repositoryName,
                                          @Nonnull final String gitHubToken) {
        final String query = prepareQuery(repositoryOwner, repositoryName);

        return sendGraphQLQuery(query, gitHubToken);
    }

    private List<String> parseResponse(@Nonnull final InputStream is) {
        return JsonPath.parse(is)
                .read(PATH_SELECTOR);
    }

    @SneakyThrows
    private List<String> sendGraphQLQuery(@Nonnull final String query,
                                          @Nonnull final String gitHubToken) {
        log.info("Sending request: {}", query);

        final HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(GIT_HUB_GRAPH_QL_API))
                .header("Authorization", "token " + gitHubToken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(query))
                .build();

        final HttpResponse<InputStream> response = httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream());


        if (response.statusCode() >= NOT_SUCCESSFUL_STATUS_CODE) {
            throw new RuntimeException(IOUtils.toString(response.body(), StandardCharsets.UTF_8));
        }

        final List<String> commitMessages = parseResponse(response.body());

        log.info("Response: {]", commitMessages);

        return commitMessages;
    }

}
