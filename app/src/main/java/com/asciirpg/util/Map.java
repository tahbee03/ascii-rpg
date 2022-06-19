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

    // Data member(s)
    private final ArrayList<TextView> rows;

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

    // Draws '-' onto map
    public void draw(char c, Position p) {
        String currRow = rows.get(p.getRow() - 1).getText().toString();
        int currColNum = p.getCol() - 1;
        currRow = currRow.substring(0, currColNum)
                + c
                + currRow.substring(currColNum + 1);
        rows.get(p.getRow() - 1).setText(currRow);
    }

    // Draws entities onto map
    public void draw(Entity e) {
        String currRow = rows.get(e.getPos().getRow() - 1).getText().toString();
        int currColNum = e.getPos().getCol() - 1;
        currRow = currRow.substring(0, currColNum)
                + e.getIcon()
                + currRow.substring(currColNum + 1);
        rows.get(e.getPos().getRow() - 1).setText(currRow);
    }

    // Determines whether an entity is at the given position or not
    public boolean getPosState(Position p) {
        int row = p.getRow() - 1;
        int col = p.getCol() - 1;
        String text = rows.get(row).getText().toString();
        char c = text.charAt(col);

        return c != '-'; // true -> position is occupied; false -> position is not occupied
    }

    // Gives color to entities
    public void processColor() {
        // SOURCE: https://www.youtube.com/watch?v=tTLmz-JKxsI

        // TODO: Fix so that colors don't temporarily disappear

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
                        // Log.d("processColor()", "Setting (" + String.valueOf(i + 1)
                        //     + ", " + String.valueOf(j + 1) + ") to BLUE");
                        break;
                    case '#':
                        ss.setSpan(red, j, j + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        // Log.d("processColor()", "Setting (" + String.valueOf(i + 1)
                        //        + ", " + String.valueOf(j + 1) + ") to RED");
                        break;
                    default:
                        ss.setSpan(black, j, j + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        // Log.d("processColor()", "Setting (" + String.valueOf(i + 1)
                        //         + ", " + String.valueOf(j + 1) + ") to BLACK");
                }
            }
            rows.get(i).setText(ss);
        }
    }

}
