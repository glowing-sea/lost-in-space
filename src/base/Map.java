package src.base;

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
    /**
     * input location, the char you will replace with
     * output replace the location with the char you set
     * @param location
     * @param newchar
     * @param st
     */
    // currently no function is using this due to refactoring on 10/9 by Zhishang
    // feel free to use this
    public static void refreshLocation(State st, Location location, char newchar){ // entre the new string and location where you want the string to be
        int X = location.getX();
        int Y = location.getY();
        Map map = st.map;
        if(X<0||X>8||Y<0||Y>8){
            throw new NullPointerException("this is <0 or >8");
        }else{
//            Map newmap = new Map(this.mapID,this.map,this.walls);
            String[] thenew = map.getMap();
            char[] newline = thenew[X].toCharArray();
            newline[Y] = newchar;
            thenew[X] = String.valueOf(newline);
            st.map = new Map(map.getMapID(),thenew,map.getWalls());

        }
    }
}
