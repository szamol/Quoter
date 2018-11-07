package com.example.szamol.quoter;

import android.graphics.drawable.Drawable;

public interface CharacterStrategy {
    Drawable getImage();

    String getSentence();

    String getCharacterName();
}
