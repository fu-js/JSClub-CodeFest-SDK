package jsclub.codefest.sdk.util;

import com.google.gson.Gson;
import jsclub.codefest.sdk.socket.data.GameInfo;

public class GameUtil {
    /**
     * It takes an array of objects, checks if the array is not null and not empty, then converts the first
     * object in the array to a string, then converts that string to a GameInfo object, and finally returns
     * the GameInfo object
     * 
     * @return A GameInfo object
     */
    public static GameInfo getGameInfo(Object... objects) {
        if (objects != null && objects.length != 0) {
            String data = objects[0].toString();
            GameInfo gameInfo = new Gson().fromJson(data, GameInfo.class);

            return gameInfo;
        }

        return null;
    }
}
