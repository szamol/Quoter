package com.example.szamol.quoter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class Saver {
    private SharedPreferences preferences;

    Saver(Context mainContext) {
        preferences = mainContext.getSharedPreferences("preferences", Activity.MODE_PRIVATE);
    }

    public void save(String characterKey, String sentence, String characterName) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("characterKey", characterKey);
        editor.putString("sentence", sentence);
        editor.putString("characterName", characterName);
        editor.apply();
    }

    public String getSavedKey() {
        return preferences.getString("characterKey", "gandhi");
    }

    public String getSavedSentence() {
        return preferences.getString("sentence", "Odbierz cytat klikając przycisk!");
    }

    public String getSavedName() {
        return preferences.getString("characterName", "");
    }
}
