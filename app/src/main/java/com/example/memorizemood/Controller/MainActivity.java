package com.example.memorizemood.Controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.memorizemood.Model.DetectSwipeGestureListener;
import com.example.memorizemood.Model.Mood;
import com.example.memorizemood.Model.MoodHistory;
import com.example.memorizemood.Model.GsonConverter;
import com.example.memorizemood.R;
import com.example.memorizemood.utils.Utils;

import java.util.ArrayList;

import static com.example.memorizemood.utils.Keys.BOARD_MOOD_HISTORY;
import static com.example.memorizemood.utils.Keys.LAST_MOOD_KEY;

public class MainActivity extends AppCompatActivity{

    private ImageButton addNoteImgBtn;
    private ImageButton historyImgBtn;
    private ImageView moozHappy;

    private EditText chooseCommentEditTxt;

    private GestureDetectorCompat gestureDetectorCompat;
    private Mood currentMood;
    private int indiceMoodPosition = 1;
    private MoodHistory moodHistory;
    private String currentComment;
    private String lastComment;
    private ArrayList<MoodHistory> boardOfMoodHistory;

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

        addNoteImgBtn = findViewById(R.id.add_note_imageBtn);
        historyImgBtn = findViewById(R.id.history_imageBtn);
        moozHappy = findViewById(R.id.smiley_happy_imageView);

        // test
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        chooseCommentEditTxt = findViewById(R.id.chose_comment_editText);
        // Fin test




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






    }

    private void handleSwipe(boolean isSwipeUp) {

        currentMood = Utils.moods[indiceMoodPosition];
        findViewById(R.id.idRelativeLayout).setBackgroundResource(currentMood.getBackgroundRes());
        moozHappy.setImageResource(currentMood.getSmileyRes());


        if (isSwipeUp) {
            if (indiceMoodPosition < Utils.moods.length - 1) {
                indiceMoodPosition++;
            }
        }else {
            if (indiceMoodPosition > 0){
                indiceMoodPosition--;
            }
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    
    @Override
    protected void onPause() {
        GsonConverter serialize = new GsonConverter();
        GsonConverter deserialize = new GsonConverter();
        moodHistory = new MoodHistory(indiceMoodPosition, lastComment);
        boardOfMoodHistory = new ArrayList<>();

        String toJsonMood = serialize.serializeSingleObjectToJson(moodHistory);
        MoodHistory fromJsonMood = deserialize.deserializeFromJsonToSingleObject(toJsonMood);

        sharedPreferences.edit().putString(LAST_MOOD_KEY,toJsonMood).apply();

        Utils.isSameDate(fromJsonMood,fromJsonMood);

        boardOfMoodHistory.add(moodHistory);

        if (boardOfMoodHistory.size() >= 7){
            boardOfMoodHistory.add(0,moodHistory);
            boardOfMoodHistory.remove(7);
        }

        String toJsonArray = serialize.serializeListToJson(boardOfMoodHistory);

        sharedPreferences.edit().putString(BOARD_MOOD_HISTORY, toJsonArray).apply();

        Log.e("MainActivity",toJsonArray);


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
