package jsclub.codefest.sdk.socket.data;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

public class Dir {
    public static final String LEFT = "1";
    public static final String RIGHT = "2";
    public static final String UP = "3";
    public static final String DOWN = "4";
    public static final String DROP_BOMB = "b";
    public static final String INVALID = "";
    public static final String STOP = "x";

    public static final Map<String, String> MOVE_TO_STRING = new HashMap<String, String>() {
        {
            put(LEFT, "LEFT");
            put(RIGHT, "RIGHT");
            put(UP, "UP");
            put(DOWN, "DOWN");
            put(DROP_BOMB, "DROP BOMB");
            put(INVALID, "INVALID");
        }
    };

    public final String direction;

    public Dir(String dir) {
        this.direction = dir;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
