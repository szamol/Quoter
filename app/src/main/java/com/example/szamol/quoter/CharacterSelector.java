package com.example.szamol.quoter;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.util.Random;

public class CharacterSelector {
    private final int NUMBER_OF_CHARACTERS = 8;
    private Random random = new Random();
    private Character newCharacter;
    private Context mainContext;

    CharacterSelector(Context mainContext){
        this.mainContext = mainContext;
    }

    public void chooseCharacter() {
        int characterNumber = random.nextInt(NUMBER_OF_CHARACTERS);
        String characterName = "";

        switch(characterNumber) {
            case 0: characterName = "chaplin";break;
            case 1: characterName = "schopenhauer";break;
            case 2: characterName = "gandhi";break;
            case 3: characterName = "guevara";break;
            case 4: characterName = "konfucjusz";break;
            case 5: characterName = "mandela";break;
            case 6: characterName = "napoleon";break;
            case 7: characterName = "churchill";break;
        }
        newCharacter = new Character(characterName, mainContext);
    }

    public Drawable getCharacterView() {
        return newCharacter.getImage();
    }
    public String getCharacterSentece() {return newCharacter.getSentence();}}
