package com.example.memorizemood.Model;


public class MoodHistory {

    private int moodPosition;
    private String moodComment;
    private long moodTime;

    public MoodHistory(int position) {
        this.moodPosition = position;
        this.moodTime = System.currentTimeMillis();
    }

    public MoodHistory() {
        this.moodTime = System.currentTimeMillis();
    }

    public MoodHistory(int position ,String comment) {
        this(position);
        this.moodComment = comment;
    }

    public long getMoodTime() {
        return moodTime;
    }

    public int getMoodPosition() {
        return moodPosition;
    }

    public String getMoodComment() {
        return moodComment;
    }

    @Override
    public String toString() {
        return "moodPosition = "+moodPosition+" - moodComment = "+moodComment+" - moodTime = "+moodTime;
    }
}
