package com.example.a3choose2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Bundle bundle = getIntent().getExtras();
        Intent load_screen = getIntent();

        ArrayList<String> order = load_screen.getStringArrayListExtra("priorities");

       // Toast.makeText(this, order.toString(), Toast.LENGTH_LONG).show();
    }


}
