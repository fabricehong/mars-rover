package com.github.javadojo;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : fabrice
 */
public class MiniMap {
    private Waypoints matrix;

    public MiniMap(Position landingZonePosition) {
        this.matrix = new Waypoints();

        setWaypointType(landingZonePosition, WaypointType.LANDING_ZONE);
    }

    public void addMoveForwardWaypoint(GpsMovementData roverGpsMovementData) {

        Direction direction = roverGpsMovementData.getCurrentOrientation();
        final Position position = roverGpsMovementData.getCurrentPosition();

        WaypointType waypointType = determineMovementWaypointType(direction, position);

        setWaypointType(position, waypointType);

        connectPathsAround(position);
    }

    private void connectPathsAround(Position position) {
        WaypointType wpNorth = position.offset(1)
    }

    private WaypointType determineMovementWaypointType(WaypointsAroundPosition waypointsAroundPosition, Position position) {
        WaypointType waypointType = null;

        if (Direction.EAST.equals(direction) || Direction.WEST.equals(direction)) {
            waypointType = WaypointType.HORIZONTAL;
        } else {
            waypointType = WaypointType.VERTICAL;
        }

        if (isCrossingOtherPaths(position, direction)) {
            waypointType = WaypointType.CORNER;
        }
        return waypointType;
    }


    public void markSamplePosition(Position position) {
        setWaypointType(position, WaypointType.SAMPLE);
    }

    private void setWaypointType(Position position, WaypointType waypointType) {
        WaypointType wp = this.matrix.get(position);

        // exception : cannot override landing zone
        if (WaypointType.LANDING_ZONE.equals(wp)) {
            return;
        }

        // all other cases
        this.matrix.put(position, waypointType);

    }

    private boolean isSimplePath(WaypointType waypointTypeToAdd) {
        return WaypointType.HORIZONTAL.equals(waypointTypeToAdd) || WaypointType.VERTICAL.equals(waypointTypeToAdd);
    }


    // I don't want to override toString
    public String stringRepresentation(Position currentPosition) {
        return new WaypointsRepresentation(currentPosition, this.matrix).toString();
    }
}

class WaypointsAroundPosition {
    WaypointType north;
    WaypointType south;
    WaypointType east;
    WaypointType west;

    public WaypointsAroundPosition(Waypoints waypoints, Position position) {
        north = waypoints.get(position.offset(0, 1));
        south = waypoints.get(position.offset(0, -1));
        east = waypoints.get(position.offset(1, 0));
        west = waypoints.get(position.offset(-1, 0));
    }

    public boolean isCrossingPath(Direction movementOrientation) {
        if (Direction.EAST.equals(movementOrientation) || Direction.WEST.equals(movementOrientation)) {
            if (north!= null || south != null) {
                return true;
            }
        } else if (Direction.NORTH.equals(movementOrientation) || Direction.SOUTH.equals(movementOrientation)) {
            if (east!= null || west != null) {
                return true;
            }
        }
        return false;
    }



}
