package com.asciirpg.util;

import android.graphics.PostProcessor;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;

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

    @NonNull
    @Override
    public String toString() {
        return "(" + String.valueOf(this.row) + ", " + String.valueOf(this.col) + ")";
    }

    public boolean equals(Position p) {
        return (this.row == p.row && this.col == p.col);
    }

    public ArrayList<Position> getNeighbors() {
        Log.d("CURRENT POSITION", this.toString());
        ArrayList<Position> posList = new ArrayList<>();
        for(int i = -1; i <= 1; i++) { // Iterates through each of the rows
            for(int j = -1; j <= 1; j++) { // Iterates through each of the columns
                Position n = new Position(this.row + i, this.col + j);
                // int newRow = this.row + i;
                // int newCol = this.col + j;
                // Log.d("-", n.toString());
                if(!(n.row < 1) && !(n.col < 1)
                    && !(n.row > 5) && !(n.col > 5)
                    && !n.equals(this))
                    posList.add(new Position(this.row + i, this.col + j));
            }
        }

        for(Position neighbor : posList) {
            Log.d("NEIGHBOR", "(" + String.valueOf(neighbor.row)
                    + ", " + String.valueOf(neighbor.col) + ")");
        }
        return posList;
    }

}
