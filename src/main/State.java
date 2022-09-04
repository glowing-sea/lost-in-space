package src.main;
import java.util.List;

public class State {
    Map map;
    Player player;
    List<Enemy> enemies;
    int level;
    String dialogue;

    // A new game
    public State(Map map, Player player, List<Enemy> enemies, String dialogue, int level) {
        this.map = map;
        this.player = player;
        this.enemies = enemies;
        this.level = level;
        this.dialogue = dialogue;
    }

    public Boolean finish(){ // check the player win or not
        Location current = player.getLoc();
        Location finish = GameConfiguration.LEVEL0_LEVEL_UP.getLocation();
        return current.getX() == finish.getX() && current.getY() == finish.getY();
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();

        // Display Title
        output.append("+------------------+\n");

        // Display Dialogue
        output.append(dialogue).append("\n");


        // Display Map
        String[] m = map.getMap();
        int playerX = player.getLoc().getX();
        int playerY = player.getLoc().getY();
        // FIXME display enemies locations
        output.append("====================\n");
        for (int i = 0; i < m.length; i++) {
            StringBuilder line = new StringBuilder();
            line.append('‖');
            for (int j = 0; j < m[i].length(); j++) {

                // If the player is at this location, display X instead of others
                if (playerX == i && playerY == j){
                    line.append('X').append(" ");
                } else {
                    line.append(m[i].charAt(j)).append(" ");
                }
            }
            output.append(line).append('‖').append("\n");
        }
        output.append("====================\n");


        return output.toString();
    }
}
