package com.example.memorizemood.Model;

public class Mood {

    private int backgroundRes;
    private int smileyRes;


    public Mood(int backgroundRes, int smileyRes) {
        this.backgroundRes = backgroundRes;
        this.smileyRes = smileyRes;
    }

    public int getBackgroundRes() {
        return backgroundRes;
    }

    public int getSmileyRes() {
        return smileyRes;
    }
}
