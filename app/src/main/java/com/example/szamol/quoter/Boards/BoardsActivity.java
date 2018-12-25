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
        hideSystemUI();
        initViewElements();
        initOnClickListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUI();
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
        finish();
    }

    private void boardChangedToast(String boardName) {
        Toast.makeText(this, "Ustawiono " + boardName + "!", Toast.LENGTH_SHORT).show();
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
