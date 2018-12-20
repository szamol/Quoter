package com.example.szamol.quoter.Main;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.szamol.quoter.Boards.BoardsActivity;
import com.example.szamol.quoter.Boards.CurrentBoard;
import com.example.szamol.quoter.R;
import com.example.szamol.quoter.Stats.StatsButtonClicks;
import com.example.szamol.quoter.Stats.StatsReceivedQuotes;
import com.example.szamol.quoter.Stats.StatsActivity;
import com.example.szamol.quoter.Stats.StatsUnlockedCharacters;

public class MainActivity extends AppCompatActivity {

    TextView nameView;
    TextView sentenceView;
    ImageView characterView;
    Button receiveButton;
    Button statsButton;
    Button boardsButton;
    CharacterManager characterManager;

    private SharedPreferences preferences;

    private static Context mainContext;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainContext = getApplicationContext();

        initViewElements();
        initOnClickButtonListeners();

        characterManager = new CharacterManager();
        loadStats();
        loadSavedCharacter();
        CurrentBoard.loadCurrentBoard(sentenceView);

        preferences = getSharedPreferences("lastReceive", Activity.MODE_PRIVATE);
        receiveButton.setEnabled(isNewSentenceAvailable()); //button enabled ever 20 sec (draft)

    }

    @Override
    protected void onResume() {
        loadSavedCharacter();
        loadStats();
        receiveButton.setEnabled(isNewSentenceAvailable());
        CurrentBoard.loadCurrentBoard(sentenceView);
        super.onResume();
    }

    private void initViewElements() {
        nameView = findViewById(R.id.nameView);
        sentenceView = findViewById(R.id.sentenceView);
        characterView = findViewById(R.id.characterView);
        receiveButton = findViewById(R.id.recieveButton);
        statsButton = findViewById(R.id.statsButton);
        boardsButton = findViewById(R.id.boardsButton);
    }

    private void initOnClickButtonListeners() {
        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong("lastReceive", System.currentTimeMillis());
                editor.apply();

                StatsButtonClicks.increment();

                loadNewCharacter();
                receiveButton.setEnabled(false);
            }
        });

        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatsActivity();
            }
        });

        boardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBoardsActivity();
            }
        });
    }

    private void openStatsActivity() {
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(this);
    }

    private void openBoardsActivity() {
        Intent intent = new Intent(this, BoardsActivity.class);
        startActivity(intent);
        Animatoo.animateSlideRight(this);
    }

    private void loadNewCharacter() {
        characterManager.setNewCharacter();
        nameView.setText(characterManager.getCharacterName());
        sentenceView.setText(characterManager.getCharacterQuote());
        characterView.setImageDrawable(characterManager.getCharacterImage());
    }

    private void loadSavedCharacter() {
        characterManager.setSavedCharacter();
        nameView.setText(characterManager.getCharacterName());
        sentenceView.setText(characterManager.getCharacterQuote());
        characterView.setImageDrawable(characterManager.getCharacterImage());
    }

    private boolean isNewSentenceAvailable() {
        boolean result = false;
        long lastReceive = preferences.getLong("lastReceive", 0);
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastReceive > 20000) {
            result = true;
        }
        return result;
    }

    private void loadStats() {
        StatsReceivedQuotes.load();
        StatsButtonClicks.load();
        StatsUnlockedCharacters.load();
    }

    public static Context getMainContext() {
        return MainActivity.mainContext;
    }
}
