package com.example.memorizemood.utils;

import android.widget.TextView;

import com.example.memorizemood.Model.Mood;
import com.example.memorizemood.Model.MoodHistory;
import com.example.memorizemood.R;

import java.util.Calendar;

public class Utils {

    public static boolean isMoodFromToday(MoodHistory moodHistory){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(moodHistory.getMoodTime());

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(System.currentTimeMillis());

        // If true :
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
    }


   public static Mood[] moods = new Mood[] {

        new Mood(R.color.banana_yellow, R.drawable.smiley_super_happy),
        new Mood(R.color.light_sage, R.drawable.smiley_happy),
        new Mood(R.color.cornflower_blue_65, R.drawable.smiley_normal),
        new Mood(R.color.warm_grey, R.drawable.smiley_disappointed),
        new Mood(R.color.faded_red, R.drawable.smiley_sad),
    };






}
