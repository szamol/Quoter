package com.example.szamol.quoter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {

    Button backButton;
    TextView numberOfReceivedQuotesView;
    TextView percentOfUniqueReceivedQuotesView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        backButton = findViewById(R.id.backButton);
        numberOfReceivedQuotesView = findViewById(R.id.numberOfRecievedQuotesView);
        percentOfUniqueReceivedQuotesView = findViewById(R.id.percentOfUniqueReceivedQuotesView);

        Stats.load();

        numberOfReceivedQuotesView.setText(Integer.toString(Stats.getNumberOfReceivedQuotes()));
        percentOfUniqueReceivedQuotesView.setText(Stats.getPercentOfUniqueReceivedQuotes());
        initOnClickListeners();
    }

    @Override
    protected void onResume() {
        numberOfReceivedQuotesView.setText(Integer.toString(Stats.getNumberOfReceivedQuotes()));
        super.onResume();
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
    }
}
