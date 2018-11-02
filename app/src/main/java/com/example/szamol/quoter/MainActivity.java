package com.example.szamol.quoter;


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
    CharacterSelector selector;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        characterView = findViewById(R.id.characterView);
        sentenceView = findViewById(R.id.sentenceView);
        receiveButton = findViewById(R.id.recieveButton);
        selector = new CharacterSelector(this);

        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selector.chooseCharacter();
                characterView.setImageDrawable(selector.getCharacterView());
                sentenceView.setText(selector.getCharacterSentece());
            }
        });

    }
}
