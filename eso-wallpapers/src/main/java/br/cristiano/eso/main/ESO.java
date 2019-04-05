package br.cristiano.eso.main;

import br.cristiano.eso.WallpaperDownloader;
import br.cristiano.eso.error.RequiredParamMissing;
import br.cristiano.eso.extractor.WallpaperDownloadLinkExtractor;
import br.cristiano.eso.extractor.WallpaperIdsExtractor;
import br.cristiano.eso.extractor.WallpaperNextPageExtractor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static br.cristiano.eso.Constants.DEFAULT_DIRECTORY;
import static br.cristiano.eso.Constants.ESO_WALLPAPERS_PAGE;
import static java.lang.String.format;
import static java.util.stream.Collectors.toSet;

@Log4j2
public class ESO {

    private static String buildStoragePath(final String path) {
        String outputPath = path.endsWith(File.separator) ? path : path + File.separator;
        outputPath += DEFAULT_DIRECTORY + File.separator;
        return outputPath;
    }

    private static void checkParam(final String param) {
        if (null == param || param.isEmpty()) {
            throw new RequiredParamMissing("Output directory must be provided");
        }
    }

    private static void createDirectory(final String outputPath) {
        File fileDir = new File(outputPath);

        if (fileDir.exists()) {
            try {
                FileUtils.deleteDirectory(fileDir);
            } catch (IOException e) {
                throw new RequiredParamMissing(format("Not possible to empty directory [%s]", outputPath), e);
            }
        }

        if (!fileDir.mkdirs()) {
            throw new RequiredParamMissing(format("Not possible to create directory [%s]", outputPath));
        }
    }

    private static String getOptionalFirstPage(final String[] args) {
        try {
            return args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return ESO_WALLPAPERS_PAGE;
        }
    }

    private static String getStoragePath(final String[] args) {
        try {
            String storagePath = args[0];

            checkParam(storagePath);

            storagePath = buildStoragePath(storagePath);

            createDirectory(storagePath);

            log.info("Wallpapers will be stored at: {}", storagePath);

            return storagePath;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RequiredParamMissing("Output directory must be provided");
        }
    }

    public static void main(String[] args) {
        var nextPage = new WallpaperNextPageExtractor();
        var idsExtractor = new WallpaperIdsExtractor();
        var downloadLinkExtractor = new WallpaperDownloadLinkExtractor();
        var downloader = new WallpaperDownloader(getStoragePath(args));
        var firstPage = getOptionalFirstPage(args);

        nextPage
                .extractPages(firstPage)
                .parallelStream()
                .map(idsExtractor::extract)
                .flatMap(Set::stream)
                .collect(toSet())
                .parallelStream()
                .map(downloadLinkExtractor::extractWallpaperDownloadUrlById)
                .collect(toSet())
                .parallelStream()
                .forEach(downloader::download);
    }

}
