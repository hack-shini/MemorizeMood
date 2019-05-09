package com.example.memorizemood.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.memorizemood.History_comment;
import com.example.memorizemood.Model.DetectSwipeGestureListener;
import com.example.memorizemood.Model.Mood;
import com.example.memorizemood.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity{

    private ImageButton addNote_imgBtn;
    private ImageButton history_imgBtn;
    private ImageView moozHappy;
    private GestureDetectorCompat gestureDetectorCompat;
    private Mood currentMood;
    private int indiceMoodPosition = 1;
    private Mood moods[];
    final Gson gson = new GsonBuilder().serializeNulls().create();




    @Override
    public boolean onTouchEvent(MotionEvent event){
        gestureDetectorCompat.onTouchEvent(event);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNote_imgBtn = findViewById(R.id.add_note_imageBtn);
        history_imgBtn = findViewById(R.id.history_imageBtn);
        moozHappy = findViewById(R.id.smiley_happy_imageView);

        addNote_imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogComment();
            }
        });

        history_imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyComment = new Intent(MainActivity.this, History_comment.class);
                startActivity(historyComment);
            }
        });


        gestureDetectorCompat = new GestureDetectorCompat(this, new DetectSwipeGestureListener() {
            @Override
            public void onSwipeUp() {
                handleSwipe(true);
            }

            @Override
            public void onSwipeDown() {
               handleSwipe(false);
            }


        });

        moods = new Mood [] {

                new Mood(R.color.banana_yellow, R.drawable.smiley_super_happy),
                new Mood(R.color.light_sage, R.drawable.smiley_happy),
                new Mood(R.color.cornflower_blue_65, R.drawable.smiley_normal),
                new Mood(R.color.warm_grey, R.drawable.smiley_disappointed),
                new Mood(R.color.faded_red, R.drawable.smiley_sad),
        };
    }

    private void handleSwipe(boolean isSwipeUp) {

        currentMood = moods[indiceMoodPosition];
        findViewById(R.id.idRelativeLayout).setBackgroundResource(currentMood.getBackgroundRes());
        moozHappy.setImageResource(currentMood.getSmileyRes());


        if (isSwipeUp) {
            if (indiceMoodPosition < moods.length - 1) {
                indiceMoodPosition++;
            }
        }else {
            if (indiceMoodPosition > 0){
                indiceMoodPosition--;
            }
        }

    }

    // ------------------- START   ADD COMMENT --------------------------
    public void alertDialogComment() {


        // Modification layout -> View
        LayoutInflater factory = LayoutInflater.from(this);
        final View alertDialogView = factory.inflate(R.layout.alert_dialog_comment_main, null);

        // Creation of alertDialog
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);

        // Assigning custom view to alertDialog
        adb.setView(alertDialogView);

        // Title of alertDialog
        adb.setTitle(R.string.comment);

        // Modification icon alertDialog
        adb.setIcon(android.R.drawable.ic_dialog_info);

        // Affected button "OK" to alertDialog and new event
        adb.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // When we'll click on button "OK", we'll recover EditText corresponding to our custom view
                EditText editText = (EditText) alertDialogView.findViewById(R.id.chose_comment_editText);

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



}
