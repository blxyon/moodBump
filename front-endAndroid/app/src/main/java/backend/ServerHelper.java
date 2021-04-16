package backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class ServerHelper implements Callable<String> {
    public URL url;
    public ServerHelper(URL url) {
        this.url = url;
    }

    @Override
    public String call() throws Exception {
        String response = makeUrlRequest(this.url);
        return response;
    }

    private String makeUrlRequest(java.net.URL url) {
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
}
