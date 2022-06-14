package com.asciirpg.entity;

import com.asciirpg.util.Position;

/**
 * Class for enemy properties.
 */
public class Enemy extends Entity {

    // Default constructor
    public Enemy() {
        this.icon = '#';
        this.HP = 100;
        this.pos = new Position(3, 3);
    }

    // Parametrized constructor
    public Enemy(int HP, Position pos) {
        this.icon = '#';
        this.HP = HP;
        this.pos = pos;
    }

}
