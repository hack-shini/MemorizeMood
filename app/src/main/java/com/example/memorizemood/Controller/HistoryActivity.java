package com.example.memorizemood.Controller;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.memorizemood.R;
import com.example.memorizemood.utils.Keys;
import com.example.memorizemood.Controller.MainActivity;

public class HistoryActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private LinearLayout parentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_comment);
        parentView = findViewById(R.id.activityHistoryCommentParent);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//
//        // TEST : Save comment in editText on alertDialog

//
//        if (sharedPreferences.contains(Keys.COMMENT_KEY)){
//            String currentComment = sharedPreferences.getString(Keys.COMMENT_KEY,"aucun commentaire trouvé");
//            tv1.setText(currentComment);
//        }
//
//        // FIN TEST SAVE
            if (sharedPreferences.contains(Keys.COMMENT_KEY)){
                genRow();
            }



    }

    private void genRow(){


        View row = LayoutInflater.from(this).inflate(R.layout.mood_history_row, null);
        TextView historyMoodComment_textView = row.findViewById(R.id.historyMoodComment_textView) ;
        ImageView messageIcon = row.findViewById(R.id.message_imgView);

        messageIcon.setVisibility(View.INVISIBLE);

        if (sharedPreferences.contains(Keys.COMMENT_KEY)){
            String currentComment = sharedPreferences.getString(Keys.COMMENT_KEY,"aucun commentaire trouvé");
            historyMoodComment_textView.setText(currentComment);
            messageIcon.setVisibility(View.VISIBLE);
        }


        parentView.addView(row);
    }
}