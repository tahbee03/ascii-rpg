package com.asciirpg.util;

/**
 * Keeps track of the frame number as moves are carried out.
 */
public class Clock {

    // Data member(s)
    private int frame;

    // Default constructor
    public Clock() {
        this.frame = 0;
    }

    // Getter(s)
    public int getFrame() {
        return this.frame;
    }

    // Moves to next frame
    public void nextFrame() {
        this.frame++;
    }

}
