package jsclub.codefest.sdk.socket.data;

import com.google.gson.Gson;

public class Bomb extends Position {
    public String playerId;
    public int remainTime;

    public Bomb(int col, int row) {
        super(col, row);
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
