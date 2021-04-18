package com.unientrepproj.entrep;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.unientrepproj.entrep.TabsClasses.QuestionsDiaryActivity;

public class ContactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_activity);

        ImageButton homeButton=findViewById(R.id.home);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toHome();
            }
        });
        ImageButton calendar=findViewById(R.id.calendar);
        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toCalendar();
            }
        });
    }
    public void toHome(){
        startActivity(new Intent(this, StartingPage.class));
    }
    public void toCalendar(){
        startActivity(new Intent(this, CalendarActivity.class));
    }
}
