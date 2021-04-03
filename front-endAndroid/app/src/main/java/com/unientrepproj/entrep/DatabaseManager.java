package com.unientrepproj.entrep;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
import java.util.TimeZone;

public class DatabaseManager extends SQLiteOpenHelper {
    Context context;
    public DatabaseManager(Context context) {
        super(context, "userName.db", null, 2);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("drop Table if exists User");
        createDB();
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldV, int newV) {
        if(oldV<newV){
            dropDB();
            onCreate(DB);
        }

    }
    public void createDB(){
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.execSQL("create Table IF NOT EXISTS User(name TEXT primary key)");
    }
    public void dropDB(){
        SQLiteDatabase DB = this.getWritableDatabase();
        DB.execSQL("drop Table if exists User");
    }

    public Boolean insertUser(String name) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        long result=DB.insert("User", null, contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public Cursor getData ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery(String.format("Select * from User"), null);
        return cursor;

    }


}
