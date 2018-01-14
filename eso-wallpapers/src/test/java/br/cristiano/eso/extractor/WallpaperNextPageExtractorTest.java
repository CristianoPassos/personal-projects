package br.cristiano.eso.extractor;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static br.cristiano.eso.Constants.ESO_WALLPAPERS_PAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class WallpaperNextPageExtractorTest {
    private static final WallpaperNextPageExtractor extractor = new WallpaperNextPageExtractor();

    @Test
    public void testExtraction() {
        Optional<String> extract = extractor.extract(ESO_WALLPAPERS_PAGE);
        assertEquals("https://www.eso.org/public/images/archive/wallpapers/list/2/", extract.get());
    }

    @Test
    public void testLastPageExtraction() {
        Optional<String> extract = extractor.extract("https://www.eso.org/public/images/archive/wallpapers/list/222/");
        assertFalse(extract.isPresent());
    }

}
