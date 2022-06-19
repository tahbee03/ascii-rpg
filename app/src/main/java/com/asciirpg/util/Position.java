package com.asciirpg.util;

import android.util.Log;
import androidx.annotation.NonNull;
import java.util.ArrayList;

/**
 * Class used to describe the position of each cell in the game map
 */
public class Position {

    // Data members
    private int row;
    private int col;
    // private boolean state;

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

    // Setter(s)
    public void setRow(int r) {
        this.row = r;
    }
    public void setCol(int c) {
        this.col = c;
    }

    // Getter(s)
    public int getRow() {
        return this.row;
    }
    public int getCol() {
        return this.col;
    }

    // Position class string representation
    @NonNull
    @Override
    public String toString() {
        return "(" + String.valueOf(this.row) + ", " + String.valueOf(this.col) + ")";
    }

    // Compares one Position object with another
    public boolean equals(Position p) {
        return (this.row == p.row && this.col == p.col);
    }

    // Returns a list of valid Position neighbors in a 3x3 radius
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
