package com.asciirpg.entity;

import com.asciirpg.util.Position;

/**
 * Abstract class for basic entity properties.
 */
public abstract class Entity {

    /*
    TYPES:
    - player (@, blue)
    - blocker (#, red)
    - remover (+, green)
    - healer (*, yellow)
    - detractor (%, brown)
     */

    // Data member(s)
    protected char icon;
    protected Position pos;

    // Setters
    public void setIcon(char c) {
        this.icon = c;
    }
    public void setPosition(Position p) {
        this.pos = p;
    }
    public void setPosition(int r, int c) {
        this.pos.setRow(r);
        this.pos.setCol(c);
    }

    // Getters
    public char getIcon() {
        return this.icon;
    }
    public Position getPos() {
        return this.pos;
    }

}
