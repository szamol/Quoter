package com.example.szamol.quoter;

import android.content.Context;
import android.graphics.drawable.Drawable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

public class Character {

    private String key;
    private String sentence;
    private Drawable image;
    private Context mainContext;

    Character(String name, Context mainContext) {
        key = name;
        this.mainContext = mainContext;
        setImage();
        setSentence();
    }

    private void setImage() {
        image = mainContext.getResources().getDrawable(mainContext.getResources()
                .getIdentifier(key, "drawable", mainContext.getPackageName()));
    }

    private void setSentence() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(mainContext.getAssets().open(key + ".txt")));
            int sentenceNumber = chooseRandomSentence();
            for(int i = 0; i<sentenceNumber-1;i++) br.readLine();
            sentence = br.readLine();
            br.close();
        }catch (IOException e) {}
    }

    private int chooseRandomSentence() {
        Random r = new Random();
        return r.nextInt(countSentences());
    }

    private int countSentences() {
        int result = 0;

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(mainContext.getAssets().open(key + ".txt")));
            while(br.readLine() != null) result++;
            br.close();
        } catch (IOException e) {}

        return result;
    }

    public Drawable getImage() {
        return image;
    }

    public String getSentence(){
        return sentence;
    }

}
