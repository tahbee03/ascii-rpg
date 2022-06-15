package com.asciirpg.util;

import android.util.Log;
import android.widget.TextView;
import com.asciirpg.entity.Entity;
import com.asciirpg.entity.Player;
import java.util.ArrayList;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.graphics.Color;

/**
 * Game map is created using "matrix" and Position class.
 */
public class Map {

    // TODO: Add boolean value that represents whether or not a position is occupied already

    // Data member(s)
    private ArrayList<TextView> rows;

    // Default constructor
    public Map() {
        rows = new ArrayList<>();
    }

    // Adds row of text to map
    public void pushRow(TextView t) {
        rows.add(t);
    }

    // Returns a row of text
    public TextView getRow(int i) {
        return rows.get(i - 1);
    }

    // Instantiates entities onto map
    public void draw(Entity e, Position p) {
        String row_text = rows.get(p.getRow() - 1).getText().toString();
        int col = p.getCol() - 1;
        row_text = row_text.substring(0, col)
                    + e.getIcon()
                    + row_text.substring(col + 1);
        rows.get(p.getRow() - 1).setText(row_text);
    }

    // Returns the character at a specific position
    public char read(Position p) {
        return this.getRow(p.getRow()).getText().toString().charAt(p.getCol() - 1);
    }

    // Gives color to entities
    public void processColor() {
        // TODO: Fix so that colors don't temporarily disappear
        /*
        String text = this.getRow(e.getPos().getRow()).getText().toString();

        // SOURCE: https://www.youtube.com/watch?v=tTLmz-JKxsI
        SpannableString ss = new SpannableString(text);
        ForegroundColorSpan fcs;
        if(e instanceof Player) fcs = new ForegroundColorSpan(Color.BLUE);
        else fcs = new ForegroundColorSpan(Color.RED);
        ss.setSpan(fcs, e.getPos().getCol() - 1, e.getPos().getCol(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        this.getRow(e.getPos().getRow()).setText(ss);
         */
        ForegroundColorSpan blue = new ForegroundColorSpan(Color.BLUE);
        ForegroundColorSpan red = new ForegroundColorSpan(Color.RED);
        ForegroundColorSpan black = new ForegroundColorSpan(Color.BLACK);

        for(int i = 0; i < this.rows.size(); i++) { // Iterates through every row of text
            String text = rows.get(i).getText().toString();
            SpannableString ss = new SpannableString(text);
            for(int j = 0; j < text.length(); j++) { // Iterates through every character in each row
                switch(text.charAt(j)) {
                    case '@':
                        ss.setSpan(blue, j, j + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        Log.d("processColor()", "Setting (" + String.valueOf(i + 1)
                            + ", " + String.valueOf(j + 1) + ") to BLUE");
                        break;
                    case '#':
                        ss.setSpan(red, j, j + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        Log.d("processColor()", "Setting (" + String.valueOf(i + 1)
                                + ", " + String.valueOf(j + 1) + ") to RED");
                        break;
                    default:
                        ss.setSpan(black, j, j + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        Log.d("processColor()", "Setting (" + String.valueOf(i + 1)
                                + ", " + String.valueOf(j + 1) + ") to BLACK");
                }
            }
            rows.get(i).setText(ss);
        }
    }

}
