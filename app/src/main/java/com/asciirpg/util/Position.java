package com.asciirpg.util;

public class Position {

    // Data members
    private int row;
    private int col;

    // Default constructor
    public Position() {
        this.row = 1;
        this.col = 1;
    }

    // Parametrized constructor
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Setters
    public void setRow(int r) {
        this.row = r;
    }
    public void setCol(int c) {
        this.col = c;
    }

    // Getters
    public int getRow() {
        return this.row;
    }
    public int getCol() {
        return this.col;
    }

}
