package com.example.memorizemood.Model;

import android.view.GestureDetector;
import android.view.MotionEvent;


public class DetectSwipeGestureListener extends GestureDetector.SimpleOnGestureListener {

    // Minimal x and y axis swipe distance.
    private static int MIN_SWIPE_DISTANCE_X = 100;
    private static int MIN_SWIPE_DISTANCE_Y = 100;

    // Maximal x and y axis swipe distance.
    private static int MAX_SWIPE_DISTANCE_X = 1000;
    private static int MAX_SWIPE_DISTANCE_Y = 1000;

    // Source activity that display message in text view.
    private DetectSwipeGestureListener activity = null;

    public DetectSwipeGestureListener getActivity() {
        return activity;
    }

    public void setActivity(DetectSwipeGestureListener activity) {
        this.activity = activity;
    }

    /* This method is invoked when a swipe gesture happened. */
    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

        // Get swipe delta value in x axis.
        float deltaX = e1.getX() - e2.getX();

        // Get swipe delta value in y axis.
        float deltaY = e1.getY() - e2.getY();

        // Get absolute value.
        float deltaXAbs = Math.abs(deltaX);
        float deltaYAbs = Math.abs(deltaY);

        // Only when swipe distance between minimal and maximal distance value then we treat it as effective swipe
        if ((deltaXAbs >= MIN_SWIPE_DISTANCE_X) && (deltaXAbs <= MAX_SWIPE_DISTANCE_X)) {
            if (deltaX > 0) {
                this.onSwipeLeft();
            } else {
                this.onSwipeRight();
            }
        }

        if ((deltaYAbs >= MIN_SWIPE_DISTANCE_Y) && (deltaYAbs <= MAX_SWIPE_DISTANCE_Y)) {
            if (deltaY > 0) {
                this.onSwipeUp();
            } else {
                this.onSwipeDown();
            }
        }


        return true;
    }

    public void onSwipeRight() {

    }

    public void onSwipeLeft() {

    }

    public void onSwipeUp() {

    }

    public void onSwipeDown() {

    }
}



//
//    private static final int SWIPE_THRESHOLD = 100;
//    private static final int SWIPE_VELOCITY_THRESHOLD = 100;
//
//    @Override
//    public boolean onDown(MotionEvent e) {
//        return true;
//    }
//
//
//
//    // ------- START SWIPE MANAGEMENT -------------
//
//    @Override
//    public boolean onTouch(View view, MotionEvent motionEvent) {
//        return gestureDetector.onTouchEvent(motionEvent);
//    }
//
//    // Determine the speed of scrolling and the action to be done
//    @Override
//    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//        boolean result = false;
//        try {
//            float diffY = e2.getY() - e1.getY();
//            float diffX = e2.getX() - e1.getX();
//            if (Math.abs(diffX) > Math.abs(diffY)) {
//                if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
//                    if (diffX > 0) {
//                        onSwipeRight();
//                        result = true;
//                    } else {
//                        onSwipeLeft();
//                        result = true;
//                    }
//                }
//            } else {
//                if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
//                    if (diffY > 0) {
//                        onSwipeDown();
//                        result = true;
//                    } else {
//                        onSwipeUp();
//                        result = true;
//                    }
//                }
//            }
//        } catch (Exception exception) {
//            exception.printStackTrace();
//        }
//        return result;
//    }
//
//
//
//



//}

// -------- END SWIPE MANAGMENT ------------------

