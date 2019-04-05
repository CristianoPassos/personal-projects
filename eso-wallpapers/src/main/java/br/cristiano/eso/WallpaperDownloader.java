package br.cristiano.eso;

import br.cristiano.eso.util.DownloaderURL;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Log4j2
public class WallpaperDownloader {

    private final String outputDir;

    public WallpaperDownloader(@Nonnull final String outputDir) {
        this.outputDir = outputDir;
    }

    public void download(@Nonnull final String imageUrl) {
        try {
            DownloaderURL url = new DownloaderURL(imageUrl);
            download(url);
        } catch (MalformedURLException ex) {
            log.error("Invalid URL " + imageUrl, ex);
        }
    }

    public void download(@Nonnull final DownloaderURL imageUrl) {

        final String file = outputDir + FilenameUtils.getName(imageUrl.getPath());

        log.info("Downloading: {}", imageUrl.getPath());
        log.info("Saving to: {} ", file);

        try (InputStream in = imageUrl.openStream()) {
            Files.copy(in, Paths.get(file), REPLACE_EXISTING);
        } catch (FileAlreadyExistsException e) {
            log.warn(e.getLocalizedMessage());
        } catch (IOException e) {
            log.error("Error while downloading picture", e);
        }
    }

}
