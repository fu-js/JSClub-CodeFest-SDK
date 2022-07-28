package jsclub.codefest.sdk.socket.data;

public class Position {
    public int col;
    public int row;

    public int getX() {
        return col;
    }

    public int getY() {
        return row;
    }

    public Position(int col, int row) {
        this.col = col;
        this.row = row;
    }
}
