package com.asciirpg.entity;

import com.asciirpg.util.Position;

/**
 * Abstract class for basic entity properties.
 */
public abstract class Entity {

    /*
    TYPES:
    - player (@, blue)
    - healer (*, yellow)
    - detractor (%, brown)
    - blocker (#, red)
    - remover (+, green)
     */

    // Data member(s)
    protected char icon;
    protected Position pos;
    protected String ID;
    // TODO: Create a unique ID for all spawned entities to make it easier to search for them
    // THEN, create map to store IDs and positions
    // THEN, create algorithm to find entities with ID
    // THEN, use algorithm to implement Remover effect

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
    public String getID() {
        return this.ID;
    }

    abstract public void generateID();

}
