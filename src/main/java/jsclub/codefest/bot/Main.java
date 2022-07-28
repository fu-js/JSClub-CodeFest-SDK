package jsclub.codefest.bot;

import io.socket.emitter.Emitter.Listener;
import jsclub.codefest.bot.constant.GameConfig;
import jsclub.codefest.sdk.algorithm.AStarSearch;
import jsclub.codefest.sdk.model.Hero;
import jsclub.codefest.sdk.socket.data.GameInfo;
import jsclub.codefest.sdk.socket.data.MapInfo;
import jsclub.codefest.sdk.socket.data.Position;
import jsclub.codefest.sdk.util.GameUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
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
        final String SERVER_URL = "https://codefest.jsclub.me/";
        Hero player1 = new Hero("player1-xxx", GameConfig.GAME_ID);
        AStarSearch aStarSearch = new AStarSearch();

        Listener onTickTackListener = objects -> {
            GameInfo gameInfo = GameUtil.getGameInfo(objects);
            MapInfo mapInfo = gameInfo.getMapInfo();

            Position currentPosition = mapInfo.getCurrentPosition(player1);
            Position enemyPosition = mapInfo.getEnemyPosition(player1);

            List<Position> restrictPosition =  new ArrayList<Position>();

            String path = aStarSearch.aStarSearch(mapInfo.mapMatrix, restrictPosition, currentPosition, enemyPosition);

            // Set path length to 5 if length larger
            // if (path.length() > 5) {
            //     path = path.substring(0, 5);
            // }

            // Random if path is empty
//            if (path.isEmpty()) {
//                System.out.println("Random path");
//                path = getRandomPath(5);
//            }

            player1.move(path);
        };
        player1.setOnTickTackListener(onTickTackListener);
        player1.connectToServer(SERVER_URL);
    }
}
