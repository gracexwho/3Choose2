package com.example.a3choose2;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class DetectSwipeGestureListener extends GestureDetector.SimpleOnGestureListener{

    private static int MIN_SWIPE_DISTANCE_Y = 100;
    private static int MAX_SWIPE_DISTANCE_Y = 1000;

    private MainActivity activity = null;

    public MainActivity getActivity() {
        return activity;
    }

    public void setActivity(MainActivity activity) {
        this.activity = activity;
    }

    // detect up and down swipes
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        float deltaY = e1.getY() - e2.getY();
        float deltaYabs = Math.abs(deltaY);

        if (deltaYabs >= MIN_SWIPE_DISTANCE_Y && deltaYabs <= MAX_SWIPE_DISTANCE_Y) {
            if (deltaY > 0) {
                this.activity.finalScreen();
            }
        }

        return true;
    }
}
