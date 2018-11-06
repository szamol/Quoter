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

    ImageView characterView;
    TextView sentenceView;
    Button receiveButton;
    CharacterManager characterManager;

    private SharedPreferences preferences;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        characterView = findViewById(R.id.characterView);
        sentenceView = findViewById(R.id.sentenceView);
        receiveButton = findViewById(R.id.recieveButton);
        characterManager = new CharacterManager(this);

        preferences = getSharedPreferences("lastReceive", Activity.MODE_PRIVATE);

        loadOldCharacter();

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
        characterManager.loadOldCharacter();
        receiveButton.setEnabled(isNewSentenceAvailable());
        super.onResume();
    }

    private void loadNewCharacter() {
        characterManager.chooseNewCharacter();
        characterView.setImageDrawable(characterManager.getCharacterImage());
        sentenceView.setText(characterManager.getCharacterSentence());
    }

    private void loadOldCharacter() {
        characterManager.loadOldCharacter();
        characterView.setImageDrawable(characterManager.getCharacterImage());
        sentenceView.setText(characterManager.getCharacterSentence());
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
