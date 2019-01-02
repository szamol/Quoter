package com.example.szamol.quoter.Boards;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.szamol.quoter.R;

public class BoardsFragment extends Fragment {

    private Button stoneboardButton;
    private Button papirusboardButton;
    private Button schoolboardButton;
    private Button whiteboardButton;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_boards, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        initViewElements();
        initOnClickListeners();
    }

    private void initViewElements() {
        stoneboardButton = getView().findViewById(R.id.stoneboardButton);
        papirusboardButton = getView().findViewById(R.id.papirusboardButton);
        schoolboardButton = getView().findViewById(R.id.schoolboardButton);
        whiteboardButton = getView().findViewById(R.id.whiteboardButton);
    }
    private void initOnClickListeners() {
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

    private void boardChangedToast(String boardName) {
        Toast.makeText(super.getContext(), "Ustawiono " + boardName + "!", Toast.LENGTH_SHORT).show();
    }


}
