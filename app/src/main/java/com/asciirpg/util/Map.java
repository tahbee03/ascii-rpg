package com.asciirpg.util;

import android.widget.TextView;

import com.asciirpg.entity.Entity;

import java.util.ArrayList;

public class Map {

    // Data member(s)
    private ArrayList<TextView> rows;

    // Default constructor
    public Map() {
        rows = new ArrayList<>();
    }

    public void pushRow(TextView t) {
        rows.add(t);
    }

    public TextView getRow(int i) {
        return rows.get(i - 1);
    }

    public void draw(Entity e, Position p) {
        String row_text = rows.get(p.getRow() - 1).getText().toString();
        int col = p.getCol() - 1;
        row_text = row_text.substring(0, col)
                    + e.getIcon()
                    + row_text.substring(col + 1);
        rows.get(p.getRow() - 1).setText(row_text);
    }

    public char read(Position p) {
        return this.getRow(p.getRow()).getText().toString().charAt(p.getCol() - 1);
    }

}
