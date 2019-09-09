package com.example.memorizemood.Controller;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.memorizemood.Model.GsonConverter;
import com.example.memorizemood.Model.Mood;
import com.example.memorizemood.Model.MoodHistory;
import com.example.memorizemood.R;
import com.example.memorizemood.utils.Keys;
import com.example.memorizemood.utils.Utils;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private LinearLayout parentView;
    private int screenWidth;
    private String[] diffDaysArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_comment);

        // ID of activity_history_comment.xml
        parentView = findViewById(R.id.activityHistoryCommentParent);

        // For found width of phone
        screenWidth = getWindowManager().getDefaultDisplay().getWidth();

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // Variable for acces on array days_array in R.Values.String
        diffDaysArray = getResources().getStringArray(R.array.days_array);

        String json = sharedPreferences.getString(Keys.BOARD_MOOD_HISTORY, null);

        int visibleRowCount = 0;
        if (json != null) {
            GsonConverter gsonConverter = new GsonConverter();
            ArrayList<MoodHistory> boardOfMoodHistory = gsonConverter.deserializeJsonToArrayList(json);

            // GENERATION ROW HERE
            for (int i = 0; i < boardOfMoodHistory.size(); i++) {
                genRow(boardOfMoodHistory.get(i));
                visibleRowCount++;
            }
        }

        for (int i = visibleRowCount; i < 7; i++) {
            invisibleRow();
        }

    }

    // Generate invisible row on historyActivity for the rows are the same size
    private void invisibleRow() {

        View row = LayoutInflater.from(this).inflate(R.layout.mood_history_row, null);
        row.setVisibility(View.INVISIBLE);

        row.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
        parentView.addView(row);

    }


    // That method generate a row with background color with a different color and size
    // & display (or not display) icon msg
    private void genRow(final MoodHistory moodHistory) {
        View row = LayoutInflater.from(this).inflate(R.layout.mood_history_row, null);
        TextView dateSinceThisMood = row.findViewById(R.id.dateSinceThisMood_textView);
        ImageView messageIcon = row.findViewById(R.id.message_imgView);
        Utils utils = new Utils();

        Mood mood = Utils.moods[moodHistory.getMoodPosition()];
        row.setBackgroundResource(mood.getBackgroundRes());

        int days = utils.dateFromLastComment(moodHistory);
        if (days <= 7){
            dateSinceThisMood.setText(diffDaysArray[days]);
        }else {
            dateSinceThisMood.setText(getString(R.string.x_days, days));
        }

        // Display message Icon if getMoodComment != null
        if (moodHistory.getMoodComment() != null && !moodHistory.getMoodComment().trim().equals("")) {
            messageIcon.setVisibility(View.VISIBLE);
            messageIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(HistoryActivity.this, moodHistory.getMoodComment(), Toast.LENGTH_LONG).show();
                }
            });
        }


        int size = 1; // Variable for size of mood in activity.xml

        // size management according to the mood of the day
        if (moodHistory.getMoodPosition() == 0) {
            size = screenWidth;
        } else if (moodHistory.getMoodPosition() == 1) {
            size = (int) (screenWidth / 1.2);
        } else if (moodHistory.getMoodPosition() == 2) {
            size = (int) (screenWidth / 1.4);
        } else if (moodHistory.getMoodPosition() == 3) {
            size = (int) (screenWidth / 1.6);
        } else if (moodHistory.getMoodPosition() == 4) {
            size = (int) (screenWidth / 2.8);
        }

        row.setLayoutParams(new LinearLayout.LayoutParams(size, ViewGroup.LayoutParams.MATCH_PARENT, 1f));
        parentView.addView(row);
    }

}
