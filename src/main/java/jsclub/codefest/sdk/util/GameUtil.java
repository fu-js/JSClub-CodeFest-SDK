package jsclub.codefest.sdk.util;

import com.google.gson.Gson;
import jsclub.codefest.sdk.socket.data.GameInfo;

public class GameUtil {
    public static GameInfo getGameInfo(Object... objects) {
        if (objects != null && objects.length != 0) {
            String data = objects[0].toString();
            GameInfo gameInfo = new Gson().fromJson(data, GameInfo.class);

            return gameInfo;
        }

        return null;
    }
}
