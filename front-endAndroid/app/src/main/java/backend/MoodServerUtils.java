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

    public static String makeUrlRequest(URL url) {
        String result = "";
        HttpURLConnection con = null;
        try {
            con = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            result = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
        return result;
    }

    public static float getMoodRating(String text) {
        URL url = makeUrl(text);
        String response = makeUrlRequest(url);
        float result = Float.parseFloat(response);
        return result;
    }
}
