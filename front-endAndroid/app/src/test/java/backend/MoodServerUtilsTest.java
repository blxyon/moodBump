package backend;

import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.*;

public class MoodServerUtilsTest {
    @Test
    public void testURL() {
        String request = "These violent delights have violent ends";
        URL url = MoodServerUtils.makeUrl(request);
        URL expected = null;
        try {
            expected = new URL("http://127.0.0.1:8000/mood?text=These%20violent%20delights%20have%20violent%20ends");
        } catch (MalformedURLException e) {
            e.printStackTrace();
            assertFalse(true);
        }
        assertTrue(url.equals(expected));
    }
}