package com.example.npuzzle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class GameActivity extends AppCompatActivity {

    LinearLayout boardLayout;
    BoardView boardView;
    Board board;
    int boardSize;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        boardLayout = findViewById(R.id.boardLayout);

        boardSize = getIntent().getIntExtra("boardSize", 3);

        board = new Board(this, boardSize);

        boardView = new BoardView(this, board, boardSize);

        boardLayout.addView(boardView);

    }
}