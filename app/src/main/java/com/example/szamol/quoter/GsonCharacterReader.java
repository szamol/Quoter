package com.example.szamol.quoter;

import com.example.szamol.quoter.Main.Character;
import com.example.szamol.quoter.Main.MainActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class GsonCharacterReader {
    private Gson gsonRead;
    private Character[] allCharacters;

    public GsonCharacterReader() {
        gsonRead = new Gson();
        readCharactersFromGson();
    }

    private void readCharactersFromGson() {
        try {
            Reader reader = new InputStreamReader(MainActivity.getMainContext().getAssets().open("characters.json"));
            allCharacters = gsonRead.fromJson(reader, Character[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Character[] getAllCharacters() {
        return allCharacters;
    }

}
