package com.example.szamol.quoter;

import android.app.Activity;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.Set;

class Stats {

    private static SharedPreferences preferences = MainActivity.getMainContext().getSharedPreferences("preferences", Activity.MODE_PRIVATE);

    private static int numberOfReceivedQuotes = 0;
    private static Set<String> receivedQuotes = new HashSet<>();

    public static void incrementReceivedQuotes() {
        numberOfReceivedQuotes++;
        saveStats("numberOfReceivedQuotes");
    }

    public static void addReceivedQuote(String receivedQuote) {
        receivedQuotes.add(Integer.toString(receivedQuote.hashCode()));
        System.out.println(receivedQuotes.size());
        saveStats("receivedQuotes");
    }

    private static void saveStats(String nameOfStat) {
        SharedPreferences.Editor editor = preferences.edit();
        if (nameOfStat == "numberOfReceivedQuotes")
            editor.putInt(nameOfStat, numberOfReceivedQuotes);
        if (nameOfStat == "receivedQuotes") editor.putStringSet(nameOfStat, receivedQuotes);
        editor.apply();
    }

    public static void load() {
        numberOfReceivedQuotes = preferences.getInt("numberOfReceivedQuotes", 0);
        receivedQuotes = preferences.getStringSet("receivedQuotes", new HashSet<String>());
    }

    public static int getNumberOfReceivedQuotes() {
        return numberOfReceivedQuotes;
    }

    public static String getPercentOfUniqueReceivedQuotes() {
        double receivedQuotesNumber = receivedQuotes.size();
        double allQuotes = getAllQuotesNumber();
        DecimalFormat df = new DecimalFormat("0.00");
        System.out.println(allQuotes);
        return df.format(receivedQuotesNumber / allQuotes) + " %";
    }

    private static double getAllQuotesNumber() {
        Gson gsonRead = new Gson();
        Character[] charactersArray;
        double result = 0.00;
        try {
            Reader reader = new InputStreamReader(MainActivity.getMainContext().getAssets().open("characters.json"));
            charactersArray = gsonRead.fromJson(reader, Character[].class);

            for (int i = 0; i < charactersArray.length; i++) {
                result += charactersArray[i].getAllCharactersQuotes();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;
    }
}
