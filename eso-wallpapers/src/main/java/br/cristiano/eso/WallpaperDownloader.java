package br.cristiano.eso;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FilenameUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Log4j2
public class WallpaperDownloader {

    private final String outputDir;

    public WallpaperDownloader(String outputDir) {
        this.outputDir = outputDir;
    }

    public void download(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            String file = outputDir + FilenameUtils.getName(url.getPath());
            log.info("Downloading: {}", url.getPath());
            log.info("Saving to: {} ", file);

            try (InputStream in = url.openStream()) {
                Files.copy(in, Paths.get(file), REPLACE_EXISTING);
            }
        } catch (FileAlreadyExistsException e) {
            log.warn(e.getLocalizedMessage());
        } catch (IOException e) {
            log.error("Error while downloading picture", e);
        }
    }

}
