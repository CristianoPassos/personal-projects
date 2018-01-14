package br.cristiano.eso.main;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import static br.cristiano.eso.Constants.DEFAULT_DIRECTORY;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ESOIntegrationTest {

    private static String[] args;
    private static String tempDir;

    @BeforeAll
    public static void beforeAll() {
        tempDir = System.getProperty("java.io.tmpdir") + UUID.randomUUID().toString() + File.separator;
        args = new String[]{tempDir, "https://www.eso.org/public/unitedkingdom/images/archive/wallpapers/list/221/"};
    }

    @Test
    public void testESO() {
        ESO.main(args);
        File wallpapers = new File(tempDir + DEFAULT_DIRECTORY + File.separator);
        assertTrue(wallpapers.listFiles().length > 1);
    }

    @AfterEach
    public void afterEach() throws IOException {
        FileUtils.deleteDirectory(new File(tempDir));
    }

}
