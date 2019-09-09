package com.example.memorizemood.Controller;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.memorizemood.Model.DetectSwipeGestureListener;
import com.example.memorizemood.Model.GsonConverter;
import com.example.memorizemood.Model.Mood;
import com.example.memorizemood.Model.MoodHistory;
import com.example.memorizemood.R;
import com.example.memorizemood.utils.Utils;

import java.util.ArrayList;

import static com.example.memorizemood.utils.Keys.BOARD_MOOD_HISTORY;
import static com.example.memorizemood.utils.Keys.LAST_MOOD_KEY;

public class MainActivity extends AppCompatActivity{

    public static final int MAX_MOODS = 7;
    private ImageView moozHappy;

    private GestureDetectorCompat gestureDetectorCompat;
    private int indiceMoodPosition = 1;
    private String lastComment;
    private ArrayList<MoodHistory> boardOfMoodHistory;

    MediaPlayer mp;

    // Variable for sharedPreference
    SharedPreferences sharedPreferences;

    @Override
    public boolean onTouchEvent(MotionEvent event){
        gestureDetectorCompat.onTouchEvent(event);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton addNoteImgBtn = findViewById(R.id.add_note_imageBtn);
        ImageButton historyImgBtn = findViewById(R.id.history_imageBtn);
        moozHappy = findViewById(R.id.smiley_happy_imageView);
        moozHappy.setImageLevel(0);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Add comment management
        addNoteImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogComment();
            }
        });

        historyImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent historyComment = new Intent(MainActivity.this, HistoryActivity.class);
                startActivity(historyComment);
            }
        });



        // --- START --- Methods for swipeUp and swipeDown

        gestureDetectorCompat = new GestureDetectorCompat(this, new DetectSwipeGestureListener() {

            @Override
            public void onSwipeUp() {
                handleSwipe(true);
                mediaPlayer();
            }

            @Override
            public void onSwipeDown() {
               handleSwipe(false);
               mediaPlayer2();
            }

        });
        // --- END --- Methods for swipeUp and swipeDown

        boardOfMoodHistory = new ArrayList<>();
    }

    // Method for change background color and mood with swipe up/down
    private void handleSwipe(boolean isSwipeUp) {
        if (isSwipeUp) {
            if (indiceMoodPosition > 0) {
                indiceMoodPosition--;
            }
        }else {
            if (indiceMoodPosition < Utils.moods.length -1){
                indiceMoodPosition++;
            }
        }

        Mood currentMood = Utils.moods[indiceMoodPosition];
        findViewById(R.id.idRelativeLayout).setBackgroundResource(currentMood.getBackgroundRes());
        moozHappy.setImageResource(currentMood.getSmileyRes());
    }


    // --- START --- Methods for sound managment

    private void mediaPlayer2() {
        mp = new MediaPlayer();
        Context context = getApplicationContext();
        mp = MediaPlayer.create(context, R.raw.bipswipedown);
        mp.start();
    }

    private void mediaPlayer(){
        mp = new MediaPlayer();
        Context context = getApplicationContext();
        mp = MediaPlayer.create(context, R.raw.bipswipeup);
        mp.start();
    }
    // --- END --- Methods for sound managment


    @Override
    protected void onResume() {
        super.onResume();

        String json = sharedPreferences.getString(LAST_MOOD_KEY, null);

        // --- START --- Mangament for add mood in boardOfMoods

        if (json != null) {
            GsonConverter gsonConverter = new GsonConverter();
            MoodHistory moodHistory = gsonConverter.deserializeFromJsonToSingleObject(json);

            if (!Utils.isMoodFromToday(moodHistory)) {
                String jsonBoardOfMoods = sharedPreferences.getString(BOARD_MOOD_HISTORY, null);
                if (jsonBoardOfMoods != null) {
                    boardOfMoodHistory = gsonConverter.deserializeJsonToArrayList(jsonBoardOfMoods);
                }

                if (boardOfMoodHistory.size() < MAX_MOODS){
                    boardOfMoodHistory.add(0, moodHistory);

                }else if (boardOfMoodHistory.size() == MAX_MOODS) {
                    boardOfMoodHistory.remove(0);
                    boardOfMoodHistory.add(0, moodHistory);
                }

                String toJsonArray = gsonConverter.serializeListToJson(boardOfMoodHistory);
                sharedPreferences.edit()
                        .putString(BOARD_MOOD_HISTORY, toJsonArray)
                        .putString(LAST_MOOD_KEY, null)
                        .apply();
            }
        }

        // --- END --- Mangament for add mood in boardOfMoods
    }

    
    @Override
    protected void onPause() {
        GsonConverter gsonConverter = new GsonConverter();
        MoodHistory moodHistory = new MoodHistory(indiceMoodPosition, lastComment);

        String jsonMoodHistory = gsonConverter.serializeSingleObjectToJson(moodHistory);
        sharedPreferences.edit().putString(LAST_MOOD_KEY, jsonMoodHistory).apply();
        super.onPause();
    }

    // ------------------- START   ADD COMMENT --------------------------
    public void alertDialogComment() {


        // Modification layout -> View
        LayoutInflater factory = LayoutInflater.from(this);
        final View alertDialogView = factory.inflate(R.layout.alert_dialog_comment_main, null);

        // Creation of alertDialog
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);

        // Assigning custom view to alertDialog, title and icon
        adb.setView(alertDialogView).setTitle(R.string.comment).setIcon(android.R.drawable.ic_dialog_info);

        // Affected button "OK" to alertDialog and new event
        adb.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                // When we'll click on button "OK", we'll recover EditText corresponding to our custom view
                EditText editText = alertDialogView.findViewById(R.id.chose_comment_editText);
                lastComment =  editText.getText().toString();
            }
        });

        // When click on "Annuler" we go out of the alertDialog
        adb.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        adb.show();
    }



}
