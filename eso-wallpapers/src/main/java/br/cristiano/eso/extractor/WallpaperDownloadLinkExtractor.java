package br.cristiano.eso.extractor;

import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static br.cristiano.eso.Constants.ESO_WALLPAPER_PAGE;

@Log4j2
public class WallpaperDownloadLinkExtractor {
    private static List<String> wallpaperSizes;

    static {
        wallpaperSizes = Arrays.asList(
                ":containsOwn(2048x1536)",
                ":containsOwn(1920x1200)",
                ":containsOwn(1600x1200)",
                ":containsOwn(1280x1024)",
                ":containsOwn(1024x768)"
        );
    }

    public String extractWallpaperDownloadUrlById(String id) {
        return extractWallpaperDownloadUrl(ESO_WALLPAPER_PAGE + "/" + id);
    }

    public String extractWallpaperDownloadUrl(String wallpaperPageUrl) {
        String url = "";

        try {
            log.info("Extracting download url: {}", wallpaperPageUrl);

            Document document = Jsoup.connect(wallpaperPageUrl).get();
            url = extractWallpaperDownloadElement(document)
                    .attr("href");

            log.info("URL extracted: {}", url);
        } catch (IOException e) {
            log.error(("Extracting download url failed: " + wallpaperPageUrl), e);
        }

        return url;
    }

    private Element extractWallpaperDownloadElement(Document document) {
        Element foundWallpaper = null;
        int i = 0;

        while (foundWallpaper == null && i < wallpaperSizes.size()) {
            Optional<Element> wallpaper = searchWallpaperSize(document, wallpaperSizes.get(i));
            if (wallpaper.isPresent()) {
                foundWallpaper = wallpaper.get();
            }
            i++;
        }

        return foundWallpaper;
    }

    private Optional<Element> searchWallpaperSize(Document document, String selector) {
        return Optional.ofNullable(document.selectFirst(selector));
    }

}
