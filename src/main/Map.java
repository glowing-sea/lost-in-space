package src.main;

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
     * @param loc the location that a character wants to go
     * @return true if the character can go there
     */
    public Boolean reachable(Location loc){ //cannot pass -, |, +
        int X = loc.getX();
        int Y = loc.getY();
        if (X < 0 || X > 8 || Y < 0 || Y > 8)
            return false;
        for (char wall : walls){
            if (map[X].charAt(Y) == wall)
                return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        StringBuilder output = new StringBuilder();
//        output.append("====================\n");
//        for (int i = 0; i < map.length; i++) {
//            StringBuilder line = new StringBuilder();
//            line.append('‖');
//            for (int j = 0; j < map[i].length(); j++) {
//                line.append(map[i].charAt(j)).append(" ");
//            }
//            output.append(line).append('‖').append("\n");
//        }
//        output.append("====================\n");
//        return output.toString();
//    }
}
