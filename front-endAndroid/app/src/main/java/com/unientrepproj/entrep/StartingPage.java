package com.unientrepproj.entrep;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.unientrepproj.entrep.TabsClasses.QuestionsDiaryActivity;

public class StartingPage extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_page);

        Button button =findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toQuestion();
            }
        });
    }
    public void toQuestion(){

        startActivity(new Intent(this, QuestionsDiaryActivity.class));
    }
}
