package com.example.szamol.quoter.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.szamol.quoter.Boards.BoardsActivity;
import com.example.szamol.quoter.Boards.CurrentBoard;
import com.example.szamol.quoter.R;
import com.example.szamol.quoter.Stats.StatsButtonClicks;
import com.example.szamol.quoter.Stats.StatsReceivedQuotes;
import com.example.szamol.quoter.Stats.StatsActivity;
import com.example.szamol.quoter.Stats.StatsUnlockedCharacters;

public class MainActivity extends AppCompatActivity {

    TextView nameView;
    TextView quoteView;
    ImageView characterView;
    Button receiveButton;
    Button statsButton;
    Button boardsButton;
    CharacterManager characterManager;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hideSystemUI();

        initViewElements();
        initOnClickButtonListeners();

        characterManager = new CharacterManager();
        loadStats();
        loadSavedCharacter();
        CurrentBoard.loadCurrentBoard(quoteView);

        receiveButton.setEnabled(TimeSaver.isQuoteReady()); //button enabled ever 20 sec (draft)
    }

    @Override
    protected void onResume() {
        hideSystemUI();
        loadSavedCharacter();
        loadStats();
        receiveButton.setEnabled(TimeSaver.isQuoteReady());
        CurrentBoard.loadCurrentBoard(quoteView);
        super.onResume();
    }

    private void initViewElements() {
        nameView = findViewById(R.id.nameView);
        quoteView = findViewById(R.id.sentenceView);
        characterView = findViewById(R.id.characterView);
        receiveButton = findViewById(R.id.recieveButton);
        statsButton = findViewById(R.id.statsButton);
        boardsButton = findViewById(R.id.boardsButton);
    }

    private void initOnClickButtonListeners() {
        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeSaver.saveTime();

                StatsButtonClicks.increment();

                loadNewCharacter();
                receiveButton.setEnabled(false);
                animateNewQuote();
                TimeSaver.setNotification();
            }
        });

        statsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openStatsActivity();
                finish();
            }
        });

        boardsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBoardsActivity();
                finish();
            }
        });
    }

    private void animateNewQuote() {
        YoYo.with(Techniques.DropOut).duration(1500).playOn(nameView);
        YoYo.with(Techniques.Tada).duration(1500).delay(1000).playOn(quoteView);
        YoYo.with(Techniques.FadeInRight).duration(1500).playOn(characterView);
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
        quoteView.setText(characterManager.getCharacterQuote());
        characterView.setImageDrawable(null);
        characterView.setImageDrawable(characterManager.getCharacterImage());
    }

    private void loadSavedCharacter() {
        characterManager.setSavedCharacter();
        nameView.setText(characterManager.getCharacterName());
        quoteView.setText(characterManager.getCharacterQuote());
        characterView.setImageDrawable(characterManager.getCharacterImage());
    }

    private void loadStats() {
        StatsReceivedQuotes.load();
        StatsButtonClicks.load();
        StatsUnlockedCharacters.load();
    }

    private void hideSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }


}
