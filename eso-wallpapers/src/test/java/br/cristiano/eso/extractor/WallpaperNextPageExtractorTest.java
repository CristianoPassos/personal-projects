package br.cristiano.eso.extractor;

import java.time.Duration;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

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
        Assertions.assertTimeout(Duration.ofSeconds(5), () -> {
            Optional<String> possibleNextPage = Optional.of("https://www.eso.org/public/images/archive/wallpapers/list/285/");

            while (possibleNextPage.isPresent()) {
                possibleNextPage = extractor.extract(possibleNextPage.get());
            }

            assertFalse(possibleNextPage.isPresent());
        });
    }
}
