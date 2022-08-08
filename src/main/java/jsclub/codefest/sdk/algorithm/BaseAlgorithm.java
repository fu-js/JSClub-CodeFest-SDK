package jsclub.codefest.sdk.algorithm;

import jsclub.codefest.sdk.socket.data.Node;
import jsclub.codefest.sdk.socket.data.Dir;
import jsclub.codefest.sdk.socket.data.Position;

import java.util.Stack;

public class BaseAlgorithm {
    /**
     * Calculate Manhattan distance
     * 
     * @param src source position
     * @param des destination position
     * @return shortest distance
     */
    public static int manhattanDistance(Position src, Position des) {
        return Math.abs(src.getCol() - des.getCol()) + Math.abs(src.getRow() - des.getRow());
    }

    protected static String getStepsInString(Node first, Stack<Node> path) {
        StringBuilder steps = new StringBuilder();
        Node previousStep = first;
        int size = path.size();
        for (int i = 0; i <= size; i++) {
            if (path.size() > 0) {
                Node nextStep = path.pop();
                int x = nextStep.getCol();
                int y = nextStep.getRow();
                if (x > previousStep.getCol() && y == previousStep.getRow()) {
                    steps.append(Dir.RIGHT);
                }
                if (x < previousStep.getCol() && y == previousStep.getRow()) {
                    steps.append(Dir.LEFT);
                }
                if (x == previousStep.getCol() && y > previousStep.getRow()) {
                    steps.append(Dir.DOWN);
                }
                if (x == previousStep.getCol() && y < previousStep.getRow()) {
                    steps.append(Dir.UP);
                }
                previousStep = nextStep;
            } else {
                break;
            }
        }
        return steps.toString();
    }

    /**
     * Given two points, return the distance between them.
     * 
     * @param p1 The first point
     * @param p2 The second point
     * @return The distance between two points.
     */
    public static double distanceBetweenTwoPoints(Position p1, Position p2) {
        return distanceBetweenTwoPoints(p1.getCol(), p1.getRow(), p2.getCol(), p2.getRow());
    }

    private static double distanceBetweenTwoPoints(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
    }
}
