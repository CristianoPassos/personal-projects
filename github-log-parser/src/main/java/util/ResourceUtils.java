package util;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.InputStream;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.apache.commons.lang3.StringUtils.EMPTY;

public interface ResourceUtils {
    String COMMIT_MESSAGES_QUERY_PATH = "gitHub/graphQL/commitMessagesQuery.json";

    @SneakyThrows
    static byte[] readByes(@Nonnull final String resource) {
        try (InputStream is = getResourceAsStream(resource)) {
            return IOUtils.toByteArray(is);
        }
    }

    @SneakyThrows
    static String readText(@Nullable final String resource) {
        if (resource == null) {
            return EMPTY;
        }

        return new String(readByes(resource), UTF_8);
    }

    static InputStream getResourceAsStream(@Nonnull final String resource) {
        final ClassLoader classLoader = ResourceUtils.class.getClassLoader();
        final InputStream inputStream = classLoader.getResourceAsStream(resource);

        if (inputStream == null) {
            throw new RuntimeException("Resource not found: " + resource);
        }

        return inputStream;
    }


    static String prepareQuery(@Nonnull final String repositoryOwner,
                               @Nonnull final String repositoryName) {

        return readText(COMMIT_MESSAGES_QUERY_PATH)
                .replace("${repo_owner}", repositoryOwner)
                .replace("${repo_name}", repositoryName);

    }


}
