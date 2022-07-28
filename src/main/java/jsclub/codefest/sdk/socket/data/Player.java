package jsclub.codefest.sdk.socket.data;

import com.google.gson.Gson;

public class Player {
    public String id;
    public Position spawnBegin;
    public Position currentPosition;
    public int speed;
    public int delay;
    public int score;
    public int power;
    public int lives;
    public int box;
    public int pill;
    public int human;
    public int pillUsed;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
