package com.example.npuzzle;

import android.content.Context;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Board {
    private Cell[][] board;
    private final int size;
    Context context;

    public Board(Context context, int size) {
        this.size = size;
        this.board = new Cell[size][size];
        this.context = context;
        loadBoard();
    }

    public void loadBoard() {
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i < this.size * this.size; i++) {
            nums.add(i);
        }
        nums.add(0);
        this.board = toArray(nums, this.size);
        generateBoardRandom();
    }

    public Cell[][] getBoard() {
        return this.board;
    }

//    public int getNumInversions(List<Integer> nums) {
//        int numInversions = 0;
//        for (int i = 0; i < nums.size() - 1; i++) {
//            int a = nums.get(i);
//            int b = nums.get(i + 1);
//            if (a > b) {
//                numInversions++;
//            }
//        }
//        return numInversions;
//    }

    public Cell[][] toArray(List<Integer> list, int rows) {
        int resultRows = list.size()/rows;
        if (list.size() % rows != 0) {
            resultRows++;
        }
        Cell[][] result = new Cell[resultRows][rows];
        int i = 0;
        int j = 0;
        for (Integer value : list) {
            result[i][j] = new Cell(this.context, value, i, j);
            j++;
            if(j > rows - 1){
                i++;
                j = 0;
            }
        }
        return result;
    }

    public boolean isBoardSolved() {
        List<Integer> nums = new ArrayList<>();
        for (Cell[] row : this.board) {
            for (Cell cell : row) {
                nums.add(cell.getNum());
            }
        }
        List<Integer> copyNums = new ArrayList<>(nums);
        Collections.sort(copyNums);
        copyNums.remove(0);
        copyNums.add(0);
        return nums.equals(copyNums);
    }

    public void move(Cell tile) {
        if (canMove(tile)) {
            Cell nullCell = getCellWithNum(0);
            nullCell.setNum(tile.getNum());
            tile.setNum(0);
        }
    }

    public boolean canMove(Cell cell) {
        Cell nullCell = getCellWithNum(0);
        int rowDistance = Math.abs(cell.getRow() - nullCell.getRow());
        int colDistance = Math.abs(cell.getCol() - nullCell.getCol());

        return (rowDistance == 0 && colDistance == 1) || (rowDistance == 1 && colDistance == 0);
    }

    public Cell getCellWithNum(int num) {
        for (Cell[] row : this.board) {
            for (Cell cell : row) {
                if (cell.getNum() == num) {
                    return cell;
                }
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Cell[] row : this.board) {
            for (Cell cell : row) {
                s.append('[').append(cell.toString()).append(']');
            }
            s.append('\n');
        }
        return '\n' + s.toString() + '\n';
    }

    public List<Cell> getMovableCells() {
        List<Cell> movableCells = new ArrayList<>();
        for (Cell[] row : this.board) {
            for (Cell cell : row) {
                if (canMove(cell)) {
                    movableCells.add(cell);
                }
            }
        }
        return movableCells;
    }

    public void generateBoardRandom() {
        List<Cell> movableCells;
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            movableCells = getMovableCells();
            System.out.println(this + " " + movableCells);
            Cell chosenCell = movableCells.get(random.nextInt(movableCells.size()));
            move(chosenCell);
        }
    }
}
