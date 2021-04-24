package com.unientrepproj.entrep.TabsClasses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.unientrepproj.entrep.CalendarActivity;
import com.unientrepproj.entrep.ContactActivity;
import com.unientrepproj.entrep.R;
import com.unientrepproj.entrep.StartingPage;
import com.unientrepproj.entrep.imageModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class QuestionsDiaryActivity extends AppCompatActivity {

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

        ImageButton calendarButon=findViewById(R.id.calendar);
        calendarButon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCalendar();
            }
        });
        ImageButton homeButton=findViewById(R.id.home);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHome();
            }
        });

        ImageButton contactsBut=findViewById(R.id.contacts);
        contactsBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toContacts();
            }
        });

        Thread t = new Thread() {
            @Override
            public void run() {
                try {
                    while (!isInterrupted()) {
                        Thread.sleep(1000);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView day = (TextView) findViewById(R.id.day);
                                TextView monthYear= findViewById(R.id.monthYear);
                                TextView time=findViewById(R.id.time);
                                TextView dayInWeek=findViewById(R.id.dayWeek);


                                long date = System.currentTimeMillis();


                                SimpleDateFormat sdf1 = new SimpleDateFormat("hh:mm a");
                                SimpleDateFormat sdf2 = new SimpleDateFormat("dd");
                                SimpleDateFormat sdf3 = new SimpleDateFormat("MMM yyyy");
                                SimpleDateFormat sdf4 = new SimpleDateFormat("EE");

                                day.setText(sdf2.format(date));
                                monthYear.setText(sdf3.format(date));
                                time.setText(sdf1.format(date));
                                dayInWeek.setText(sdf4.format(date));
                            }
                        });
                    }
                } catch (InterruptedException e) {
                }
            }
        };
        t.start();

    }
    public void toCalendar(){
        startActivity(new Intent(this, CalendarActivity.class));
    }
    public void toHome(){
        startActivity(new Intent(this, StartingPage.class));
    }
    public void toContacts(){
        startActivity(new Intent(this, ContactActivity.class));
    }
}
