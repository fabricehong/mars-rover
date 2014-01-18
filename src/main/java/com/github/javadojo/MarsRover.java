package com.github.javadojo;

/**
 * The Mars rover is programmed to drive around Mars.
 * Its programming is very simple. The commands are the following:
 * <dl>
 *     <dt>s</dt>
 *     <dd>drive in a straight line</dd>
 *     <dt>r</dt>
 *     <dd>turn right</dd>
 *     <dt>l</dt>
 *     <dd>turn left</dd>
 * </dl>
 *
 * Note that the Mars rover always land at the <code>X</code> and starts by facing east.
 * 
 * The Mars rover can send a 2D string representation of its path back to Mission Control. The following character are
 * used with the following meanings:
 * <dl>
 *     <dt>X</dt>
 *     <dd>where the Mars rover landed</dd>
 *     <dt>*</dt>
 *     <dd>current position of the Mars rover</dd>
 *     <dt>-</dt>
 *     <dd>path in the west-east direction</dd>
 *     <dt>|</dt>
 *     <dd>path in the north-south direction</dd>
 *     <dt>+</dt>
 *     <dd>a place where the Mars rover turned or a crossroad</dd>
 *     <dt>S</dt>
 *     <dd>a place where a sample was taken</dd>
 * </dl>
 */
public class MarsRover {

    public enum Turn {
        LEFT(90), RIGHT(-90);

        private int degrees;

        private Turn(int degrees) {
            this.degrees = degrees;
        }

        public int getDegrees() {
            return degrees;
        }
    }

    static final String LINE_SEPARATOR = System.getProperty("line.separator");

    private GpsMovementData gpsData;
    private MiniMap miniMap;

    public MarsRover(String operations) {
        this.gpsData = new GpsMovementData(new Position(0, 0), Direction.EAST);
        this.miniMap = new MiniMap(this.gpsData.getCurrentPosition());
        runProgram(operations);
    }

    private void runProgram(String operations) {
        for (char c : operations.toCharArray()) {
            switch(c) {
                case 'l':
                    turnLeft();
                    break;
                case 'r':
                    turnRight();
                    break;
                case 's':
                    moveForward();
                    break;
                case 'S':
                    takeSample();
                    break;
                default:
                    throw new RuntimeException(String.format("Unknown command '%s'", c));
            }
        }
    }

    public String path() {
        return this.miniMap.stringRepresentation(gpsData.getCurrentPosition());
    }

    public MarsRover turnLeft() {
        this.gpsData = gpsData.turn(Turn.LEFT);
        return this;
    }

    public MarsRover turnRight() {
        this.gpsData = gpsData.turn(Turn.RIGHT);
        return this;
    }

    public MarsRover moveForward() {
        this.gpsData = gpsData.moveForward(gpsData.getCurrentOrientation());

        this.miniMap.addMoveForwardWaypoint(this.gpsData);
        return this;
    }


    public MarsRover takeSample() {
        this.miniMap.markSamplePosition(this.gpsData.getCurrentPosition());
        return this;
    }
}
