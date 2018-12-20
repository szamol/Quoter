package com.example.szamol.quoter.Boards;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.TextView;

import com.example.szamol.quoter.Main.MainActivity;

public class CurrentBoard {
    private static Drawable currentBoard;
    private static String boardKey;

    public static void setCurrentBoard(String key) {
        boardKey = key;
        currentBoard = MainActivity.getMainContext().getResources().getDrawable(MainActivity.getMainContext().getResources()
                .getIdentifier(key, "drawable", MainActivity.getMainContext().getPackageName()));
    }

    public static void loadCurrentBoard(TextView sentenceView) {
        setCurrentBoard(BoardSaver.getBoardKey());
        sentenceView.setBackground(getCurrentBoard());
        setCurrentBoardFont(sentenceView);
    }

    private static void setCurrentBoardFont(TextView sentenceView) {
        int color = Color.rgb(85,85,85);
        switch(boardKey) {
            case "stoneboard": color = Color.rgb(85,85,85);break;
            case "papirusboard": color = Color.rgb(165,42,42);break;
            case "schoolboard": color = Color.rgb(255,255,255);break;
            case "whiteboard": color = Color.rgb(0,128,0);break;
        }
        sentenceView.setTextColor(color);
    }

    public static Drawable getCurrentBoard() {
        return currentBoard;
    }
}
