package com.asciirpg.entity;

import com.asciirpg.util.Position;

/**
 * Class for player properties.
 */
public class Player extends Entity {

    // Data member(s)
    private int HP;
    private int score;

    // Default constructor
    public Player() {
        this.icon = '@';
        this.HP = 100;
        this.pos = new Position(3, 3);
        this.score = 0;
        generateID();
    }

    // Parametrized constructor
    public Player(int HP, Position pos) {
        this.icon = '@';
        this.HP = HP;
        this.pos = pos;
        this.score = 0;
        generateID();
    }

    // Setter(s)
    public void setHP(int h) {
        this.HP = h;
    }
    public void setScore(int s) {
        this.score = s;
    }

    // Getter(s)
    public int getHP() {
        return this.HP;
    }
    public int getScore() {
        return this.score;
    }

    public void generateID() {
        this.ID = "p0000";
    }
}
