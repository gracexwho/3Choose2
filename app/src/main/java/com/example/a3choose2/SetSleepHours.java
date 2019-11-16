package com.example.a3choose2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class SetSleepHours extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_sleep_hours);

        Intent prev = getIntent();
        ArrayList<String> priorities = prev.getStringArrayListExtra("priorities");
        int curr = prev.getIntExtra("curr", 0);


        // now get next activity
        String next = priorities.get(curr);
        Toast.makeText(this, next, Toast.LENGTH_LONG);

    }
}
