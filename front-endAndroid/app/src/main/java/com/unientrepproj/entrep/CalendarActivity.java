package com.unientrepproj.entrep;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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


    List<String> greenDateList = Arrays.asList(
            "2019-01-01",
            "2019-01-03", "2019-01-04", "2019-01-05", "2019-01-06");
    List<String> grayDateList = Arrays.asList(
            "2019-01-09", "2019-01-10", "2019-01-11",
            "2019-01-24", "2019-01-25", "2019-01-26", "2019-01-27", "2019-01-28", "2019-01-29");
    List<String> redDateList = Arrays.asList(
            "2021-01-09", "2021-01-10", "2021-01-11",
            "2021-01-24", "2021-01-25", "2021-01-26", "2021-01-27", "2021-01-28", "2021-01-29");
    List<String> reddish=new ArrayList<>();
    List<String> greenish=new ArrayList<>();
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


        TextView statsText=findViewById(R.id.statsText);
        statsText.setText("This month there has been a 10% increase in your positive mood!");



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
            if(je.getMood()==0){
                grayDateList.add(sdf.format(je.getDate()));
            }else if(je.getMood()==1){
                greenDateList.add(sdf.format(je.getDate()));
            }else if(je.getMood()==-1){
                redDateList.add(sdf.format(je.getDate()));
            }else if(je.getMood()==0.5){
                greenish.add(sdf.format(je.getDate()));
            }else if(je.getMood()==-0.5){
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
                datesCenter.add(CalendarDay.from(localDate.getYear(),localDate.getMonthValue(),localDate.getDayOfMonth()));
            } else if (left) {
                datesLeft.add(CalendarDay.from(localDate.getYear(),localDate.getMonthValue(),localDate.getDayOfMonth()));
            } else if (right) {
                datesRight.add(CalendarDay.from(localDate.getYear(),localDate.getMonthValue(),localDate.getDayOfMonth()));
            } else {
                datesIndependent.add(CalendarDay.from(localDate.getYear(),localDate.getMonthValue(),localDate.getDayOfMonth()));
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
            return LocalDate.of(cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH) + 1,
                    cal.get(Calendar.DAY_OF_MONTH));


        } catch (NullPointerException e) {
            return null;
        } catch (ParseException e) {
            return null;
        }
    }
}
