package com.example.a3choose2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.widget.Button;
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

    private Bundle extras = new Bundle();

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

        if (total > 24) {
            // "Don't be greedy, you'll never be able to do so many things in one day."
        }
        if (school_hours < 6) {
            // "Taking 15 credits means you should be studying 6 hours a day."
            int missing_school = 6 - school_hours;
            missing_school = (int) (missing_school*days);
            int bad_mark = 100 - (missing_school/(105*6)*100);
            // new mark out of 100

            if (bad_mark >= 85) {
                // "No decrease
            } else if (bad_mark < 85 && bad_mark >= 80) {
                // "3.7, SO DECREASE BY 0.3 GPA points
            } else if (bad_mark < 80 && bad_mark >= 75) {
                // 3.3, so decrease is 0.7
            } else if (bad_mark <75 && bad_mark >= 70) {
                // 3.0, so decrease is 1.0
            } else if (bad_mark <70 && bad_mark >= 65) {
                // 2.7, so decrease is 1.3
            } else if (bad_mark <65 && bad_mark >=60) {
                // 2.3, so decrease is 1.7
            } else {
                // decrease is 4.0, you get a 0
            }

        }


    }


}
