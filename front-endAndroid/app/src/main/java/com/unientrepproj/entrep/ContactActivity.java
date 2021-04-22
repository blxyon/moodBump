package com.unientrepproj.entrep;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.unientrepproj.entrep.TabsClasses.QuestionsDiaryActivity;

import backend.MoodServerUtils;

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
        Button submitIp =findViewById(R.id.submitIp);
        submitIp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText ipAdd=findViewById(R.id.ipAddress);
                MoodServerUtils.host=ipAdd.getText().toString();
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
