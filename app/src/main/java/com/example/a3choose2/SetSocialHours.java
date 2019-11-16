package com.example.a3choose2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SetSocialHours extends AppCompatActivity  implements View.OnClickListener{
    private ArrayList<String> priorities = new ArrayList<String>();
    private int curr = 0;
    Bundle extras = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_social_hours);

        Intent prev = getIntent();
        extras = prev.getExtras();
        priorities = extras.getStringArrayList("priorities");
        curr = extras.getInt("curr", 0);

        // now get next activity

        Button next_button = findViewById(R.id.next_social);
        next_button.setOnClickListener(this);



    }

    public void onClick(View v) {
        curr = curr + 1;
        EditText editText = (EditText) findViewById(R.id.social_hours_int);
        int social_hours= Integer.parseInt(editText.getText().toString());

        if (curr == 3) {

        } else {
            Toast toast = Toast.makeText(this, priorities.get(curr), Toast.LENGTH_SHORT);
            toast.show();
            if (priorities.get(curr) == "School") {
                Intent intent = new Intent(this, SetSchoolHours.class);
                extras.putInt("curr", curr);
                extras.putInt("sleep_hours", school_hours);
                intent.putExtras(extras);
                startActivity(intent);

            } else {
                // it's sleep
                Intent intent = new Intent(this, SetSleepHours.class);
                extras.putInt("curr", curr);
                extras.putInt("sleep_hours", school_hours);
                intent.putExtras(extras);
                startActivity(intent);
            }
        }
    }



}
