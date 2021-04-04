package com.unientrepproj.entrep.TabsClasses;

import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.unientrepproj.entrep.R;

import java.util.ArrayList;

public class QuestionsDiaryAcrivity extends AppCompatActivity {

    GridView gridView;
    ArrayList<imageModel> arrayList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quest_diary_tabs);

        ViewPager vp=findViewById(R.id.viewPager);
        PagerAdapterTabs pA= new PagerAdapterTabs(getSupportFragmentManager());
        vp.setAdapter(pA);

        TabLayout tL=findViewById(R.id.slidingTabs);
        tL.setupWithViewPager(vp);


    }
}
