package com.github.javadojo;

/**
 * @author : fabrice
 */
public enum WaypointType {
    LANDING_ZONE('X'), VERTICAL('|'), HORIZONTAL('-'), CORNER('+'), SAMPLE('S'), CURRENT_POSITION('*');

    private char representation;

    private WaypointType(char representation) {
        this.representation = representation;
    }

    public char getcode() {
        return representation;
    }
}
