package com.example.szamol.quoter;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class NewCharacter implements CharacterStrategy {

    private String characterKey;
    private String characterName;
    private String sentence;
    private Drawable image;
    private Context mainContext;

    private final int NUMBER_OF_CHARACTERS = 8;

    NewCharacter(Context mainContext) {
        this.mainContext = mainContext;
        chooseCharacter();
        setCharacterName();
        setImage();
        setSentence();
        saveCharacter();
    }

    private void chooseCharacter() {
        Random r = new Random();
        int characterNumber = r.nextInt(NUMBER_OF_CHARACTERS);
        characterKey = "";

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
    }

    private void setCharacterName() {
        switch (characterKey) {
            case "chaplin":
                characterName = "Charlie Chaplin";
                break;
            case "schopenhauer":
                characterName = "Artur Schopenhauer";
                break;
            case "gandhi":
                characterName = "Mahatma Gandhi";
                break;
            case "guevara":
                characterName = "Che Guevara";
                break;
            case "konfucjusz":
                characterName = "Konfucjusz";
                break;
            case "mandela":
                characterName = "Nelson Mandela";
                break;
            case "napoleon":
                characterName = "Napoleon Bonaparte";
                break;
            case "churchill":
                characterName = "Winston Churchill";
                break;
        }
    }

    private void setImage() {
        image = mainContext.getResources().getDrawable(mainContext.getResources()
                .getIdentifier(characterKey, "drawable", mainContext.getPackageName()));
    }

    private void setSentence() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(mainContext.getAssets().open(characterKey + ".txt")));
            int sentenceNumber = chooseRandomSentence();
            for (int i = 0; i < sentenceNumber - 1; i++) br.readLine();
            sentence = br.readLine();
            br.close();
        } catch (IOException e) {
        }
    }

    private int chooseRandomSentence() {
        Random r = new Random();
        return r.nextInt(countSentences());
    }

    private int countSentences() {
        int result = 0;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(mainContext.getAssets().open(characterKey + ".txt")));
            while (br.readLine() != null) result++;
            br.close();
        } catch (IOException e) {
        }

        return result;
    }

    private void saveCharacter() {
        Saver saver = new Saver(mainContext);
        saver.save(characterKey, sentence, characterName);
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
        return characterName;
    }
}
