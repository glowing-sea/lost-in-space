public class Map {
    private int mapID;
    private String[] map;

    public Map(int mapID, String[] map) {
        this.mapID = mapID;
        this.map = map;
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

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        output.append("====================\n");
        for (int i = 0; i < map.length; i++) {
            StringBuilder line = new StringBuilder();
            line.append('‖');
            for (int j = 0; j < map[i].length(); j++) {
                line.append(map[i].charAt(j)).append(" ");
            }
            output.append(line).append('‖').append("\n");
        }
        output.append("====================\n");
        return output.toString();
    }
}
