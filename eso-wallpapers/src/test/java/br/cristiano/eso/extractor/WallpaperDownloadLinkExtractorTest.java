package br.cristiano.eso.extractor;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WallpaperDownloadLinkExtractorTest {

    private static final WallpaperDownloadLinkExtractor extractor = new WallpaperDownloadLinkExtractor();

    @Test
    @Disabled
    void extraction_shouldSucceed() {
        //When
        String wallpaperDownloadUlr = extractor.extractWallpaperDownloadUrl("https://www.eso.org/public/images/eso1723a/");

        //Then
        assertEquals(wallpaperDownloadUlr, "https://cdn.eso.org/images/wallpaper5/eso1723a.jpg");
    }
}
