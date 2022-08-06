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
    public List<Position> teleportGate = new ArrayList<>();
    public List<Position> quarantinePlace = new ArrayList<>();

    /**
     * This function returns a list of viruses
     * 
     * @return The list of viruses.
     */
    public List<Viruses> getVirus() {
        return viruses;
    }

    /**
     * It takes a 2D array of integers, and based on the value of each integer, it adds the position of
     * that integer to a list
     */
    public void updateMapInfo() {
        int[][] mapMatrix = new int[size.rows][size.cols];
        for (int i = 0; i < size.rows; i++) {
            mapMatrix[i] = map.get(i);
            for (int j = 0; j < size.cols; j++) {
                switch (mapMatrix[i][j]) {
                    case MapEncode.ROAD:
                        blank.add(new Position(j, i));
                        break;
                    case MapEncode.WALL:
                        walls.add(new Position(j, i));
                        break;
                    case MapEncode.BALK:
                        balk.add(new Position(j, i));
                        break;
                    case MapEncode.TELEPORT_GATE:
                        teleportGate.add(new Position(j, i));
                        break;
                    case MapEncode.QUARANTINE_PLACE:
                        quarantinePlace.add(new Position(j, i));
                        break;
                    default:
                        walls.add(new Position(j, i));
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

    /**
     * This function returns a list of players
     * 
     * @return The list of players.
     */
    public List<Player> getPlayers() {
        return players;
    }

    /**
     * This function returns a list of bombs.
     * 
     * @return The bombs list.
     */
    public List<Bomb> getBombs() {
        return bombs;
    }

    /**
     * This function returns a list of spoils
     * 
     * @return The spoils list.
     */
    public List<Spoil> getSpoils() {
        return spoils;
    }

    /**
     * This function returns a list of gifts
     * 
     * @return A list of gifts.
     */
    public List<Gift> getGifts() {
        return gifts;
    }

    /**
     * This function returns a list of humans
     * 
     * @return The list of humans.
     */
    public List<Human> getHuman() {
        return human;
    }

    /**
     * It returns a list of all the infected humans in the current human list
     * 
     * @return A list of infected humans.
     */
    public List<Human> getDhuman() {
        List<Human> dhumanList = new ArrayList<>();
        if (human != null) {
            for (Human dhuman : human) {
                if (dhuman.infected) {
                    dhumanList.add(dhuman);
                }
            }
        }
        return dhumanList;
    }

    /**
     * This function returns a list of positions that represent the walls of the maze.
     * 
     * @return The list of walls.
     */
    public List<Position> getWalls() {
        return walls;
    }

    /**
     * This function returns a list of positions that are the balk
     * 
     * @return The list of positions of the balk.
     */
    public List<Position> getBalk() {
        return balk;
    }

    /**
     * It returns a list of positions that are blank
     * 
     * @return The list of blank positions.
     */
    public List<Position> getBlank() {
        return blank;
    }

    /**
     * It returns a list of positions
     * 
     * @return The list of positions.
     */
    public List<Position> getTeleportGate() {
        return teleportGate;
    }

    /**
     * This function returns a list of positions that are the quarantine places
     * 
     * @return The list of positions of the quarantine places.
     */
    public List<Position> getQuarantinePlace() {
        return quarantinePlace;
    }

    /**
     * It return the first player whose id starts with the given key
     * 
     * @param key The key of the player you want to get.
     * @return The player object.
     */
    public Player getPlayerByKey(String key) {
        Player player = null;
        if (players != null) {
            for (Player p : players) {
                if (key.startsWith(p.id)) {
                    player = p;
                    break;
                }
            }
        }
        return player;
    }

    /**
     * It returns a list of all the positions that are affected by bombs
     * 
     * @return A list of positions that are bombs and the effective range of the bombs.
     */
    public List<Position> getBombList() {
        List<Position> output = new ArrayList<>();
        for (Bomb bomb : this.getBombs()) {
            output.add(bomb);
            Player player = getPlayerByKey(bomb.playerId);
            for (int d = 1; d < 5; d++) {
                for (int p = 1; p <= player.power; p++) {
                    Position effBomb = bomb.nextPosition(d, p);
                    output.add(effBomb);
                }
            }
        }
        return output;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
