package com.github.javadojo;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

/**
 * @author : fabrice
 */
public class GpsMovementData {
    private Position currentPosition;
    private Direction currentOrientation;

    GpsMovementData(Position currentPosition, Direction currentOrientation) {
        this.currentPosition = currentPosition;
        this.currentOrientation = currentOrientation;
    }


    public GpsMovementData moveForward(Direction direction) {
        int xOffset = (int) cos(toRadians(direction.getDegrees()));
        int yOffset = (int) sin(toRadians(direction.getDegrees()));
        final Position newPosition = new Position(this.currentPosition.getX() + xOffset, this.currentPosition.getY()+yOffset);
        return new GpsMovementData(newPosition, direction);
    }

    public GpsMovementData turn(MarsRover.Turn direction) {
        Direction newOrientation = Direction.getDirectionFromDegrees(getDegreeOnCircle(this.currentOrientation.getDegrees() + direction.getDegrees()));
        return new GpsMovementData(this.currentPosition, newOrientation);
    }

    private int getDegreeOnCircle(int degree) {
        int degreesOnCircle = degree % 360;
        degreesOnCircle = degreesOnCircle>=0?degreesOnCircle:degreesOnCircle+360;
        return degreesOnCircle;
    }


    public Position getCurrentPosition() {
        return currentPosition;
    }

    public Direction getCurrentOrientation() {
        return currentOrientation;
    }


    @Override
    public String toString() {
        return String.format("[pos : cur=%s|last=%s], [orientation : cur=%s|lastMove=%s]", this.currentPosition, this.currentOrientation);
    }
}
