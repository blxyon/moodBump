package com.unientrepproj.entrep;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.CalendarMode;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import backend.DBHelper;
import backend.journalEntry;


import static android.provider.Settings.System.DATE_FORMAT;


public class CalendarActivity extends AppCompatActivity {


    //dummy initial representation
    List<String> greenDateList =Arrays.asList(
            "2021-04-09", "2021-04-08", "2021-04-06","2021-04-14","2021-04-07");
    List<String> grayDateList = Arrays.asList(
            "2021-04-12","2021-04-13");
    List<String> redDateList = Arrays.asList(
            "2021-04-09", "2021-04-10", "2021-04-11",
            "2021-04-02", "2021-04-03", "2021-04-04");
    List<String> reddish=Arrays.asList(
            "2021-04-05","2021-04-17","2021-04-18","2021-04-19","2021-04-20","2021-03-31","2021-03-30","2021-03-29","2021-03-28","2021-03-27");
    List<String> greenish=Arrays.asList(
            "2021-04-01","2021-04-16","2021-04-15");
    final String DATE_FORMAT = "yyyy-MM-dd";
    final CalendarDay min=CalendarDay.from(1900,1,1);
    final CalendarDay max=CalendarDay.from(2100,12,12);
    int green = 0;
    int gray = 1;
    int red=2;
    int greenis=3;
    int redis=4;

    MaterialCalendarView calendarView;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_activity);
        getFromDB();
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

        TextView statsText=findViewById(R.id.statsText);
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date startofThis = cal.getTime();
        cal.add(Calendar.MONTH, -1);
        Date startofLast = cal.getTime();
        cal.add(Calendar.MONTH, 2);
        Date startofNext = cal.getTime();
        DBHelper db = new DBHelper(this);
        ArrayList<journalEntry> lastMonth = db.getEntryByRange(startofLast, startofThis);
        ArrayList<journalEntry> thisMonth = db.getEntryByRange(startofThis, startofNext);
        float totalLast = 0;
        float totalThis = 0;
        for (journalEntry je : lastMonth) {totalLast += je.getMood();};
        for (journalEntry je : thisMonth) {totalThis += je.getMood();};
        float averageLast = totalLast/lastMonth.size();
        float averageThis = totalThis/thisMonth.size();
        int diff = 0;
        String stats = "";
        Log.i("mood last", String.valueOf(averageLast));
        Log.i("mood this", String.valueOf(averageThis));
        if (averageLast != 0 && averageLast < averageThis) {
           diff = Math.abs((int)(100*(averageThis - averageLast)/averageLast));
           Log.i("mood", String.valueOf(diff));
            stats = String.format("This month your mood has been %d%% better than last month.", diff);
        }
        //String stats = "This month your mood has been 13% better than last month.";
        statsText.setText(stats);



        calendarView=findViewById(R.id.calendarView);
        calendarView.setShowOtherDates(MaterialCalendarView.SHOW_ALL);



        calendarView.state().edit()
                .setMinimumDate(min)
                .setMaximumDate(max)
                .commit();


        setEvent(greenDateList, green);
        setEvent(grayDateList, gray);
        setEvent(redDateList,red);
        setEvent(greenish,greenis);
        setEvent(reddish,redis);

        calendarView.invalidateDecorators();
    }

    public void toHome(){
        startActivity(new Intent(this, StartingPage.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void getFromDB(){
        greenDateList=new ArrayList<>();
        redDateList=new ArrayList<>();
        grayDateList=new ArrayList<>();
        greenish=new ArrayList<>();
        reddish=new ArrayList<>();

        DBHelper db=new DBHelper(this);
        ArrayList<journalEntry> jes=db.getAllEntries();
        SimpleDateFormat sdf=new SimpleDateFormat(DATE_FORMAT);
        for (journalEntry je:jes){
            float mood = je.getMood();
            Log.i("mood", Float.toString(mood));
            if(mood < 0.2 && mood >= -0.2){
                grayDateList.add(sdf.format(je.getDate()));
            }else if(mood >= 0.5){
                greenDateList.add(sdf.format(je.getDate()));
            }else if(mood < -0.5){
                Log.i("mood", "red date");
                redDateList.add(sdf.format(je.getDate()));
            }else if(mood < 0.5 && mood >= 0.2){
                greenish.add(sdf.format(je.getDate()));
            }else if(mood < -0.2 && mood >= -0.5){
                reddish.add(sdf.format(je.getDate()));
            }
        }

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    void setEvent(List<String> dateList, int color) {
        List<LocalDate> localDateList = new ArrayList<>();

        for (String string : dateList) {
            LocalDate calendar = getLocalDate(string);
            if (calendar != null) {
                localDateList.add(calendar);
            }
        }


        List<CalendarDay> datesLeft = new ArrayList<>();
        List<CalendarDay> datesCenter = new ArrayList<>();
        List<CalendarDay> datesRight = new ArrayList<>();
        List<CalendarDay> datesIndependent = new ArrayList<>();


        for (LocalDate localDate : localDateList) {

            boolean right = false;
            boolean left = false;

            for (LocalDate day1 : localDateList) {


                if (localDate.isEqual(day1.plusDays(1))) {
                    left = true;
                }
                if (day1.isEqual(localDate.plusDays(1))) {
                    right = true;
                }
            }

            if (left && right) {
                datesCenter.add(CalendarDay.from(localDate.getYear(),localDate.getMonthValue()-1,localDate.getDayOfMonth()));
            } else if (left) {
                datesLeft.add(CalendarDay.from(localDate.getYear(),localDate.getMonthValue()-1,localDate.getDayOfMonth()));
            } else if (right) {
                datesRight.add(CalendarDay.from(localDate.getYear(),localDate.getMonthValue()-1,localDate.getDayOfMonth()));
            } else {
                datesIndependent.add(CalendarDay.from(localDate.getYear(),localDate.getMonthValue()-1,localDate.getDayOfMonth()));
            }
        }

        if (color == green) {
            setDecor(datesCenter, R.drawable.p_center);
            setDecor(datesLeft, R.drawable.p_left);
            setDecor(datesRight, R.drawable.p_right);
            setDecor(datesIndependent, R.drawable.p_independent);
        } else if(color == gray) {
            setDecor(datesCenter, R.drawable.g_center);
            setDecor(datesLeft, R.drawable.g_left);
            setDecor(datesRight, R.drawable.g_right);
            setDecor(datesIndependent, R.drawable.g_independent);
        }else if(color == red){
            setDecor(datesCenter, R.drawable.r_center);
            setDecor(datesLeft, R.drawable.r_left);
            setDecor(datesRight, R.drawable.r_right);
            setDecor(datesIndependent, R.drawable.r_independent);
        }else if(color == redis){
            setDecor(datesCenter, R.drawable.redish_center);
            setDecor(datesLeft, R.drawable.redish_left);
            setDecor(datesRight, R.drawable.redish_right);
            setDecor(datesIndependent, R.drawable.redish_independent);
        }else{
            setDecor(datesCenter, R.drawable.greenish_center);
            setDecor(datesLeft, R.drawable.greenish_left);
            setDecor(datesRight, R.drawable.greenish_right);
            setDecor(datesIndependent, R.drawable.greenish_independent);
        }
    }

    void setDecor(List<CalendarDay> calendarDayList, int drawable) {
        calendarView.addDecorators(new EventDecorator(this
                , drawable
                , calendarDayList));
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    LocalDate getLocalDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        try {
            Date input = sdf.parse(date);
            Calendar cal = Calendar.getInstance();
            cal.setTime(input);
            Log.i("month", Integer.toString(cal.get(Calendar.MONTH)));
            return LocalDate.of(cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH)+1,
                    cal.get(Calendar.DAY_OF_MONTH));


        } catch (NullPointerException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }
    }
    public void toContacts(){
        startActivity(new Intent(this, ContactActivity.class));
    }
}
