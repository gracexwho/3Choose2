package com.example.a3choose2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    // display results
    private Bundle extras = new Bundle();

    private GestureDetectorCompat gestureDetectorCompat = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Bundle bundle = getIntent().getExtras();
        Intent info = getIntent();

        extras = info.getExtras();


        // here are all the hours the user entered
        int sleep_hours = extras.getInt("sleep_hours");
        int social_hours = extras.getInt("social_hours");
        int school_hours = extras.getInt("school_hours");
        int total = sleep_hours + social_hours + school_hours;

        String datestr = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date curr_date = new Date();
        Date last_date = new Date();

        try {
            curr_date = sdf.parse(datestr);
            last_date = sdf.parse("2019-12-04");
        } catch (ParseException e) {
            e.printStackTrace();
        }


        long diff = last_date.getTime() - curr_date.getTime();
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        int bad_mark = 0;
        double decrease = 0.0;

        if (total > 24) {
            Toast.makeText(this, "Don't be greedy", Toast.LENGTH_SHORT).show();
        }

        if (school_hours < 6) {
            // "Taking 15 credits means you should be studying 6 hours a day."
            //double missing_school = 6 - school_hours;
            //missing_school = (missing_school*days);

            //Toast toast = Toast.makeText(this, String.valueOf(missing_school), Toast.LENGTH_SHORT);
            //toast.show();
            bad_mark = (int)((school_hours*days/(days*6))*100);
            // new mark out of 100

            if (bad_mark >= 85) {
                decrease = 0.0;
            } else if (bad_mark < 85 && bad_mark >= 80) {
                // "3.7, SO DECREASE BY 0.3 GPA points
                decrease = 0.3;
            } else if (bad_mark < 80 && bad_mark >= 75) {
                // 3.3, so decrease is 0.7
                decrease = 0.7;
            } else if (bad_mark <75 && bad_mark >= 70) {
                // 3.0, so decrease is 1.0
                decrease = 1.0;
            } else if (bad_mark <70 && bad_mark >= 65) {
                // 2.7, so decrease is 1.3
                decrease = 1.3;
            } else if (bad_mark <65 && bad_mark >=60) {
                // 2.3, so decrease is 1.7
                decrease = 1.7;
            } else {
                // decrease is 4.0, you get a 0
                decrease = 4.0;
            }

        }

        // this is how to dynamically update the text in the "CardView" boxes!!! @SHUA
        TextView school_message = (TextView)findViewById(R.id.school_message);
        if (school_hours>12){
            school_message.setText("You are studying way too much. \nPlease make sure you do take breaks!");
        }else if (decrease==0) {
            school_message.setText("Great :) \nyou should be able to maintain \nor even get better GPA!");
        }else {
            school_message.setText("Oh no... \nEstimated Term GPA decrease: -" + decrease);
        }

        TextView social_message = (TextView)findViewById(R.id.social_message);
        if (sleep_hours<8 && decrease!=0) { //not sleeping enough and getting bad GPA
            social_message.setText("You might want to decrease social hours to achieve an optimal balance!");
        }else if (social_hours==0){ //not having any social life
            social_message.setText("You should have some social hours, \nplease spare some time to talk to people you care about!");
        }else{
            social_message.setText("Great :) \nKeep up this balance!");
        }

        TextView sleep_message = (TextView)findViewById(R.id.sleep_message);
        if (sleep_hours<8) {
            sleep_message.setText("You should definitely sleep more!");
        }else if (sleep_hours>10){
            sleep_message.setText("You are probably sleeping too much...");
        }else{
            sleep_message.setText("Great :) \nYou have a good amount of sleep!");
        }

        DetectSwipeGestureListener gestureListener = new DetectSwipeGestureListener();
        gestureListener.setActivity(this);
        gestureDetectorCompat = new GestureDetectorCompat(this, gestureListener);


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetectorCompat.onTouchEvent(event);
        return true;
    }

    public void finalScreen() {
        // go to next screen actually and rename this
        Intent intent = new Intent(this, ExitScreen.class);
        startActivity(intent);
    }






}
