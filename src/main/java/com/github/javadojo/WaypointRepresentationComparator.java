package com.github.javadojo;

import java.util.Comparator;

/**
 * @author : fabrice
 */
public class WaypointRepresentationComparator implements Comparator<Position> {
    @Override
    public int compare(Position o1, Position o2) {
        if (o1==null) {
            return -1;
        }
        if (o2==null) {
            return 1;
        }
        if (o1.getY()==o2.getY()) {
            return o1.getX() - o2.getX();
        }
        return o2.getY() - o1.getY();
    }
}
