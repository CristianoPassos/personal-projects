package br.cristiano.eso.main;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import static br.cristiano.eso.Constants.DEFAULT_DIRECTORY;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ESOIntegrationTest {

    private static String[] ARGS;

    private static String TEMP_DIR;

    @BeforeAll
    public static void beforeAll() {
        TEMP_DIR = System.getProperty("java.io.tmpdir") + File.separator + UUID.randomUUID().toString() + File.separator;
        ARGS = new String[]{TEMP_DIR, "https://www.eso.org/public/unitedkingdom/images/archive/wallpapers/list/239/"};
    }

    @Test
    public void testESO() {
        ESO.main(ARGS);
        File wallpapers = new File(TEMP_DIR + DEFAULT_DIRECTORY + File.separator);
        assertTrue(Objects.requireNonNull(wallpapers.listFiles()).length > 1);
    }

    @AfterEach
    public void afterEach() throws IOException {
        FileUtils.deleteDirectory(new File(TEMP_DIR));
    }
}
