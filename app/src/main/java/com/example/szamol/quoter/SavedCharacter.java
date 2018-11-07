package com.example.szamol.quoter;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class SavedCharacter implements CharacterStrategy {


    private Context mainContext;
    private Drawable image;
    private String characterKey;
    private String sentence;
    private String name;

    SavedCharacter(Context mainContext) {
        this.mainContext = mainContext;
        setSavedParams();
        setImage();
    }

    private void setSavedParams() {
        Saver saver = new Saver(mainContext);
        characterKey = saver.getSavedKey();
        sentence = saver.getSavedSentence();
        name = saver.getSavedName();
    }

    private void setImage() {
        image = mainContext.getResources().getDrawable(mainContext.getResources()
                .getIdentifier(characterKey, "drawable", mainContext.getPackageName()));
    }

    @Override
    public Drawable getImage() {
        return image;
    }

    @Override
    public String getSentence() {
        return sentence;
    }

    @Override
    public String getCharacterName() {
        return name;
    }
}
