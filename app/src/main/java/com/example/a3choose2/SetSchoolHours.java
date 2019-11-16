package com.example.a3choose2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class SetSchoolHours extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<String> priorities = new ArrayList<String>();
    private int curr = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_school_hours);

        Intent prev = getIntent();
        priorities = prev.getStringArrayListExtra("priorities");
        curr = prev.getIntExtra("curr", 0);


        // now get next activity

        Button next_button = findViewById(R.id.next_school);
        next_button.setOnClickListener(this);


    }

    public void onClick(View v) {
        curr = curr + 1;
        Toast toast = Toast.makeText(this, priorities.get(curr), Toast.LENGTH_SHORT);
        toast.show();
        if (curr == 3) {

        } else {

            if (priorities.get(curr) == "Social") {
                Intent intent = new Intent(this, SetSocialHours.class);
                intent.putExtra("curr", curr);
                intent.putExtra("priorities", priorities);
                startActivity(intent);

            } else {
                // it's sleep
                Intent intent = new Intent(this, SetSleepHours.class);
                intent.putExtra("curr", curr);
                intent.putExtra("priorities", priorities);
                startActivity(intent);
            }

        }
    }
}