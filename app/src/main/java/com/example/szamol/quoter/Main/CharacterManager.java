package com.example.szamol.quoter.Main;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;

import com.example.szamol.quoter.GlobalContext;
import com.example.szamol.quoter.GsonCharacterReader;
import com.example.szamol.quoter.Stats.StatsReceivedQuotes;
import com.example.szamol.quoter.Stats.StatsUnlockedCharacters;

import java.util.Random;


public class CharacterManager {

    private Character[] charactersArray;
    private Character currentCharacter;
    private int characterId;

    private SharedPreferences preferences;

    CharacterManager() {
        preferences = GlobalContext.getAppContext().getSharedPreferences("characterPreferences", Activity.MODE_PRIVATE);
    }

    public void setNewCharacter() {

        GsonCharacterReader gcr = new GsonCharacterReader();
        charactersArray = gcr.getAllCharacters();

        characterId = setCharacterId();
        currentCharacter = charactersArray[characterId];

        StatsUnlockedCharacters.addUnlockedCharacter(currentCharacter.getKey());

        currentCharacter.loadImage();
        currentCharacter.loadRandomQuote();

        StatsReceivedQuotes.addReceivedQuote(currentCharacter.getQuote());
        saveCharacter();
    }

    public void setSavedCharacter() {
        currentCharacter = new Character(
                preferences.getString("name", ""),
                preferences.getString("key", "gandhi"),
                preferences.getString("quote", "Odbierz cytat klikając przycisk!"));
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
        currentCharacter.loadImage();
        return currentCharacter.getImage();
    }

    public String getCharacterQuote() {
        return currentCharacter.getQuote();
    }

    public String getCharacterName() {
        return currentCharacter.getName();
    }

}
