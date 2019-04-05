package br.cristiano.eso.util;

import javax.annotation.Nonnull;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloaderURL {
    private final URL url;

    public DownloaderURL(@Nonnull final String imageUrl) throws MalformedURLException {
        this.url = new URL(imageUrl);
    }

    public String getPath() {
        return url.getPath();
    }

    public InputStream openStream() throws IOException {
        return url.openStream();
    }
}
