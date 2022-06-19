package com.asciirpg.entity;

import com.asciirpg.util.Position;

public class Remover extends Entity {

    // Default constructor
    public Remover() {
        this.icon = '+';
        this.pos = new Position(3, 3);
    }

    // Parametrized constructor
    public Remover(Position pos) {
        this.icon = '+';
        this.pos = pos;
    }

}
