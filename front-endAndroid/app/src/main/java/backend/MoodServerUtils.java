package backend;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MoodServerUtils {
    private MoodServerUtils() {}
    public static String protocol = "http";
    public static String host = "127.0.0.1";
    public static int port = 8000;
    public static String path = "/mood";

    public static URL makeUrl(String text) {
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

    public static String getResponse(String text) {
        URL url = makeUrl(text);
        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<String> response;
        Callable<String> server = new ServerHelper(url);
        response = executor.submit(server);
        try {
            return response.get();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
