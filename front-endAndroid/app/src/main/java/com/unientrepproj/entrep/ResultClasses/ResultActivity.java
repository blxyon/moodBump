package com.unientrepproj.entrep.ResultClasses;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.unientrepproj.entrep.R;
import com.unientrepproj.entrep.TabsClasses.questionImagesAdapter;
import com.unientrepproj.entrep.imageModel;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<imageModel> arrayList;
    ViewFlipper viewFlipper;
    String quotes[]={"asfasg","asgasgas","asgashahashahsas"};
    int image[] = {R.mipmap.s1,R.mipmap.s2,R.mipmap.s3, R.mipmap.s4,R.mipmap.s5};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_activity);

        gridView = findViewById(R.id.grivViewResults);

        arrayList = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            imageModel imagemodel = new imageModel();
            imagemodel.setmThumbIds(image[i]);
            //add in array list
            arrayList.add(imagemodel);
        }

        resultImagesAdapter adpter = new resultImagesAdapter(this, arrayList);
        gridView.setAdapter(adpter);
        //item click listner
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), ("direct to spotify link for..." + image[position]), Toast.LENGTH_SHORT).show();
            }
        });

        viewFlipper=findViewById(R.id.quoteSlider);
        for(int i=0;i<quotes.length;i++){
            flipperQuotes(quotes[i]);
        }

    }
    public void flipperQuotes(String quote){
        TextView textView=new TextView(this);
        textView.setText(quote);

        viewFlipper.addView(textView);
        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);


    }
}
