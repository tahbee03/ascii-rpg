package com.asciirpg.entity;

import com.asciirpg.util.Position;

public abstract class Entity {

    protected char icon;
    protected int HP;
    protected Position pos;

    // Setters
    public void setIcon(char c) {
        this.icon = c;
    }
    public void setHP(int h) {
        this.HP = h;
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
    public int getHP() {
        return this.HP;
    }
    public Position getPos() {
        return this.pos;
    }

}
