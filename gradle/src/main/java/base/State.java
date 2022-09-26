package base;

import java.util.ArrayList;
import java.util.List;

/**
 * An object shoring the current game state
 */

public class State {
    Map map;
    String story; // The Main story at this level
    int level;
    Player player;
    List<Enemy> enemies;
    List<Item> items;
    List<NPC> NPCs;
    List<Merchant> merchants;



    // A new game
    public State(Map map, String story, int level, Player player, List<Enemy> enemies, List<Item> items, List<NPC> NPCs, List<Merchant> merchants) {
        this.map = map;
        this.player = player;
        this.enemies = enemies;
        this.level = level;
        this.story = story;
        this.items = items;
        this.NPCs = NPCs;
        this.merchants = merchants;
    }

    public String getStory() {
        return story;
    }
    public int getLevel() {
        return level;
    }

    /**
     * clear the stuff left in last level when we want to level up
     */
    public void clearall(){
        if(this.enemies!=null) {
            this.enemies.clear();
        }
        if(this.items !=null){
            this.items.clear();
        }
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
        story = newGameState.story;

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
        output.append(story).append("\n");


        // Display Map
        String[] m = map.getMap();
        int playerX = player.getLoc().getX();
        int playerY = player.getLoc().getY();

        // display enemies locations
        ArrayList<Location> enemiesloc = new ArrayList<>(); // where the enemies are
        if(this.enemies!=null) {
            for (Enemy enemy : this.enemies) {
                enemiesloc.add(enemy.getLoc());
            }
        }
        // display item locations
        ArrayList<Location> itemsloc = new ArrayList<>(); // where the items are
        if(this.items !=null) {
            for (Item item : this.items) {
                itemsloc.add(item.getLocation());
            }
        }
        output.append("====================\n");
        for (int i = 0; i < m.length; i++) {
            StringBuilder line = new StringBuilder();
            line.append('|');       // JDK 18 only: ‖
            for (int j = 0; j < m[i].length(); j++) {
                // Print Player
                if (player.getLoc().equals(new Location(i, j))){
                    line.append('X').append(" ");}
                // Print Enemies
                else if ((new Location(i,j).isin(enemiesloc))) {
                    line.append('E').append(" ");}
                // Print Items
                else if ((new Location(i,j).isin(itemsloc))) {
                    line.append('i').append(" ");}
                // Print NPCs
                else if ((new Location(i,j).isin(itemsloc))) {
                    line.append('i').append(" ");}
                // Print Merchants
                else if ((new Location(i,j).isin(itemsloc))) {
                    line.append('i').append(" ");}
                // Print Walls
                else {
                    line.append(m[i].charAt(j)).append(" ");
                }
            }
            output.append(line).append('|').append("\n");    // JDK 18 only: ‖
        }
        output.append("====================\n");

        return output.toString();
    }
}
