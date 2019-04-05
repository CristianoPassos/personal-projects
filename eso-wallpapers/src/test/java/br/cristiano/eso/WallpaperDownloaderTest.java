package br.cristiano.eso;

import br.cristiano.eso.util.DownloaderURL;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WallpaperDownloaderTest {

    private static WallpaperDownloader DOWNLOADER;

    private static String TEMP_DIR;

    @Mock
    private DownloaderURL url;

    @BeforeAll
    public static void beforeAll() {
        TEMP_DIR = System.getProperty("java.io.tmpdir") + File.separator;
        DOWNLOADER = new WallpaperDownloader(TEMP_DIR);
    }

    @Test
    public void testDownload() throws IOException {
        //Given
        when(url.getPath())
                .thenReturn("images/wallpaper5/ann13016a.jpg");
        when(url.openStream())
                .thenReturn(IOUtils.toInputStream("src/test/resources/images/ann13016a.jpg", Charset.defaultCharset()));

        //When
        DOWNLOADER.download(url);

        //Then
        File image = new File(TEMP_DIR + "ann13016a.jpg");
        assertTrue(image.exists());
        image.delete();
    }
}
