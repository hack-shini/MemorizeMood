package com.example.memorizemood.Model;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;


public class GsonConverter {

    // Serialize a single object.
    public String serializeSingleObjectToJson(MoodHistory mood){
        Gson gson = new Gson();
        String json = gson.toJson(mood);
        return json;
    }


    // Deserialize to single object.

    public MoodHistory deserializeFromJsonToSingleObject(String jsonString){
        Gson gson = new Gson();
        MoodHistory mood = gson.fromJson(jsonString, MoodHistory.class);
        return mood;
    }


    // Serialize List into JSON String

    public String serializeListToJson(ArrayList<MoodHistory> boardMoodHistory){
        Gson gson = new Gson();
        String json = gson.toJson(boardMoodHistory);
        return json;
    }


    // Deserialize JSON String back to List
    public ArrayList<MoodHistory> deserializeJsonToArrayList(String jsonArray){
        Type type =  new TypeToken<ArrayList<MoodHistory>>(){}.getType();
        ArrayList<MoodHistory> arrayList = new Gson().fromJson(jsonArray, type);
        return arrayList;

    }


}
