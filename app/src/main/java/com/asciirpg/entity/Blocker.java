package com.asciirpg.entity;

import com.asciirpg.util.Position;

import java.util.Random;

/**
 * Class for blocker properties. Reduces map space.
 */
public class Blocker extends Entity {

    // Default constructor
    public Blocker() {
        this.icon = '#';
        this.pos = new Position(3, 3);
        generateID();
    }

    // Parametrized constructor
    public Blocker(Position pos) {
        this.icon = '#';
        this.pos = pos;
        generateID();
    }

    public void generateID() {
        Random numGen = new Random();
        this.ID = "b"
                + String.valueOf(numGen.nextInt(10))
                + String.valueOf(numGen.nextInt(10))
                + String.valueOf(numGen.nextInt(10))
                + String.valueOf(numGen.nextInt(10));
    }

}
