package jsclub.codefest.bot;

// import com.google.gson.Gson;
import io.socket.emitter.Emitter;
import jsclub.codefest.bot.constant.GameConfig;
import jsclub.codefest.sdk.model.Hero;
// import jsclub.codefest.sdk.socket.data.GameInfo;
// import jsclub.codefest.sdk.util.GameUtil;

import java.util.Random;

public class RandomPlayer {
    final static String SERVER_URL = "https://codefest.jsclub.me/";

    public static String getRandomPath(int length) {
        Random rand = new Random();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int random_integer = rand.nextInt(5);
            sb.append("1234b".charAt(random_integer));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Hero player2 = new Hero("player2-xxx", GameConfig.GAME_ID);
        Emitter.Listener onTickTackListener = objects -> {
            // GameInfo gameInfo = GameUtil.getGameInfo(objects);
            player2.move(getRandomPath(10));
        };
        player2.setOnTickTackListener(onTickTackListener);
        player2.connectToServer(SERVER_URL);
    }
}
