package com.asciirpg.entity;

import com.asciirpg.util.Position;

/**
 * Class for blocker properties. Reduces map space.
 */
public class Blocker extends Entity {

    // Default constructor
    public Blocker() {
        this.icon = '#';
        this.pos = new Position(3, 3);
    }

    // Parametrized constructor
    public Blocker(Position pos) {
        this.icon = '#';
        this.pos = pos;
    }

}
