package com.example.memorizemood.Model;

public class Gson {

    // Serialize a single object.
    public String serializeToJson(Mood mood){
        Gson gson = new Gson();
        String json = gson.serializeToJson(mood);
        return json;
    }

    // Deserialize to single object.

    public Mood deserializeFromJson(String jsonString){
        Gson gson = new Gson();
        Mood mood = gson.deserializeFromJson(jsonString);
        return mood;
    }



}
