package com.asciirpg.entity;

import com.asciirpg.util.Position;

import java.util.Random;

/**
 * Class for healer properties. Increases player health if encountered.
 */
public class Healer extends Entity {

    // Default constructor
    public Healer() {
        this.icon = '*';
        this.pos = new Position(3, 3);
        generateID();
    }

    // Parametrized constructor
    public Healer(Position pos) {
        this.icon = '*';
        this.pos = pos;
        generateID();
    }

    public void generateID() {
        Random numGen = new Random();
        this.ID = "h"
                + String.valueOf(numGen.nextInt(10))
                + String.valueOf(numGen.nextInt(10))
                + String.valueOf(numGen.nextInt(10))
                + String.valueOf(numGen.nextInt(10));
    }

}
