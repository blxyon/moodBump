package com.unientrepproj.entrep.TabsClasses;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;

import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.unientrepproj.entrep.R;
import com.unientrepproj.entrep.ResultClasses.ResultActivity;
import com.unientrepproj.entrep.imageModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import backend.DBHelper;
import backend.MoodServerUtils;
import backend.journalEntry;


public class QuestionsFrag extends Fragment {
    GridView gridView;
    int image[] = {R.mipmap.afraid,R.mipmap.sad,R.mipmap.happy2, R.mipmap.angry,R.mipmap.surprised,R.mipmap.disgusted};
    String emotions[]={"afraid","sad","happy","angry","surprised","disgusted"};
    ArrayList<imageModel> arrayList;
    questionImagesAdapter adapter;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v=inflater.inflate(R.layout.fragment_questions,container,false);
        //gridView=(GridView)v.findViewById(R.id.girdviewQuestionAnswers);

        gridView = v.findViewById(R.id.girdviewQuestionAnswers);
        arrayList = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            imageModel imagemodel = new imageModel();
            imagemodel.setmThumbIds(image[i]);
            //add in array list
            arrayList.add(imagemodel);
        }

        adapter= new questionImagesAdapter(getActivity().getApplicationContext(), arrayList);
        gridView.setAdapter(adapter);
        //item click listner
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                arrayList.get(position).setPressed();
            }
        });

        setDynamicHeight();
        FloatingActionButton button=v.findViewById(R.id.submit_quest);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s=analyseQuestionsAnswers();
                Log.i("questions keywords",s);

                ArrayList<String> imagesLinks=new ArrayList();
                ArrayList<String> spotLinks=new ArrayList();

                Log.i("string from diary:",s);
                String s2= MoodServerUtils.getResponse(s);
                try {
                    JSONObject jsonObject = new JSONObject(s2);
                    Date date = Calendar.getInstance().getTime();
                    journalEntry je = new journalEntry(date, s, (float)jsonObject.getDouble("compound"));
                    DBHelper db = new DBHelper(getActivity().getApplicationContext());
                    db.insertEntry(je);
                    JSONArray playlists=jsonObject.getJSONArray("playlists");

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
        return v;
    }
    public String analyseQuestionsAnswers(){
        //ArrayList<Integer> booleans=new ArrayList<>();
        String s="";
        for(int i=0; i<arrayList.size();i++){
            if(arrayList.get(i).isPressed()==1) {
                s = s + " " + emotions[i];
            }
        }

        EditText q2Answer=v.findViewById(R.id.q2ans);
        s=s+" "+q2Answer.getText().toString();

        EditText q4Answer=v.findViewById(R.id.q4ans);
        s=s+" "+q4Answer.getText().toString();

        EditText q5Answer=v.findViewById(R.id.q5ans);
        s=s+" "+q5Answer.getText().toString();

        return s;
    }
    private void setDynamicHeight() {
        if (adapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int items = adapter.getCount();
        int rows = 0;

        View listItem = adapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight();

        float x = 1;
        if( items > 5 ){
            x = items/5;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight+20;
        gridView.setLayoutParams(params);
    }
}