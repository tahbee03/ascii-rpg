package com.asciirpg.entity;

import com.asciirpg.util.Position;

import java.util.Random;

/**
 * Class for detractor properties. Decreases player health if encountered.
 */
public class Detractor extends Entity{

    // Default constructor
    public Detractor() {
        this.icon = '%';
        this.pos = new Position(3, 3);
        generateID();
    }

    // Parametrized constructor
    public Detractor(Position pos) {
        this.icon = '%';
        this.pos = pos;
        generateID();
    }

    public void generateID() {
        Random numGen = new Random();
        this.ID = "d"
                + String.valueOf(numGen.nextInt(10))
                + String.valueOf(numGen.nextInt(10))
                + String.valueOf(numGen.nextInt(10))
                + String.valueOf(numGen.nextInt(10));
    }

}
