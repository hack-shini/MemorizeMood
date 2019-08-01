package com.example.memorizemood.Model;

import com.google.gson.Gson;

public class GsonConverter {

    // Serialize a single object.
    public String serializeToJson(MoodHistory mood){
        Gson gson = new Gson();
        String json = gson.toJson(mood);
        return json;
    }


    // Deserialize to single object.

    public MoodHistory deserializeFromJson(String jsonString){
        Gson gson = new Gson();
        MoodHistory mood = gson.fromJson(jsonString, MoodHistory.class);
        return mood;
    }



}
