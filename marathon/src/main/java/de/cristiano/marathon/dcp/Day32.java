package de.cristiano.marathon.dcp;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;

/**
 * Implement a URL shortener with the following methods:
 * <p>
 * shorten(url), which shortens the url into a six-character alphanumeric string, such as zLg6wl.
 * <p>
 * restore(short), which expands the shortened string into the original url. If no such shortened string exists, return null.
 * <p>
 * Hint: What if we enter the same URL twice?
 */
public class Day32 {


    private final static char[] MAP = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    private final Map<Integer, String> urls = new HashMap<>();

    private final AtomicInteger atomicInteger = new AtomicInteger();

    String shorten(String url) {
        final int id = atomicInteger.incrementAndGet();

        urls.put(id, url);

        return toBase62(id);
    }

    private String toBase62(int id) {
        final StringBuilder builder = new StringBuilder();

        int temp = id;

        while (temp > 0) {
            builder.append(MAP[temp % 62]);
            temp = temp / 62;
        }

        return builder.reverse().toString();
    }


    String restore(String shortUrl) {
        final int id = fromBase62(shortUrl);

        return urls.get(id);
    }

    private int fromBase62(String shortUrl) {
        int id = 0;

        int pow = shortUrl.length() - 1;

        for (int index = 0; index < shortUrl.length(); index++) {
            int decimalValue = decimalValue(shortUrl.charAt(index));

            id += Math.pow(62, pow) * decimalValue;
            pow--;
        }

        return id;

    }

    private int decimalValue(char character) {
        for (int index = 0; index < MAP.length; index++) {
            if ((character == MAP[index])) {
                return index;
            }
        }

        throw new IllegalArgumentException(format("%s is invalid", character));
    }
}
