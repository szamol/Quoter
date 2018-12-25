package com.example.szamol.quoter.Stats;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.example.szamol.quoter.Main.MainActivity;
import com.example.szamol.quoter.R;

public class StatsActivity extends AppCompatActivity {

    Button backButton;
    TextView totalQuotesView;
    TextView numberOfReceivedQuotesView;
    TextView percentOfUniqueReceivedQuotesView;
    TextView unlockedCharactersView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        hideSystemUI();
        initViewElements();

        StatsReceivedQuotes.load();
        StatsButtonClicks.load();
        StatsUnlockedCharacters.load();

        totalQuotesView.setText(StatsAllQuotes.getTotalQuotesString());
        numberOfReceivedQuotesView.setText(StatsButtonClicks.getNumberOfReceiveButtonClicksString());
        percentOfUniqueReceivedQuotesView.setText(StatsReceivedQuotes.getPercentOfUniqueReceivedQuotes());
        unlockedCharactersView.setText(StatsUnlockedCharacters.getUnlockedCharactersNumber());
        initOnClickListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        hideSystemUI();
    }


    private void initViewElements() {
        backButton = findViewById(R.id.backButton);
        totalQuotesView = findViewById(R.id.totalQuotesView);
        numberOfReceivedQuotesView = findViewById(R.id.numberOfRecievedQuotesView);
        percentOfUniqueReceivedQuotesView = findViewById(R.id.percentOfUniqueReceivedQuotesView);
        unlockedCharactersView = findViewById(R.id.unlockedCharactersView);
    }

    private void initOnClickListeners() {
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backToMainActivity();
                finish();
            }
        });
    }

    private void backToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Animatoo.animateSlideRight(this);
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
