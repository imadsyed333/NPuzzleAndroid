package com.example.npuzzle;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;

@SuppressLint("ViewConstructor")
public class BoardView extends GridLayout implements View.OnClickListener {
    private final Board board;
    Context context;
    AppCompatActivity app;

    public BoardView(Context context, Board board, int size, AppCompatActivity app) {
        super(context);
        this.context = context;
        this.board = board;
        this.app = app;
        setRowCount(size);
        setColumnCount(size);
        setOrientation(GridLayout.HORIZONTAL);
        generateBoard();
    }

    protected void generateBoard() {
        for (Cell[] row : this.board.getBoard()) {
            for (Cell cell : row) {
                cell.setOnClickListener(this);
                addView(cell);
            }
        }
    }

    protected boolean isPuzzleSolved() {
        return this.board.isBoardSolved();
    }
    @Override
    public void onClick(View v) {
        Cell tile = (Cell) v;
        this.board.move(tile);
        if (isPuzzleSolved()) {
            endGame();
        }
    }

    public void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setTitle("Yay! You solved the puzzle!");
        builder.setPositiveButton("Return to main menu", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                app.finish();
            }
        });
        builder.show();
    }
}
