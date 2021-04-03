package com.unientrepproj.entrep;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    DatabaseManager DB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        DB = new DatabaseManager(this);
        DB.dropDB();
        DB.createDB();
        Cursor c = DB.getData();
        c.moveToFirst();
        if (c.getCount() == 1) {
            setContentView(R.layout.starting_page);
        } else {
            setContentView(R.layout.activity_main);
            Button button=findViewById(R.id.button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    brnToSrcond();
                }
            });
        }
    }
    public void brnToSrcond(){
        TextView v=findViewById(R.id.editTextTextPersonName);
        String name=v.getText().toString();
        DB.insertUser(name);
        startActivity(new Intent(this,StartingPage.class));
    }
}