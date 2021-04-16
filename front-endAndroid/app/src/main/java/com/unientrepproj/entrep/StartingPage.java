package com.unientrepproj.entrep;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.unientrepproj.entrep.TabsClasses.QuestionsDiaryActivity;

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

        DatabaseManager db=new DatabaseManager(this);
        Cursor cursor=db.getData();
        cursor.moveToFirst();
        String name =cursor.getString(0);
        TextView nameTex=findViewById(R.id.nameTex);
        nameTex.setText(name);
    }
    public void toQuestion(){
        startActivity(new Intent(this, QuestionsDiaryActivity.class));
    }
    public void toCalendar(){
        startActivity(new Intent(this, CalendarActivity.class));
    }
}
