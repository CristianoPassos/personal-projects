package br.cristiano.eso;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WallpaperDownloaderTest {

    private static WallpaperDownloader DOWNLOADER;

    private static String TEMP_DIR;

    @BeforeAll
    public static void beforeAll() {
        TEMP_DIR = System.getProperty("java.io.tmpdir") + File.separator;
        DOWNLOADER = new WallpaperDownloader(TEMP_DIR);
    }

    @Test
    public void testDownload() {
        DOWNLOADER.download("https://cdn.eso.org/images/wallpaper5/ann13016a.jpg");
        File image = new File(TEMP_DIR + "ann13016a.jpg");
        assertTrue(image.exists());
        image.delete();
    }
}
