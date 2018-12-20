package com.example.szamol.quoter.Stats;

import com.example.szamol.quoter.Main.Character;
import com.example.szamol.quoter.GsonCharacterReader;

public class StatsAllQuotes {

    private static int totalQuotes = countTotalQuotes();

    private static int countTotalQuotes() {
        GsonCharacterReader gcr = new GsonCharacterReader();
        Character[] charactersArray = gcr.getAllCharacters();

        int totalQuotes = 0;

        for (Character character : charactersArray) {
            totalQuotes += character.getAllCharactersQuotesSize();
        }

        return totalQuotes;
    }

    public static int getTotalQuotes() {
        return totalQuotes;
    }

    public static String getTotalQuotesString() {
        return Integer.toString(totalQuotes);
    }
}
