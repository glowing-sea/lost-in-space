package src.main;


public class Map {
    private int mapID;

    private char[] walls;
    private String[] map;

    public Map(int mapID, String[] map, char[] walls) {
        this.mapID = mapID;
        this.map = map;
        this.walls = walls;
    }

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

    public char[] getWalls() {
        return walls;
    }

    public void setWalls(char[] walls) {
        this.walls = walls;
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
