package com.example.a3choose2;

import android.annotation.SuppressLint;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.VERSION_CODES.JELLY_BEAN;
import static android.provider.AlarmClock.EXTRA_MESSAGE;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */



public class LaunchScreen extends AppCompatActivity implements View.OnClickListener {



    private int clicked = 0;
    final ArrayList<String> priorities = new ArrayList<String>();
    final ArrayList<Intent> order_intents = new ArrayList<Intent>();
    /**
     * Whether or not the system UI should be auto-hidden after
     * {@link #AUTO_HIDE_DELAY_MILLIS} milliseconds.
     */
    private static final boolean AUTO_HIDE = true;

    /**
     * If {@link #AUTO_HIDE} is set, the number of milliseconds to wait after
     * user interaction before hiding the system UI.
     */
    private static final int AUTO_HIDE_DELAY_MILLIS = 3000;

    /**
     * Some older devices needs a small delay between UI widget updates
     * and a change of the status and navigation bar.
     */
    private static final int UI_ANIMATION_DELAY = 300;
    private final Handler mHideHandler = new Handler();
    private View mContentView;
    private final Runnable mHidePart2Runnable = new Runnable() {
        @SuppressLint("InlinedApi")
        @Override
        public void run() {
            // Delayed removal of status and navigation bar

            // Note that some of these constants are new as of API 16 (Jelly Bean)
            // and API 19 (KitKat). It is safe to use them, as they are inlined
            // at compile-time and do nothing on earlier devices.
            mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
        }
    };
    private View mControlsView;
    private final Runnable mShowPart2Runnable = new Runnable() {
        @Override
        public void run() {
            // Delayed display of UI elements
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.show();
            }
            mControlsView.setVisibility(View.VISIBLE);
        }
    };
    private boolean mVisible;
    private final Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            hide();
        }
    };
    /**
     * Touch listener to use for in-layout UI controls to delay hiding the
     * system UI. This is to prevent the jarring behavior of controls going away
     * while interacting with activity UI.
     */
    private final View.OnTouchListener mDelayHideTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (AUTO_HIDE) {
                delayedHide(AUTO_HIDE_DELAY_MILLIS);
            }
            return false;
        }
    };





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launch_screen);

        mVisible = true;
        mControlsView = findViewById(R.id.fullscreen_content_controls);
        mContentView = findViewById(R.id.fullscreen_content);


        // Set up the user interaction to manually show or hide the system UI.
        mContentView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                toggle();
            }
        });

        // Upon interacting with UI controls, delay any scheduled hide()
        // operations to prevent the jarring behavior of controls going away
        // while interacting with the UI.
        //findViewById(R.id.dummy_button).setOnTouchListener(mDelayHideTouchListener);


        Button button1 = findViewById(R.id.school_button);
        Button button2 = findViewById(R.id.social_button);
        Button button3 = findViewById(R.id.sleep_button);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);




    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        // Trigger the initial hide() shortly after the activity has been
        // created, to briefly hint to the user that UI controls
        // are available.
        delayedHide(100);
    }

    private void toggle() {
        if (mVisible) {
            hide();
        } else {
            show();
        }
    }

    private void hide() {
        // Hide UI first
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        mControlsView.setVisibility(View.GONE);
        mVisible = false;

        // Schedule a runnable to remove the status and navigation bar after a delay
        mHideHandler.removeCallbacks(mShowPart2Runnable);
        mHideHandler.postDelayed(mHidePart2Runnable, UI_ANIMATION_DELAY);
    }

    @SuppressLint("InlinedApi")
    private void show() {
        // Show the system bar
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        mVisible = true;

        // Schedule a runnable to display UI elements after a delay
        mHideHandler.removeCallbacks(mHidePart2Runnable);
        mHideHandler.postDelayed(mShowPart2Runnable, UI_ANIMATION_DELAY);
    }

    /**
     * Schedules a call to hide() in delay milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedHide(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.school_button:
                // now navigate to next page
                //Toast.makeText(this, "School clicked",Toast.LENGTH_SHORT).show();
                v.setVisibility(View.GONE);
                //startActivity(intent1);
                clicked += 1;
                priorities.add("School");
                order_intents.add(new Intent(this, SetSchoolHours.class));

                if (clicked == 3) {
                    // then all buttons have been clicked
                    //Bundle bundle = new Bundle();
                    //bundle.putParcelableArrayList("order_intents", order_intents);
                    Intent intent = order_intents.get(0);
                    intent.putExtra("priorities", priorities);
                    intent.putExtra("curr", 0);
                    startActivity(intent);
                } else {
                    break;
                }
            case R.id.social_button:
                // now navigate to next page
                //Toast.makeText(this, "Social clicked",Toast.LENGTH_SHORT).show();
                v.setVisibility(View.GONE);
                priorities.add("Social");
                clicked += 1;
                order_intents.add(new Intent(this, SetSocialHours.class));
                if (clicked == 3) {
                    // then all buttons have been clicked
                    //Bundle bundle = new Bundle();
                    //bundle.putParcelableArrayList("order_intents", order_intents);
                    Intent intent = order_intents.get(0);
                    intent.putExtra("priorities", priorities);
                    intent.putExtra("curr", 0);
                    startActivity(intent);
                } else {
                    break;
                }
            case R.id.sleep_button:
                // now navigate to next page
                //Toast.makeText(this, "Sleep clicked", Toast.LENGTH_SHORT).show();
                v.setVisibility(View.GONE);
                priorities.add("Sleep");
                order_intents.add(new Intent(this, SetSleepHours.class));
                clicked += 1;

                if (clicked == 3) {
                    // then all buttons have been clicked
                    //Bundle bundle = new Bundle();
                    //bundle.putParcelableArrayList("order_intents", order_intents);
                    Intent intent = order_intents.get(0);
                    intent.putExtra("priorities", priorities);
                    intent.putExtra("curr", 0);
                    startActivity(intent);
                } else {
                    break;
                }
        }
    }
}
