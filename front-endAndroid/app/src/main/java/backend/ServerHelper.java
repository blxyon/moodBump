package backend;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class ServerHelper implements Callable<String> {
    public URL url;
    public String body;
    public ServerHelper(URL url, String body) {
        this.url = url;
        this.body = body;
    }

    @Override
    public String call() throws Exception {
        String response = makeUrlRequest(this.url, this.body);
        return response;
    }

    private String makeUrlRequest(java.net.URL url, String body) {
        String result = "";
        HttpURLConnection con = null;
        OutputStream out = null;
        try {
            con = (HttpURLConnection) url.openConnection();
            con.setDoOutput(true);
            out = new BufferedOutputStream(con.getOutputStream());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out, "UTF-8"));
            writer.write(body);
            writer.flush();
            writer.close();
            out.close();
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
