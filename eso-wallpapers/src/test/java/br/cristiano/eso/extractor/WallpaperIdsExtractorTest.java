package br.cristiano.eso.extractor;

import java.util.Set;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class WallpaperIdsExtractorTest {

    private static final WallpaperIdsExtractor extractor = new WallpaperIdsExtractor();

    @Test
    void testDownload() {
        Set<String> wallpaperIds = extractor.extract("https://www.eso.org/public/images/archive/wallpapers/");

        assertEquals(50, wallpaperIds.size());
        assertThat(wallpaperIds, hasItems("eso1723a", "eso1137a"));
    }
}
