package com.asciirpg.entity;

import com.asciirpg.util.Position;

public class Healer extends Entity {

    // Default constructor
    public Healer() {
        this.icon = '*';
        this.pos = new Position(3, 3);
    }

    // Parametrized constructor
    public Healer(Position pos) {
        this.icon = '*';
        this.pos = pos;
    }

}
