package com.unientrepproj.entrep;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.unientrepproj.entrep.ResultClasses.ResultActivity;
import com.unientrepproj.entrep.TabsClasses.QuestionsDiaryActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import backend.MoodServerUtils;

public class StartingPage extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_page);

        FloatingActionButton button =findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toQuestion();
            }
        });
        ImageButton calendarBut=findViewById(R.id.calendar);
        calendarBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCalendar();
            }
        });
        ImageButton contactsBut=findViewById(R.id.contacts);
        contactsBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toContacts();
            }
        });


        Button happyBut=findViewById(R.id.button17);
        happyBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directToSpotify("happy");
            }
        });

        Button keen=findViewById(R.id.button15);
        keen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directToSpotify("keen");
            }
        });

        Button sad=findViewById(R.id.button16);
        sad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directToSpotify("sad");
            }
        });

        Button guilty=findViewById(R.id.button14);
        guilty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directToSpotify("guilty");
            }
        });

        Button quiet=findViewById(R.id.button12);
        quiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directToSpotify("quiet");
            }
        });

        Button angry=findViewById(R.id.button13);
        angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directToSpotify("angry");
            }
        });

        Button active=findViewById(R.id.button10);
        active.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directToSpotify("active");
            }
        });

        Button dazed=findViewById(R.id.button4);
        dazed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directToSpotify("dazed");
            }
        });

        Button afraid=findViewById(R.id.button11);
        afraid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                directToSpotify("afraid");
            }
        });


        DatabaseManager db=new DatabaseManager(this);
        Cursor cursor=db.getData();
        cursor.moveToFirst();
        String name =cursor.getString(0);
        TextView nameTex=findViewById(R.id.nameTex);
        nameTex.setText("Welcome "+name);
    }

    public void directToSpotify(String s){
        Intent intent=new Intent(Intent.ACTION_VIEW);

        ArrayList<String> imagesLinks=new ArrayList();
        ArrayList<String> spotLinks=new ArrayList();

        String s2= MoodServerUtils.getResponse(s);
        try {
            JSONObject jsonObject = new JSONObject(s2);

            JSONArray playlists=jsonObject.getJSONArray("playlists");

            for(int i=0;i<playlists.length();i++){
                JSONArray tempObj2=playlists.getJSONArray(i);
                JSONObject tempObj=tempObj2.getJSONObject(0);

                String spotLink= (tempObj.getJSONObject("external_urls")).getString("spotify");
                spotLinks.add(spotLink);
            }
            String arr[]=spotLinks.get(0).split("/");

            intent.setData(Uri.parse("spotify:playlist:"+arr[arr.length-1]));

            intent.putExtra(
                    Intent.EXTRA_REFERRER,
                    Uri.parse("android-app://"+this)
            );
            startActivity(intent);

        }catch (JSONException err){
            Log.d("Error", err.toString());
        }
    }
    public void toQuestion(){
        startActivity(new Intent(this, QuestionsDiaryActivity.class));
    }
    public void toCalendar(){
        startActivity(new Intent(this, CalendarActivity.class));
    }
    public void toContacts(){
        startActivity(new Intent(this, ContactActivity.class));
    }
}
