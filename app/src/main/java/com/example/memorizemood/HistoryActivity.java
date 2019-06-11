package com.example.memorizemood;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.memorizemood.utils.Keys;

public class HistoryActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    private TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_comment);
        tv1 = findViewById(R.id.tv1TextView);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // TEST : Save comment in editText on alertDialog


        if (sharedPreferences.contains(Keys.COMMENT_KEY)){
            String currentComment = sharedPreferences.getString(Keys.COMMENT_KEY,"aucun commentaire trouvé");
            tv1.setText(currentComment);
        }

        // FIN TEST SAVE

    }
}
