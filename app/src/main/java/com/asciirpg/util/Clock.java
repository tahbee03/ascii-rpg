package com.asciirpg.util;

public class Clock {

    private int frame;

    public Clock() {
        this.frame = 0;
    }

    public int getFrame() {
        return this.frame;
    }

    public void nextFrame() {
        this.frame++;
    }

}
