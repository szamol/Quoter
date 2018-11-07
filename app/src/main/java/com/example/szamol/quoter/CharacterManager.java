package com.example.szamol.quoter;

import android.graphics.drawable.Drawable;


public class CharacterManager {
    private CharacterStrategy character;

    public void setCharacterStrategy(CharacterStrategy character) {
        this.character = character;
    }

    public Drawable getCharacterImage() {
        return character.getImage();
    }

    public String getCharacterSentence() {
        return character.getSentence();
    }

    public String getCharacterName() {
        return character.getCharacterName();
    }

}
