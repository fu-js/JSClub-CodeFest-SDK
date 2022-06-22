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
    public List<Position> walls = new ArrayList<>();
    public List<Position> boxs = new ArrayList<>();
    public List<Position> blank = new ArrayList<>();
    public List<Position> selfisolatedZone = new ArrayList();

    public Player getPlayerByKey(String key) {
        Player player = null;
        if (players != null ) {
            for (Player p : players) {
                if (key.startsWith(p.id)) {
                    player = p;
                    break;
                }
            }
        }
        return player;
    }

    public List<Viruses> getVirus() {
        return viruses;
    }

    public List<Human> getDhuman() {
        List<Human> dhumanList = new ArrayList<>();
        if(human!=null) {
            for (Human dhuman : human) {
                if (dhuman.infected) {
                    dhumanList.add(dhuman);
                }
            }
        }
        return dhumanList;
    }

    public List<Human> getNHuman() {
        List<Human> nhumanList = new ArrayList<>();
        if(human!=null) {
            for (Human nhuman : human) {
                if (!nhuman.infected && nhuman.curedRemainTime == 0) {
                    nhumanList.add(nhuman);
                }
            }
        }
        return nhumanList;
    }

    public int[][] getMap() {
        int[][] map = new int[size.rows][size.cols];
        for (int i = 0; i < size.rows; i++) {
            map[i] = this.map.get(i);
            for (int j = 0; j < size.cols; j++) {
                if (map[i][j] == MapEncode.ROAD) {
                    blank.add(new Position(j,i));
                } else if (map[i][j] == MapEncode.WALL) {
                    walls.add(new Position(j,i));
                } else if (map[i][j] == MapEncode.BOX) {
                    boxs.add(new Position(j,i));
                } else if (map[i][j] == MapEncode.TELEPORT_GATE || map[i][j] == MapEncode.QUARANTINE_PLACE) {
                    selfisolatedZone.add(new Position(j,i));
                } else {
                    walls.add(new Position(j,i));
                }
            }
        }
        return map;
    }

    public Position getEnemyPosition(Hero hero) {
        Position position = null;
        if (hero != null) {
            for (Player player : players) {
                if (!hero.getPlayerName().startsWith(player.id)) {
                    position = player.currentPosition;
                    break;
                }
            }
        }
        return position;
    }

    public Position getCurrentPosition(Hero hero) {
        Position position = null;
        if (hero != null) {
            for (Player player : players) {
                if (hero.getPlayerName().startsWith(player.id)) {
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
