package com.example.memorizemood.Controller;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.example.memorizemood.Controller.MainActivity;
import com.example.memorizemood.utils.Utils;
import com.google.gson.Gson;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private LinearLayout parentView;
    private LinearLayout linearLayout;
    private TextView historyMoodComment_textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_comment);
        parentView = findViewById(R.id.activityHistoryCommentParent);
        linearLayout = findViewById(R.id.linearLayout);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);



        String json = sharedPreferences.getString(Keys.BOARD_MOOD_HISTORY,null);

        if (json != null){
            GsonConverter gsonConverter = new GsonConverter();
            ArrayList<MoodHistory> boardOfMoodHistory = gsonConverter.deserializeJsonToArrayList(json);

            // GENERATION ROW HERE
            for (int i = 0; i < boardOfMoodHistory.size() ;i++){
                genRow(boardOfMoodHistory.get(i));
            }
        }
    }

    private void genRow(final MoodHistory moodHistory){

        View row = LayoutInflater.from(this).inflate(R.layout.mood_history_row, null);
        TextView historyMoodComment_textView = row.findViewById(R.id.dateSinceThisMood_textView) ;
        ImageView messageIcon = row.findViewById(R.id.message_imgView);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();

        Mood mood = Utils.moods[moodHistory.getMoodPosition()];
        row.setBackgroundResource(mood.getBackgroundRes());

        if (moodHistory.getMoodComment() != null && !moodHistory.getMoodComment().trim().equals("")){
           // historyMoodComment_textView.setText(moodHistory.getMoodComment());  // AFFICHAGE COMMENTAIRE A TRANSFORMER EN AFFICHAGE DATE DEPUIS LE COMMENTAIRE

            messageIcon.setVisibility(View.VISIBLE);
            messageIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(HistoryActivity.this,moodHistory.getMoodComment(),Toast.LENGTH_LONG).show();
                }
            });

            if ()
        }


        parentView.addView(row);
    }
}
