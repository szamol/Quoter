package com.example.szamol.quoter.Boards;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.szamol.quoter.Main.MainActivity;
import com.example.szamol.quoter.R;

public class BoardsActivity extends AppCompatActivity {

    private Button backButton;
    private Button stoneboardButton;
    private Button papirusboardButton;
    private Button schoolboardButton;
    private Button whiteboardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boards);

        initViewElements();
        initOnClickListeners();
    }

    private void initViewElements() {
        backButton = findViewById(R.id.backButton);
        stoneboardButton = findViewById(R.id.stoneboardButton);
        papirusboardButton = findViewById(R.id.papirusboardButton);
        schoolboardButton = findViewById(R.id.schoolboardButton);
        whiteboardButton = findViewById(R.id.whiteboardButton);
    }
    private void initOnClickListeners() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainActivity();
            }
        });
        stoneboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentBoard.setCurrentBoard("stoneboard");
                BoardSaver.saveBoard("stoneboard");
                boardChangedToast("Default");
            }
        });
        papirusboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentBoard.setCurrentBoard("papirusboard");
                BoardSaver.saveBoard("papirusboard");
                boardChangedToast("Papirus");
            }
        });
        schoolboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentBoard.setCurrentBoard("schoolboard");
                BoardSaver.saveBoard("schoolboard");
                boardChangedToast("Tablica Szkolna");
            }
        });
        whiteboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentBoard.setCurrentBoard("whiteboard");
                BoardSaver.saveBoard("whiteboard");
                boardChangedToast("Biała Tablica");
            }
        });
    }

    private void backToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Animatoo.animateSlideLeft(this);
    }

    private void boardChangedToast(String boardName) {
        Toast.makeText(this, "Ustawiono " + boardName + "!", Toast.LENGTH_SHORT).show();
    }
}
