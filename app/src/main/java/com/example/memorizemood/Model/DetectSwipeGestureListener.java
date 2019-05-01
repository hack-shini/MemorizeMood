package com.example.memorizemood.Model;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.Toast;


import com.example.memorizemood.Controller.MainActivity;


public abstract class DetectSwipeGestureListener extends GestureDetector.SimpleOnGestureListener {



    private static final String TAG = "MyActivity";

    // Minimal x and y axis swipe distance.
    private static int MIN_SWIPE_DISTANCE_Y = 100;

    // Maximal x and y axis swipe distance.
    private static int MAX_SWIPE_DISTANCE_Y = 1000;

    /* This method is invoked when a swipe gesture happened. */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {


        // Get swipe delta value in y axis.
        float deltaY = e1.getY() - e2.getY();

        // Get absolute value.
        float deltaYAbs = Math.abs(deltaY);


        if ((deltaYAbs >= MIN_SWIPE_DISTANCE_Y) && (deltaYAbs <= MAX_SWIPE_DISTANCE_Y)) {
            if (deltaY > 0) {
                this.onSwipeUp();
            } else {
                this.onSwipeDown();
            }
        }


        return true;
    }


    public abstract void onSwipeUp();

    public abstract void onSwipeDown();

}
