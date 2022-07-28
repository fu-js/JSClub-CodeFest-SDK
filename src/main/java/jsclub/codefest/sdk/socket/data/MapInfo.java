package jsclub.codefest.sdk.socket.data;

import com.google.gson.Gson;

import jsclub.codefest.sdk.constant.MapEncode;
import jsclub.codefest.sdk.model.Hero;
import java.util.ArrayList;
import java.util.List;

public class MapInfo {
    public String myId;
    public MapSize size;
    public List<Player> players;
    public List<int[]> map;
    public List<Bomb> bombs;
    public List<Spoil> spoils;
    public List<Gift> gifts;
    public List<Viruses> viruses;
    public List<Human> human;
    public int[][] mapMatrix;
    public List<Position> walls = new ArrayList<>();
    public List<Position> balk = new ArrayList<>();
    public List<Position> blank = new ArrayList<>();
    public List<Position> teleportGate = new ArrayList();
    public List<Position> quarantinePlace = new ArrayList();

    public List<Viruses> getVirus() {
        return viruses;
    }

    public void updateMapInfo() {
        int[][] mapMatrix = new int[size.rows][size.cols];
        for (int i = 0; i < size.rows; i++) {
            mapMatrix[i] = map.get(i);
            for (int j = 0; j < size.cols; j++) {
                switch (mapMatrix[i][j]) {
                    case MapEncode.ROAD:
                        blank.add(new Position(j,i));
                        break;
                    case MapEncode.WALL:
                        walls.add(new Position(j,i));
                        break;
                    case MapEncode.BALK:
                        balk.add(new Position(j,i));
                        break;
                    case MapEncode.TELEPORT_GATE:
                        teleportGate.add(new Position(j,i));
                        break;
                    case MapEncode.QUARANTINE_PLACE:
                        quarantinePlace.add(new Position(j,i));
                        break;
                    default:
                        walls.add(new Position(j,i));
                        break;
                }
            }
        }
        this.mapMatrix = mapMatrix;
    }

    /**
     * It returns the position of the enemy hero
     * 
     * @param hero The hero object that you are controlling.
     * @return The position of the enemy hero.
     */
    public Position getEnemyPosition(Hero hero) {
        Position position = null;
        if (hero != null) {
            for (Player player : players) {
                if (!hero.getPlayerID().startsWith(player.id)) {
                    position = player.currentPosition;
                    break;
                }
            }
        }
        return position;
    }

    /**
     * It returns the current position of a hero
     * 
     * @param hero The hero object that you want to get the position of.
     * @return The current position of the hero.
     */
    public Position getCurrentPosition(Hero hero) {
        Position position = null;
        if (hero != null) {
            for (Player player : players) {
                if (hero.getPlayerID().startsWith(player.id)) {
                    position = player.currentPosition;
                    break;
                }
            }
        }
        return position;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
