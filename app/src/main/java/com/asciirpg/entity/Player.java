package com.asciirpg.entity;

import com.asciirpg.util.Position;

/**
 * Class for player properties.
 */
public class Player extends Entity {

    // Default constructor
    public Player() {
        this.icon = '@';
        this.HP = 100;
        this.pos = new Position(3, 3);
    }

    // Parametrized constructor
    public Player(int HP, Position pos) {
        this.icon = '@';
        this.HP = HP;
        this.pos = pos;
    }

}
