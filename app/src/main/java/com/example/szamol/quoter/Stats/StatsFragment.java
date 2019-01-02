package com.example.szamol.quoter.Stats;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.szamol.quoter.R;

public class StatsFragment extends Fragment {

    TextView totalQuotesView;
    TextView numberOfReceivedQuotesView;
    TextView percentOfUniqueReceivedQuotesView;
    TextView unlockedCharactersView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initViewElements();

        StatsReceivedQuotes.load();
        StatsButtonClicks.load();
        StatsUnlockedCharacters.load();

        totalQuotesView.setText(StatsAllQuotes.getTotalQuotesString());
        numberOfReceivedQuotesView.setText(StatsButtonClicks.getNumberOfReceiveButtonClicksString());
        percentOfUniqueReceivedQuotesView.setText(StatsReceivedQuotes.getPercentOfUniqueReceivedQuotes());
        unlockedCharactersView.setText(StatsUnlockedCharacters.getUnlockedCharactersNumber());
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

    private void initViewElements() {
        totalQuotesView = getView().findViewById(R.id.totalQuotesView);
        numberOfReceivedQuotesView = getView().findViewById(R.id.numberOfRecievedQuotesView);
        percentOfUniqueReceivedQuotesView = getView().findViewById(R.id.percentOfUniqueReceivedQuotesView);
        unlockedCharactersView = getView().findViewById(R.id.unlockedCharactersView);
    }
}
