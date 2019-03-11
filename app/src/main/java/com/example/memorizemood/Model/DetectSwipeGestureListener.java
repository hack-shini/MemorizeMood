package com.example.memorizemood.Model;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.example.memorizemood.Controller.MainActivity;

public class DetectSwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

    private static final int SWIPE_THRESHOLD = 100;
    private static final int SWIPE_VELOCITY_THRESHOLD = 100;

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }



    // ------- START SWIPE MANAGEMENT -------------

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    // Determine the speed of scrolling and the action to be done
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        boolean result = false;
        try {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();
            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffX > 0) {
                        onSwipeRight();
                        result = true;
                    } else {
                        onSwipeLeft();
                        result = true;
                    }
                }
            } else {
                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                    if (diffY > 0) {
                        onSwipeDown();
                        result = true;
                    } else {
                        onSwipeUp();
                        result = true;
                    }
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return result;
    }

}


    public void onSwipeRight() {
        Toast.makeText(MainActivity.this,"swipe droite", Toast.LENGTH_LONG).show();
    }

    public void onSwipeLeft() {
        Toast.makeText(MainActivity.this,"swipe gauche", Toast.LENGTH_LONG).show();
    }

    public void onSwipeUp() {
        Toast.makeText(MainActivity.this,"swipe haut", Toast.LENGTH_LONG).show();
    }

    public void onSwipeDown() {
        Toast.makeText(MainActivity.this,"swipe bas", Toast.LENGTH_LONG).show();
    }
}

// -------- END SWIPE MANAGMENT ------------------
}
