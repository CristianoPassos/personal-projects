package br.cristiano.eso.extractor;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class WallpaperIdsExtractorTest {
    private static final WallpaperIdsExtractor extractor = new WallpaperIdsExtractor();

    @Test
    public void testDownload() {
        Set<String> wallpaperIds = extractor.extract("https://www.eso.org/public/images/archive/wallpapers/");
        assertEquals(50, wallpaperIds.size());
        assertThat(wallpaperIds, hasItems("eso1039a",
                "vlt-brunier-nuit",
                "potw1119a")
        );
    }
}
