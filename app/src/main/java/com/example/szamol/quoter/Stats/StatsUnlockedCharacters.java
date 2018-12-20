package com.example.szamol.quoter.Stats;

import com.example.szamol.quoter.GsonCharacterReader;

import java.util.Set;

public class StatsUnlockedCharacters {

    private static Set<String> unlockedCharacters;

    public static void load() {
        unlockedCharacters = StatsSaver.getUnlockedCharacters();
    }

    public static void addUnlockedCharacter(String key) {
        unlockedCharacters.add(key);
        StatsSaver.saveUnlockedCharacters(unlockedCharacters);
    }

    public static String getUnlockedCharactersNumber() {
        GsonCharacterReader gcr = new GsonCharacterReader();
        return String.format("%d z %d", unlockedCharacters.size(), gcr.getAllCharacters().length);
    }
}
