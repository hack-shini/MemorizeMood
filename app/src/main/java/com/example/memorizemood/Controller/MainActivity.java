package com.example.memorizemood.Controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.memorizemood.History_comment;
import com.example.memorizemood.R;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    private GestureDetector gestureDetector;
    private ImageButton addNote_imgBtn;
    private ImageButton history_imgBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNote_imgBtn = findViewById(R.id.add_note_imageBtn);
        history_imgBtn = findViewById(R.id.history_imageBtn);


        addNote_imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogComment();
            }
        });

        history_imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyComment =new Intent(MainActivity.this, History_comment.class);
                startActivity(historyComment);
            }
        });

        gestureDetector = new GestureDetector(this, new GestureListner());

    }

    public MainActivity(){

    }

    // ------------------- START   ADD COMMENT --------------------------
    public void alertDialogComment(){


        // Modification layout -> View
        LayoutInflater factory = LayoutInflater.from(this);
        final View alertDialogView = factory.inflate(R.layout.alert_dialog_comment_main, null);

        // Creation of alertDialog
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);

        // Assigning custom view to alertDialog
        adb.setView(alertDialogView);

        // Title of alertDialog
        adb.setTitle("Commentaire");

        // Modification icon alertDialog
        adb.setIcon(android.R.drawable.ic_dialog_info);

        // Affected button "OK" to alertDialog and new event
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // When we'll click on button "OK", we'll recover EditText corresponding to our custom view
                EditText editText = (EditText)alertDialogView.findViewById(R.id.chose_comment_editText);

            }
        });

        // When click on "Annuler" we go out of the alertDialog
        adb.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        adb.show();
    }


    // ------- END    ADD COMMENT ---------




    // ------- START SWIPE MANAGEMENT -------------

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }


    private final class GestureListner extends GestureDetector.SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
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

    // -------- END SWIPE MANAGMENT ------------------






}
