package jsclub.codefest.sdk.socket.data;

public class Position {
    private final int LEFT_POSITION = 1;
    private final int RIGHT_POSITION = 2;
    private final int UP_POSITION = 3;
    private final int DOWN_POSITION = 4;

    public int col;
    public int row;

    public int getX() {
        return col;
    }

    public int getY() {
        return row;
    }

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
