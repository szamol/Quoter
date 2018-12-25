package com.example.szamol.quoter.Main;

import android.graphics.drawable.Drawable;

import com.example.szamol.quoter.GlobalContext;
import com.google.gson.annotations.SerializedName;

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

    public Character(String savedName, String savedKey, String savedQuote) {
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
        return GlobalContext.getAppContext().getResources().getDrawable(GlobalContext.getAppContext().getResources()
                .getIdentifier(key, "drawable", GlobalContext.getAppContext().getPackageName()));
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

    public int getAllCharactersQuotesSize() {
        return quotes.length;
    }
}
