package com.example.szamol.quoter.Stats;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.szamol.quoter.GlobalContext;
import com.example.szamol.quoter.Main.MainActivity;

import java.util.HashSet;
import java.util.Set;

public class StatsSaver {

    private static SharedPreferences preferences = GlobalContext.getAppContext().getSharedPreferences("statPreferences", Activity.MODE_PRIVATE);

    public static void saveNumberOfReceiveButtonClicks(int value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("numberOfReceivedQuotes", value);
        editor.apply();
    }

    public static void saveSetOfReceivedQuotes(Set<String> value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet("receivedQuotes", value);
        editor.apply();
    }

    public static void saveUnlockedCharacters(Set<String> value) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putStringSet("unlockedCharacters", value);
        editor.apply();
    }

    public static int getNumberOfReceiveButtonClicks() {
        return preferences.getInt("numberOfReceivedQuotes", 0);
    }

    public static Set<String> getSetOfReceivedQuotes() {
        return preferences.getStringSet("receivedQuotes", new HashSet<String>());
    }

    public static Set<String> getUnlockedCharacters() {
        return preferences.getStringSet("unlockedCharacters", new HashSet<String>());
    }
}
