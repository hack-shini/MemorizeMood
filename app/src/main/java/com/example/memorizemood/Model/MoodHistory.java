package com.example.memorizemood.Model;

import com.example.memorizemood.Controller.MainActivity;

public class MoodHistory {

    private int moodPosition;
    private String moodComment;
    private long moodTime;

    public MoodHistory(int position) {
        this.moodPosition = position;
        this.moodTime = System.currentTimeMillis();
    }

    public MoodHistory(int position ,String comment) {
        this(position);
        this.moodComment = comment;
    }

    public long getMoodTime() {
        return moodTime;
    }
}
