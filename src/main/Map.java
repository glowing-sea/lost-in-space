package src.main;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * An object storing a map in a game
 */

public class Map {
    private int mapID;

    // If a location (x,y) contain one of the characters in walls, this location is unreachable.
    private char[] walls;

    // Map is stored as an array (size 9) of string (size 9)
    private String[] map;

    public Map(int mapID, String[] map, char[] walls) {
        this.mapID = mapID;
        this.map = map;
        this.walls = walls;
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
