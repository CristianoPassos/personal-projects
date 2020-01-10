package br.cristiano.eso.extractor;

import lombok.extern.log4j.Log4j2;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static br.cristiano.eso.Constants.ESO_HOST;
import static java.lang.String.format;

@Log4j2
public class WallpaperNextPageExtractor {
    private static String NEXT_PAGE_SELECTOR = ":containsOwn(next)";

    public Optional<String> extract(String wallpapersPageUrl) {
        Optional<String> nextPage = Optional.empty();

        try {
            log.info("Extracting next page of {}", wallpapersPageUrl);

            nextPage = Optional.ofNullable(
                    Jsoup.connect(wallpapersPageUrl)
                            .get()
                            .selectFirst(NEXT_PAGE_SELECTOR))
                    .map(page -> page.attr("href"))
                    .map(page -> ESO_HOST + page);

            nextPage.ifPresent(page -> log.info(format("Page found [%s]", page)));
        } catch (IOException e) {
            log.error("Error extracting next page of " + wallpapersPageUrl, e);
        }

        return nextPage;
    }

    public Set<String> extractPages(String firstPage) {
        Set<String> pages = new HashSet<>();

        pages.add(firstPage);

        Optional<String> nextPage = extract(firstPage);

        while (nextPage.isPresent()) {
            String page = nextPage.get();
            pages.add(page);
            nextPage = extract(page);
        }

        return pages;
    }

}