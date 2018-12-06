package com.example.szamol.quoter;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Random;


public class CharacterManager {

    private Gson gsonRead = new Gson();
    private Character[] charactersArray;
    private Character currentCharacter;
    private int characterId;

    private SharedPreferences preferences;

    CharacterManager() {
        preferences = MainActivity.getMainContext().getSharedPreferences("preferences", Activity.MODE_PRIVATE);
    }

    public void setNewCharacter() {
        try {
            Reader reader = new InputStreamReader(MainActivity.getMainContext().getAssets().open("characters.json"));
            charactersArray = gsonRead.fromJson(reader, Character[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        characterId = setCharacterId();
        currentCharacter = charactersArray[characterId];
        currentCharacter.loadImage();
        currentCharacter.loadRandomQuote();
        saveCharacter();
    }

    public void setSavedCharacter() {
        currentCharacter = new Character(preferences.getString("name", "") ,preferences.getString("key", "gandhi"), preferences.getString("quote", "Odbierz cytat klikając przycisk!"));
        currentCharacter.loadImage();
    }

    private int setCharacterId() {
        Random r = new Random();
        return r.nextInt(charactersArray.length);
    }

    private void saveCharacter() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("name", currentCharacter.getName());
        editor.putString("key", currentCharacter.getKey());
        editor.putString("quote", currentCharacter.getQuote());
        editor.apply();
    }

    public Drawable getCharacterImage() {
        return currentCharacter.getImage();
    }

    public String getCharacterQuote() {
        return currentCharacter.getQuote();
    }

    public String getCharacterName() {
        return currentCharacter.getName();
    }

}
