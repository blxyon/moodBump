package backend;

import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

public class MoodServerUtilsTest {

    @Test
    public void testRequest() {
        String request = "These violent delights have violent ends";
        URL url = MoodServerUtils.makeUrl();
        String result = MoodServerUtils.getResponse(request);
        System.out.println(result);
    }
}