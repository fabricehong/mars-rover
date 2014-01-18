package com.github.javadojo;

import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * @author : fabrice
 */
public class WaypointsRepresentation {
    private Map<Position, WaypointType> matrix;
    private StringBuilder stringBuilder;
    private Integer lastY;
    private Integer lastX;


    public WaypointsRepresentation(Position currentPosition, Map<Position, WaypointType> waypoints) {
        this.matrix = new TreeMap<Position, WaypointType>(new WaypointRepresentationComparator());
        this.matrix.putAll(waypoints);
        this.matrix.put(currentPosition, WaypointType.CURRENT_POSITION);
    }

    @Override
    public String toString() {
        this.stringBuilder = new StringBuilder();
        lastY = null;
        lastX = 0;
        for (Position position : this.matrix.keySet()) {
            final WaypointType waypointType = this.matrix.get(position);
            reachYPosition(position.getY());
            reachXPosition(position);
            print(waypointType.getcode());
            lastX = position.getX();
        }
        print('\n');
        return stringBuilder.toString();
    }

    private void print(char getcode) {
        stringBuilder.append(getcode);
    }

    private void reachXPosition(Position position) {
        if (lastX !=null&& position.getX() > lastX + 1) {
            stringBuilder.append(StringUtils.repeat(" ", position.getX() - lastX));
        }
    }

    private void reachYPosition(int y) {
        if (lastY==null) {
            lastY = y;
        }
        while(lastY > y) {
            newLine();
        }
    }

    private void newLine() {
        print('\n');
        lastX = 0;
        lastY--;
    }
}
