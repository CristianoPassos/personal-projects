package br.cristiano.eso;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WallpaperDownloaderTest {

    private static WallpaperDownloader downloader;
    private static String tempDir;

    @BeforeAll
    public static void beforeAll() {
        tempDir = System.getProperty("java.io.tmpdir") + "eso_download/";
        downloader = new WallpaperDownloader(tempDir);

    }

    @Test
    public void testDownload() {
        downloader.download("https://cdn.eso.org/images/wallpaper5/ann13016a.jpg");
        File image = new File(tempDir + "ann13016a.jpg");
        assertTrue(image.exists());
        image.delete();
    }
}
