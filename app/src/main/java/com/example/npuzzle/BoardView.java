package com.example.npuzzle;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.GridLayout;

@SuppressLint("ViewConstructor")
public class BoardView extends GridLayout implements View.OnClickListener {
    private final Board board;

    public BoardView(Context context, Board board, int size) {
        super(context);
        this.board = board;
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

    @Override
    public void onClick(View v) {
        Cell tile = (Cell) v;
        this.board.move(tile);
    }
}
