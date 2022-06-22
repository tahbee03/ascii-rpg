package com.asciirpg.util;

import android.util.Log;
import android.widget.TextView;

import com.asciirpg.entity.Blocker;
import com.asciirpg.entity.Entity;
import com.asciirpg.entity.Player;
import java.util.*;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.graphics.Color;

// NOTE: I attempted to use a HashMap<Position, Integer> for the entity list, but it just overcomplicated things.

/**
 * Game map is created using "matrix" and Position class.
 */
public class GameMap {

    // Data member(s)
    private final ArrayList<TextView> rows;
    private final ArrayList<Entity> entityList;

    // Default constructor
    public GameMap() {
        rows = new ArrayList<>();
        entityList = new ArrayList<>();
    }

    // Adds row of text to map
    public void pushRow(TextView t) {
        rows.add(t);
    }

    // Adds an entity to the map
    public void pushEntity(Entity e) {
        entityList.add(e);
    }

    // Removes an entity from the map
    public void removeEntity(Entity e) {
        entityList.remove(e);
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

    // Returns the character at the given position
    public char readPos(Position p) {
        return rows.get(p.getRow() - 1).getText().toString().charAt(p.getCol() - 1);
    }

    // Determines whether an entity is at the given position or not
    public boolean isOccupied(Position p) {
        return readPos(p) != '-'; // true -> position is occupied; false -> position is not occupied
    }

    // Determines whether an entity can be "stepped on" or not
    public boolean isPassable(Position p) {
        return readPos(p) != '#';
    }

    // Applies the effect of the encountered entity
    public void applyEffect(Player p, char c) {
        switch(c) {
            case '+':
                // TODO: Implement Remover effect
                Log.d("EFFECT", "Encountered Remover!");
                boolean containsBlocker = false;
                for(Entity e : entityList) {
                    if (e instanceof Blocker) {
                        containsBlocker = true;
                        break;
                    }
                }

                if(containsBlocker) {
                    Random numGen = new Random();
                    int index;
                    do {
                        index = numGen.nextInt(entityList.size());
                    } while(!(entityList.get(index) instanceof Blocker));

                    draw('-', entityList.get(index).getPos());
                    entityList.remove(index);

                }
                break;
            case '*':
                Log.d("EFFECT", "Encountered Healer!");
                p.setHP(p.getHP() + 6);
                break;
            case '%':
                Log.d("EFFECT", "Encountered Remover!");
                p.setHP(p.getHP() - 9);
                break;
        }
    }

    // Gives color to entities
    public void processColor() {
        // BASED ON: https://www.youtube.com/watch?v=tTLmz-JKxsI

        for(int i = 0; i < this.rows.size(); i++) { // Iterates through every row of text
            String text = rows.get(i).getText().toString();
            SpannableString ss = new SpannableString(text);
            for(int j = 0; j < text.length(); j++) { // Iterates through every character in each row
                switch(text.charAt(j)) {
                    case '@':
                        ss.setSpan(new ForegroundColorSpan(Color.BLUE), j, j + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        break;
                    case '#':
                        ss.setSpan(new ForegroundColorSpan(Color.RED), j, j + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        break;
                    case '+':
                        ss.setSpan(new ForegroundColorSpan(Color.GREEN), j, j + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        break;
                    case '*':
                        ss.setSpan(new ForegroundColorSpan(Color.YELLOW), j, j + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        break;
                    case '%':
                        ss.setSpan(new ForegroundColorSpan(Color.rgb(159, 121, 83)), j, j + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        break;
                    default:
                        ss.setSpan(new ForegroundColorSpan(Color.BLACK), j, j + 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                }
            }
            rows.get(i).setText(ss);
        }
    }

}
