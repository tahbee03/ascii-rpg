package com.asciirpg.entity;

import com.asciirpg.util.Position;

/**
 * Class for remover properties. Increases map space if encountered.
 */
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
