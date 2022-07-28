package jsclub.codefest.sdk.model;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;
import jsclub.codefest.sdk.constant.ServerSocketConfig;
import jsclub.codefest.sdk.socket.data.Dir;
import jsclub.codefest.sdk.socket.data.Game;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import jsclub.codefest.sdk.util.SocketUtil;
import jsclub.codefest.sdk.constant.ServerConfig;
import org.json.JSONException;
import org.json.JSONObject;

public class Hero {
    private static final Logger LOGGER = LogManager.getLogger(Hero.class);
    private String playerName = "";
    private String gameID = "";
    private Socket socket;
    private Emitter.Listener onTickTackListener = objects -> {};

    public Hero(String playerName, String gameID) {
        this.playerName = playerName;
        this.gameID = gameID;
    }

    public void setOnTickTackListener(Emitter.Listener onTickTackListener) {
        this.onTickTackListener = onTickTackListener;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getGameID() {
        return gameID;
    }

    public Boolean connectToServer(String serverUrl) {
        if (socket != null) {
            socket.disconnect();
            socket = null;
        }
        socket = SocketUtil.init(serverUrl);

        if (socket == null) {
            LOGGER.error("Socket null - can't connect");
            return false;
        }

        socket.on(Socket.EVENT_CONNECT, objects -> {
            String gameParams = new Game(gameID, playerName).toString();
            try {
                socket.emit(ServerSocketConfig.JOIN_GAME, new JSONObject(gameParams));
                LOGGER.info("{} connected into game {}!", this.playerName, this.gameID);
            } catch (JSONException e) {
                LOGGER.error(e);
            }
        });
        socket.on(ServerSocketConfig.TICKTACK_PLAYER, onTickTackListener);
        socket.on(Socket.EVENT_CONNECT_ERROR, objects -> LOGGER.error("Connect Failed "+ objects[0].toString()));
        socket.on(Socket.EVENT_DISCONNECT, objects -> LOGGER.info("{} Disconnected!", this.playerName));

        socket.connect();
        return true;
    }

    public void move(String step) {
        if (socket != null && step.length() > 0) {
            Dir dir = new Dir(step);
            LOGGER.debug("Player = {} - Dir = {}", this.playerName, dir);
            try {
                socket.emit(ServerSocketConfig.DRIVE_PLAYER, new JSONObject(dir.toString()));
            } catch (JSONException e) {
                LOGGER.error(e);
            }
        }
    }
}
