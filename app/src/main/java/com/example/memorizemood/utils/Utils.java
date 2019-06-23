package com.example.memorizemood.utils;

import com.example.memorizemood.Model.MoodHistory;

import java.util.Calendar;

public class Utils {

    public static boolean isSameDate(MoodHistory moodHistory1, MoodHistory moodHistory2){

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(moodHistory1.getMoodTime());

        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTimeInMillis(moodHistory2.getMoodTime());

        // If true :
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
    }
}
