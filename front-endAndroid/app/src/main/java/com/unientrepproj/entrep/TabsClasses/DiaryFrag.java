package com.unientrepproj.entrep.TabsClasses;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.unientrepproj.entrep.R;
import com.unientrepproj.entrep.ResultClasses.ResultActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.SimpleTimeZone;

import backend.DBHelper;
import backend.MoodServerUtils;
import backend.journalEntry;


public class DiaryFrag extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v=inflater.inflate(R.layout.fragment_diary,container,false);

        EditText journalText=v.findViewById(R.id.editTextTextPersonName2);


        ImageButton but=v.findViewById(R.id.button5);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=journalText.getText().toString();
                ArrayList<String> imagesLinks=new ArrayList();
                ArrayList<String> spotLinks=new ArrayList();

                Log.i("string from diary:",s);
                String s2=MoodServerUtils.getResponse(s);
                Log.i("server response", s2);
                try {
                    JSONObject jsonObject = new JSONObject(s2);

                    JSONArray playlists=jsonObject.getJSONArray("playlists");
                    Date date = Calendar.getInstance().getTime();
                    journalEntry je = new journalEntry(date, s, (float)jsonObject.getDouble("compound"));
                    DBHelper db = new DBHelper(getActivity().getApplicationContext());
                    db.insertEntry(je);
                    for(int i=0;i<playlists.length();i++){
                        JSONArray tempObj2=playlists.getJSONArray(i);
                        JSONObject tempObj=tempObj2.getJSONObject(0);


                        String spotLink= (tempObj.getJSONObject("external_urls")).getString("spotify");
                        spotLinks.add(spotLink);
                        String imageLink=((JSONObject) (tempObj.getJSONArray("images").get(0))).getString("url");
                        imagesLinks.add(imageLink);
                    }

                    Intent intent=new Intent(getActivity().getApplicationContext(), ResultActivity.class);//defining the next page to start
                    String[] arraySpot = spotLinks.toArray(new String[0]);
                    String[] arrayImg = imagesLinks.toArray(new String[0]);
                    intent.putExtra("spotLinks",arraySpot);
                    intent.putExtra("imgLinks",arrayImg);
                    startActivity(intent);//starting it
                }catch (JSONException err){
                    Log.d("Error", err.toString());
                }



            }
        });

        ImageButton but2=v.findViewById(R.id.button7);
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return v;
    }


}