package com.example.szamol.quoter.Boards;

import android.app.Activity;
import android.content.SharedPreferences;

import com.example.szamol.quoter.Main.MainActivity;

public class BoardSaver {
    private static SharedPreferences preferences = MainActivity.getMainContext().getSharedPreferences("boardPreferences", Activity.MODE_PRIVATE);

    public static void saveBoard(String boardKey) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("currentBoard", boardKey);
        editor.apply();
    }

    public static String getBoardKey() {
        return preferences.getString("currentBoard", "stoneboard");
    }
}
