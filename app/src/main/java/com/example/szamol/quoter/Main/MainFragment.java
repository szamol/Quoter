package com.example.szamol.quoter.Main;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.example.szamol.quoter.Boards.CurrentBoard;
import com.example.szamol.quoter.Help.HelpActivity;
import com.example.szamol.quoter.R;
import com.example.szamol.quoter.Stats.StatsButtonClicks;
import com.example.szamol.quoter.Stats.StatsReceivedQuotes;
import com.example.szamol.quoter.Stats.StatsUnlockedCharacters;

import static android.content.Context.CLIPBOARD_SERVICE;

public class MainFragment extends Fragment {

    Button clipboardButton;
    Button helpButton;
    TextView nameView;
    TextView quoteView;
    ImageView characterView;
    Button receiveButton;
    CharacterManager characterManager;

    public MainFragment(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initViewElements();
        initOnClickButtonListeners();

        characterManager = new CharacterManager();
        loadStats();
        loadSavedCharacter();
        CurrentBoard.loadCurrentBoard(quoteView);

        receiveButton.setEnabled(TimeSaver.isQuoteReady()); //button enabled ever 20 sec (draft)
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            getFragmentManager().beginTransaction().detach(this).attach(this).commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        receiveButton.setEnabled(TimeSaver.isQuoteReady());
    }


    private void initViewElements() {
        clipboardButton = getView().findViewById(R.id.clipboardButton);
        helpButton = getView().findViewById(R.id.helpButton);
        nameView = getView().findViewById(R.id.nameView);
        quoteView = getView().findViewById(R.id.sentenceView);
        characterView = getView().findViewById(R.id.characterView);
        receiveButton = getView().findViewById(R.id.recieveButton);
    }

    private void initOnClickButtonListeners() {
        receiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeSaver.saveTime();

                loadNewCharacter();
                receiveButton.setEnabled(false);
                animateNewQuote();
                TimeSaver.setNotification();
                StatsButtonClicks.increment();
            }
        });

        clipboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyQuoteToClipboard();
                Toast.makeText(getContext(), "Skopiowano do schowka!", Toast.LENGTH_SHORT).show();
            }
        });

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), HelpActivity.class));
                Animatoo.animateDiagonal(getContext());
            }
        });

    }

    private void copyQuoteToClipboard() {
        String text;
        text = "\"" + characterManager.getCharacterQuote() + "\"" + " ~" + characterManager.getCharacterName();
        ClipboardManager myClipboard = (ClipboardManager) getActivity().getSystemService(CLIPBOARD_SERVICE);
        ClipData myClip = ClipData.newPlainText("quote", text);
        myClipboard.setPrimaryClip(myClip);
    }

    private void animateNewQuote() {
        YoYo.with(Techniques.DropOut).duration(1500).playOn(nameView);
        YoYo.with(Techniques.Tada).duration(1500).delay(1000).playOn(quoteView);
        YoYo.with(Techniques.FadeInRight).duration(1500).playOn(characterView);
    }

    private void loadNewCharacter() {
        characterManager.setNewCharacter();
        nameView.setText(characterManager.getCharacterName());
        quoteView.setText(characterManager.getCharacterQuote());
        characterView.setImageDrawable(null);
        characterView.setImageDrawable(characterManager.getCharacterImage());
    }

    private void loadSavedCharacter() {
        characterManager.setSavedCharacter();
        nameView.setText(characterManager.getCharacterName());
        quoteView.setText(characterManager.getCharacterQuote());
        characterView.setImageDrawable(characterManager.getCharacterImage());
    }

    private void loadStats() {
        StatsReceivedQuotes.load();
        StatsButtonClicks.load();
        StatsUnlockedCharacters.load();
    }
}
