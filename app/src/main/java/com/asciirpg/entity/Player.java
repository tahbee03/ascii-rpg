package com.asciirpg.entity;

import com.asciirpg.util.Position;

/**
 * Class for player properties.
 */
public class Player extends Entity {

    // Data member(s)
    protected int HP;
    protected int score;
    protected Position prevPos;

    // Default constructor
    public Player() {
        this.icon = '@';
        this.HP = 100;
        this.pos = new Position(3, 3);
        this.prevPos = new Position(0, 0);
        this.score = 0;
    }

    // Parametrized constructor
    public Player(int HP, Position pos) {
        this.icon = '@';
        this.HP = HP;
        this.pos = pos;
        this.prevPos = new Position(0, 0);
        this.score = 0;
    }

    // Setter(s)
    public void setHP(int h) {
        this.HP = h;
    }
    public void storePos() {
        this.prevPos.setRow(this.pos.getRow());
        this.prevPos.setCol(this.pos.getCol());
    }
    public void updateScore() {
        this.score += 1;
    }

    // Getter(s)
    public int getHP() {
        return this.HP;
    }
    public Position getPrevPos() {
        return this.prevPos;
    }
    public int getScore() {
        return this.score;
    }
}
