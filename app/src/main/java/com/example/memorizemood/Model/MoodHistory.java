package com.example.memorizemood.Model;

import com.example.memorizemood.Controller.MainActivity;

public class MoodHistory {

    private String moodComment;
    private Mood mood;
    private long moodTime;

    public MoodHistory(Mood mood) {
        this.mood = mood;
        this.moodTime = System.currentTimeMillis();
    }

    public MoodHistory(Mood mood ,String comment) {
        this(mood);
        this.moodComment = comment;
    }

    public long getMoodTime() {
        return moodTime;
    }
}
