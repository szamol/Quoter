package com.example.szamol.quoter;

import android.app.Activity;
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
    CharacterManager characterManager;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameView = findViewById(R.id.nameView);
        sentenceView = findViewById(R.id.sentenceView);
        characterView = findViewById(R.id.characterView);
        receiveButton = findViewById(R.id.recieveButton);

        characterManager = new CharacterManager();
        loadSavedCharacter();

        preferences = getSharedPreferences("lastReceive", Activity.MODE_PRIVATE);
        receiveButton.setEnabled(isNewSentenceAvailable()); //button enabled ever 20 sec (draft)

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
    }

    @Override
    protected void onResume() {
        loadSavedCharacter();
        receiveButton.setEnabled(isNewSentenceAvailable());
        super.onResume();
    }

    private void loadNewCharacter() {
        characterManager.setCharacterStrategy(new NewCharacter(getApplicationContext()));
        nameView.setText(characterManager.getCharacterName());
        sentenceView.setText(characterManager.getCharacterSentence());
        characterView.setImageDrawable(characterManager.getCharacterImage());
    }

    private void loadSavedCharacter() {
        characterManager.setCharacterStrategy(new SavedCharacter(getApplicationContext()));
        nameView.setText(characterManager.getCharacterName());
        sentenceView.setText(characterManager.getCharacterSentence());
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
}
