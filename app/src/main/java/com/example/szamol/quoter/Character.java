package com.example.szamol.quoter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Character {

    @SerializedName("name")
    private String name;

    @SerializedName("key")
    private String key;

    @SerializedName("quotes")
    private String[] quotes;

    private String currentQuote;
    private Drawable image;

    Character(String savedName, String savedKey, String savedQuote) {
        this.name = savedName;
        this.key = savedKey;
        this.currentQuote = savedQuote;
    }

    Character(String name, String key, String[] quotes) {
        this.name = name;
        this.key = key;
        this.quotes = quotes;
    }

    public void loadRandomQuote() {
        Random r = new Random();
        currentQuote = quotes[r.nextInt(quotes.length)];
    }

    public void loadImage() {
        image = setImage();
    }


    private Drawable setImage() {
        return MainActivity.getMainContext().getResources().getDrawable(MainActivity.getMainContext().getResources()
                .getIdentifier(key, "drawable", MainActivity.getMainContext().getPackageName()));
    }

    public Drawable getImage() {
        return image;
    }

    public String getQuote() {
        return currentQuote;
    }

    public String getName() {
        return name;
    }

    public String getKey() {
        return key;
    }

    public int getAllCharactersQuotes() {
        return quotes.length;
    }
}
