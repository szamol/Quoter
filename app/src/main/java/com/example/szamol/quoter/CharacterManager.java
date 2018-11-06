package com.example.szamol.quoter;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.Random;

public class CharacterManager {
    private final int NUMBER_OF_CHARACTERS = 8;
    private Random random = new Random();
    private Character character;
    private Context mainContext;

    CharacterManager(Context mainContext) {
        this.mainContext = mainContext;
    }

    public void chooseNewCharacter() {
        int characterNumber = random.nextInt(NUMBER_OF_CHARACTERS);
        String characterKey = "";

        switch (characterNumber) {
            case 0:
                characterKey = "chaplin";
                break;
            case 1:
                characterKey = "schopenhauer";
                break;
            case 2:
                characterKey = "gandhi";
                break;
            case 3:
                characterKey = "guevara";
                break;
            case 4:
                characterKey = "konfucjusz";
                break;
            case 5:
                characterKey = "mandela";
                break;
            case 6:
                characterKey = "napoleon";
                break;
            case 7:
                characterKey = "churchill";
                break;
        }
        character = new Character(characterKey, mainContext);
    }

    public void loadOldCharacter() {
        character = new Character(mainContext);
    }

    public Drawable getCharacterImage() {
        return character.getImage();
    }

    public String getCharacterSentence() {
        return character.getSentence();
    }
}
