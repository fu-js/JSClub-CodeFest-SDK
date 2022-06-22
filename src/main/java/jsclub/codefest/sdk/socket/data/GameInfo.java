package jsclub.codefest.sdk.socket.data;

import com.google.gson.Gson;

public class GameInfo {
    public static final String EXPLOSED_TAG = "bomb:explosed";
    public static final String BOMB_SETUP_TAG = "bomb:setup";
    public static final String START_TAG = "player:start-moving";
    public static final String STOP_TAG = "player:stop-moving";
    public static final String PICK_SPOIL = "player:pick-spoil";
    public static final String DESTROY_VIRUS = "player:destroy-virus";
    public String player_id;
    public MapInfo map_info;
    public String tag;
    public int id;
    public long timestamp;

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

    public MapInfo getMap_info() {
        map_info.updateMapInfo();
        return map_info;
    }
}
