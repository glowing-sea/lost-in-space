package base;

import java.util.Arrays;

/**
 * An object storing a map in a game
 */

public class Map {
    private int mapID;

    // If a location (x,y) contain one of the characters in walls, this location is unreachable.
    private char[] walls;

    // Map is stored as an array (size 9) of string (size 9)
    private String[] map;

    /**
     * @author Haoting Chen
     * check if map == map
     * @param obj map
     * @return true if map == map
     */
    @Override
    public boolean equals(Object obj){
        if (! (obj instanceof Map m))
            return false;
        return this.mapID == m.getMapID() &&
                Arrays.equals(this.walls, m.getWalls()) &&
                Arrays.equals(this.map, m.getMap());
    }

    public Map(int mapID, String[] map, char[] walls) {
        if (map == null)
            map = GameConfiguration.DEFAULT_MAP;
        if (walls == null)
            walls = GameConfiguration.DEFAULT_WALLS;

        if (!isMapWellFormed(map))
            throw new IllegalArgumentException("The map is not well-formed.");

        this.mapID = mapID;
        this.map = map;
        this.walls = walls;
    }

    /**
     * @author Haoting Chen
     * check if the map is well-formed or not
     * @param map
     * @return ture if map is well-formed
     */
    public static boolean isMapWellFormed (String[] map){
        if (map.length != 9)
            return false;
        for (String line : map){
            if (line.length() != 9)
                return false;
        }
        return true;
    }


    // Getter and setter methods
    public int getMapID() {
        return mapID;
    }
    public String[] getMap() {
        return map;
    }
    public void setMapID(int mapID) {
        this.mapID = mapID;
    }
    public void setMap(String[] map) {
        this.map = map;
    }

    public char[] getWalls() {
        return walls;
    }
    public void setWalls(char[] walls) {
        this.walls = walls;
    }


    /**
     * @author Zhishang Bian (created)
     * @author Haoting Chen (refactor)
     * Check if a location is reachable in the map
     * @param location the location that a character wants to go
     * @return true if the character can go there
     */
    public Boolean reachable(Location location){ //cannot pass -, |, +
        int X = location.getX();
        int Y = location.getY();
        if (X < 0 || X > 8 || Y < 0 || Y > 8)
            return false;
        for (char wall : walls){
            if (map[X].charAt(Y) == wall)
                return false;
        }
        return true;
    }

}
