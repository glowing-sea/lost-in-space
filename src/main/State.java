package main;
import java.util.List;

/**
 * An object shoring the current game state
 */

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

    /**
     * Check whether the game state is a finish state (last state of the whole game).
     * @return ture or false
     */
    public Boolean isFinish(){ // check the player win or not
        return level == GameConfiguration.FINISH_REQUIREMENT;
    }

    /**
     * Update the current game state according to the input game state
     * Any fields in the new game state that has null or Integer.MIN_VALUE means that this field do not need to update.
     * @param newGameState the next game state.
     */
    public void gameLevelUp(State newGameState){
        if (newGameState.map != null)
            map = newGameState.map;
        if (newGameState.enemies != null)
            enemies = newGameState.enemies;
        level = newGameState.level;
        dialogue = newGameState.dialogue;

        // Update player state in the next level
        Player newStats = newGameState.player;
        if (newStats.getName() != null) this.player.setName(newStats.getName());
        if (newStats.getHp() != Integer.MIN_VALUE) this.player.setHp(newStats.getHp());
        if (newStats.getAtk() != Integer.MIN_VALUE) this.player.setAtk(newStats.getAtk());
        if (newStats.getDef() != Integer.MIN_VALUE) this.player.setDef(newStats.getDef());
        if (newStats.getLoc() != null) this.player.setLoc(newStats.getLoc());
        if (newStats.getExp() != Integer.MIN_VALUE) this.player.setExp(newStats.getExp());
        if (newStats.getPlayerLevel() != Integer.MIN_VALUE) this.player.setPlayerLevel(newStats.getPlayerLevel());
    }

    /**
     * Print a game state into the terminal
     * @return a graphical view of a game state.
     */
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
