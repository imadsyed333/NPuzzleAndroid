package com.example.npuzzle;

import android.annotation.SuppressLint;
import android.content.Context;

@SuppressLint("ViewConstructor")
public class Cell extends androidx.appcompat.widget.AppCompatButton {
    private int num;
    private final int row;
    private final int col;

    public Cell(Context context, int num, int row, int col) {
        super(context);
        setNum(num);
        this.row = row;
        this.col = col;
        setMinHeight(getWidth());
        setTextSize(10);
    }

    public int getRow() {
        return this.row;
    }

    public int getCol() {
        return this.col;
    }

    public int getNum() {
        return this.num;
    }

    public String toString() {
        if (this.num == 0) {
            return " ";
        }
        return Integer.toString(this.num);
    }

    public void setImage() {
    }

    @SuppressLint("SetTextI18n")
    public void setNum(int num) {
        this.num = num;
        setClickable(num != 0);
        this.setText(toString());
    }
}
