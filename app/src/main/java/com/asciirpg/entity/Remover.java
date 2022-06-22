package com.asciirpg.entity;

import com.asciirpg.util.Position;

import java.util.Random;

/**
 * Class for remover properties. Increases map space if encountered.
 */
public class Remover extends Entity {

    // Default constructor
    public Remover() {
        this.icon = '+';
        this.pos = new Position(3, 3);
        generateID();
    }

    // Parametrized constructor
    public Remover(Position pos) {
        this.icon = '+';
        this.pos = pos;
        generateID();
    }

    public void generateID() {
        Random numGen = new Random();
        this.ID = "r"
                + String.valueOf(numGen.nextInt(10))
                + String.valueOf(numGen.nextInt(10))
                + String.valueOf(numGen.nextInt(10))
                + String.valueOf(numGen.nextInt(10));
    }

}
