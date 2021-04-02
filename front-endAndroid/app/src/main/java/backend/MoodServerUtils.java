package backend;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class MoodServerUtils {
    private MoodServerUtils() {}
    public static String protocol = "http";
    public static String host = "127.0.0.1";
    public static int port = 8000;
    public static String path = "/mood";

    public static URL makeUrlRequest(String text) {
        String query = "text=" + text;
        URI uri = null;
        try {
            uri = new URI(protocol, null, host, port, path, query, null);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        URL url = null;
        try {
            url = uri.toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }
}
