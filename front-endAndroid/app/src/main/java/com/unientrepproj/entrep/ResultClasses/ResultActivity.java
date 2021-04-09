package com.unientrepproj.entrep.ResultClasses;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.unientrepproj.entrep.R;
import com.unientrepproj.entrep.TabsClasses.questionImagesAdapter;
import com.unientrepproj.entrep.imageModel;
import com.unientrepproj.entrep.imageModelDislikeLike;

import java.util.ArrayList;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

public class ResultActivity extends AppCompatActivity {
    GridView gridView;
    ArrayList<imageModelDislikeLike> arrayList;
    ViewFlipper viewFlipper;
    String quotes[]={"asfasg","asgasgas","asgashahashahsas"};
    int image[] = {R.mipmap.s1,R.mipmap.s2,R.mipmap.s3, R.mipmap.s4,R.mipmap.s5};
    String spotLinks[]={"https1","ht","asgas","asgas","asgasg"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results_activity);

        gridView = findViewById(R.id.grivViewResults);

        arrayList = new ArrayList<>();
        for (int i = 0; i < image.length; i++) {
            imageModelDislikeLike imagemodel = new imageModelDislikeLike();
            imagemodel.setmThumbIds(image[i]);
            imagemodel.setLink(spotLinks[i]);

            //add in array list
            arrayList.add(imagemodel);
        }

        resultImagesAdapter adpter = new resultImagesAdapter(this, arrayList);
        gridView.setAdapter(adpter);
        //item click listner
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView parent, View view, int position, long id) {
//                Toast.makeText(getApplicationContext(), ("direct to spotify link for..." + image[position]), Toast.LENGTH_SHORT).show();
//            }
//        });

        viewFlipper=findViewById(R.id.quoteSlider);
        for(int i=0;i<quotes.length;i++){
            flipperQuotes(quotes[i]);
        }

    }
    public void flipperQuotes(String quote){
        TextView textView=findViewById(R.id.textViewSlider);
        textView.setText(quote);

        viewFlipper.setFlipInterval(4000);
        viewFlipper.setAutoStart(true);

        viewFlipper.setInAnimation(this, android.R.anim.slide_in_left);
        viewFlipper.setOutAnimation(this, android.R.anim.slide_out_right);


    }
}
