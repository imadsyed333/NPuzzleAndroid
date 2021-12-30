package com.example.npuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class GameActivity extends AppCompatActivity {

    GridView boardView;
    int boardSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        boardView = findViewById(R.id.boardView);

        boardSize = getIntent().getIntExtra("boardSize", 3);
    }
}