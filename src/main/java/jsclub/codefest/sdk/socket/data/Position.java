package jsclub.codefest.sdk.socket.data;

public class Position {
    public static final int LEFT_POSITION = 1;
    public static final int RIGHT_POSITION = 2;
    public static final int UP_POSITION = 3;
    public static final int DOWN_POSITION = 4;

    public int col;
    public int row;

    public int getX() {
        return col;
    }

    public int getY() {
        return row;
    }

    /**
     * Given a direction and a step, return the next position.
     * 
     * @param direction the direction of the next position
     * @param step the number of steps to move in the given direction
     * @return The next position in the direction of the step.
     */
    public Position nextPosition(int direction, int step) {
        switch (direction) {
            case LEFT_POSITION:
                return new Position(col - step, row);
            case RIGHT_POSITION:
                return new Position(col + step, row);
            case UP_POSITION:
                return new Position(col, row - step);
            case DOWN_POSITION:
                return new Position(col, row + step);
            default:
                return new Position(col, row);
        }
    }

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }
}
