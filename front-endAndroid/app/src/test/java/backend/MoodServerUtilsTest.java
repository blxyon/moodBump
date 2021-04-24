package backend;

import android.content.Intent;
import android.util.Log;

import com.unientrepproj.entrep.ResultClasses.ResultActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MoodServerUtilsTest {

    @Test
    public void testRequest() {
        String request = "These violent delights have violent ends";
        URL url = MoodServerUtils.makeUrl();
        String result = MoodServerUtils.getResponse(request);
        System.out.println(result);
    }
    @Test
    public void testIndiv() {
        String s = "These violent delights have violent ends";
        URL url = MoodServerUtils.makeUrl();
        ArrayList<String> imagesLinks=new ArrayList();
        ArrayList<String> spotLinks=new ArrayList();


        String result = MoodServerUtils.getResponse(s);


        try {
            JSONObject jsonObject = new JSONObject(result);
            JSONArray playlists = jsonObject.getJSONArray("playlists");

            for (int i = 0; i < playlists.length(); i++) {
                JSONArray tempObj2 = playlists.getJSONArray(i);
                JSONObject tempObj = tempObj2.getJSONObject(0);


                String spotLink = (tempObj.getJSONObject("external_urls")).getString("spotify");
                spotLinks.add(spotLink);
                String imageLink = ((JSONObject) (tempObj.getJSONArray("images").get(0))).getString("url");
                imagesLinks.add(imageLink);
            }

            String[] arraySpot = spotLinks.toArray(new String[0]);
            String[] arrayImg = imagesLinks.toArray(new String[0]);


            System.out.println(arraySpot[0]);
            System.out.println(arrayImg[0]);
        }catch (JSONException err){
            Log.d("Error", err.toString());
        }

    }
}