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
import com.example.szamol.quoter.Stats.StatsAllQuotes;
import com.example.szamol.quoter.Stats.StatsButtonClicks;
import com.example.szamol.quoter.Stats.StatsReceivedQuotes;

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

        initViewElements();

        StatsReceivedQuotes.load();
        StatsButtonClicks.load();
        StatsUnlockedCharacters.load();

        totalQuotesView.setText(StatsAllQuotes.getTotalQuotesString());
        numberOfReceivedQuotesView.setText(StatsButtonClicks.getNumberOfReceiveButtonClicksString());
        percentOfUniqueReceivedQuotesView.setText(StatsReceivedQuotes.getPercentOfUniqueRecivedQuotes());
        unlockedCharactersView.setText(StatsUnlockedCharacters.getUnlockedCharactersNumber());
        initOnClickListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
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
            }
        });
    }

    private void backToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        Animatoo.animateSlideRight(this);
    }
}
