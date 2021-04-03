package com.unientrepproj.entrep;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Questions extends AppCompatActivity {
    int image[] = {R.mipmap.afraid,R.mipmap.sad,R.mipmap.happy, R.mipmap.angry,R.mipmap.surprised};
    GridView gridView;
    ArrayList<imageModel> arrayList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questions);
        gridView = (GridView) findViewById(R.id.girdviewQuestionAnswers);
        arrayList = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            imageModel imagemodel = new imageModel();
            imagemodel.setmThumbIds(image[i]);
            //add in array list
            arrayList.add(imagemodel);
        }

        questionImagesAdapter adpter= new questionImagesAdapter(getApplicationContext(), arrayList);
        gridView.setAdapter(adpter);
        //item click listner
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
            }
        });
        ;
    }
}
