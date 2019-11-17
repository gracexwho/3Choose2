package com.example.a3choose2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class SetSleepHours extends AppCompatActivity implements View.OnClickListener {
    private ArrayList<String> priorities = new ArrayList<String>();
    Bundle extras = new Bundle();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_sleep_hours);

        Intent prev = getIntent();
        extras = prev.getExtras();
        priorities = extras.getStringArrayList("priorities");

        // now get next activity

        Button next_button = findViewById(R.id.next_sleep);
        next_button.setOnClickListener(this);

    }

    public void onClick(View v) {
        int curr = extras.getInt("curr");
        curr = curr + 1;
        EditText editText = (EditText) findViewById(R.id.sleep_hours_int);
        int sleep_hours= Integer.parseInt(editText.getText().toString());
        extras.putInt("curr", curr);
        extras.putInt("sleep_hours", sleep_hours);

        if (curr >= 3) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtras(extras);
            startActivity(intent);

        } else {
         //Toast toast = Toast.makeText(this, priorities.get(curr), Toast.LENGTH_SHORT);
         //toast.show();

            if (priorities.get(curr).contentEquals("School")) {
                Intent intent = new Intent(this, SetSchoolHours.class);
                intent.putExtras(extras);
                startActivity(intent);

            } else {
                Intent intent = new Intent(this, SetSocialHours.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        }
    }
}
