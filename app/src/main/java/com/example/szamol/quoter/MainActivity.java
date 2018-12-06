package com.example.szamol.quoter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView nameView;
    TextView sentenceView;
    ImageView characterView;
    Button receiveButton;
    Button statsButton;
    CharacterManager characterManager;

    private SharedPreferences preferences;

    private static Context mainContext;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainContext = getApplicationContext();

        nameView = findViewById(R.id.nameView);
        sentenceView = findViewById(R.id.sentenceView);
        characterView = findViewById(R.id.characterView);
        receiveButton = findViewById(R.id.recieveButton);
        statsButton = findViewById(R.id.statsButton);

        characterManager = new CharacterManager();

        loadSavedCharacter();

        preferences = getSharedPreferences("lastReceive", Activity.MODE_PRIVATE);
        receiveButton.setEnabled(isNewSentenceAvailable()); //button enabled ever 20 sec (draft)

        initOnClickButtonListeners();

    }

    @Override
    protected void onResume() {
        loadSavedCharacter();
        receiveButton.setEnabled(isNewSentenceAvailable());
        super.onResume();
    }

    private void initOnClickButtonListeners() {
        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong("lastReceive", System.currentTimeMillis());
                editor.apply();

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
    }

    private void openStatsActivity() {
        Intent intent = new Intent(this, StatsActivity.class);
        startActivity(intent);
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

    public static Context getMainContext() {
        return MainActivity.mainContext;
    }
}
