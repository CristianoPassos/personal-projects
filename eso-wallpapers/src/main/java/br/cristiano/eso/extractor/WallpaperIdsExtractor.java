package br.cristiano.eso.extractor;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Collections.emptySet;

@Log4j2
public class WallpaperIdsExtractor {
    private static String WALLPAPER_PAGE_REGEX = "(id: '.+',)";

    public Set<String> extract(String wallpapersPageUrl) {
        Set<String> pageIds = emptySet();

        try {
            log.info("Extracting image ids of {}", wallpapersPageUrl);

            URLConnection urlConnection = new URL(wallpapersPageUrl).openConnection();
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            String html = IOUtils.toString(inputStream, Charset.defaultCharset());
            pageIds = Pattern
                .compile(WALLPAPER_PAGE_REGEX)
                .matcher(html)
                .results()
                .map(foundId -> foundId.group())
                .map(id -> cleanId(id))
                .collect(Collectors.toSet());
        } catch (IOException e) {
            log.error("Error extracting wallpaper ids of " + wallpapersPageUrl, e);
        }

        return pageIds;
    }

    private String cleanId(String id) {
        //TODO: Improve logic
        return id.replaceAll("id: '", "")
            .replaceAll("',", "")
            .trim();
    }
}
