package base;

import utility.DisplayUtility;
import utility.Utility;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

    // Display Variable
    MessageBox messageBox; // Length must be <= 10
    int flexibleDisplay; // 0 for inventory, 1 for enemy stats. See toString for detail


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
        this.messageBox = new MessageBox();
        messageBox.putMessage("Need Help? Type 'tips'");
        flexibleDisplay = 0;
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

        /*
         1 ===========================================================================================================
         2 |                   Lost in Space                    |                       Messages                     |
         3 |====================================================|                                                    |
         4 |   Your Name    |------------------|   Enemy Name   | > Guilty: I'm not afraid of you                    |
         5 |     Jack       |  X     E - - - - |     Guilty     |                                                    |
         6 |                |                  |                |                                                    |
         7 |   LV: 2        |        + - -     |    LV: 3       |                                                    |
         8 |   HP: 100      |    i   | M       |    HP:         |                                                    |
         9 |   ATK: 40      |        + - - - - |    ATK:        |                                                    |
        10 |   DEF: 30      |    i   i N   A   |    DEF:        |                                                    |
        11 |   EXP: 300     |            A H A |    LOC: (6,6)  |                                                    |
        12 |   LOC: (6,6)   |- - - - - - - - - |                |                                                    |
        13 |                |------------------|                |                                                    |
        14 ===========================================================================================================
        15 | Story...............                                                                                    |
        16 |                                                                                                         |
        17 |                                                                                                         |
        18 ==========================================================================================================|
         */


        // Title Part
        String title = DisplayUtility.centerText(GameConfiguration.GAME_TITLE, 52);


        // My Stats Part
        String[] myStats = new String[10];
        myStats[0] = DisplayUtility.centerText("Your Name", 16);
        myStats[1] = DisplayUtility.centerText(this.player.getName(), 16);
        myStats[2] = " ".repeat(16);
        myStats[3] = "   " + DisplayUtility.fixLength("LV: " + this.player.getPlayerLevel(), 13);
        myStats[4] = "   " + DisplayUtility.fixLength("HP: " + this.player.getHp(), 13);
        myStats[5] = "   " + DisplayUtility.fixLength("ATK: " + this.player.getAtk(), 13);
        myStats[6] = "   " + DisplayUtility.fixLength("DEF: " + this.player.getDef(), 13);
        myStats[7] = "   " + DisplayUtility.fixLength("EXP: " + this.player.getExp(), 13);
        myStats[8] = "   " + DisplayUtility.fixLength("LOC: " + "(" + this.player.getLoc().getX() + "," + this.player.getLoc().getY() + ")", 13);
        myStats[9] = " ".repeat(16);

        // Inventory Part
        String[] inventory = new String[10];
        inventory[0] = DisplayUtility.centerText("Inventory", 16);
        inventory[1] = DisplayUtility.centerText(this.player.getInventory().size() + "/" + this.player.getCapacity(), 16);
        inventory[2] = " ".repeat(16);

        int count = 3; // The number of line that has text.
        for (Item item: this.player.getInventory()){
            inventory[count] = "  " + DisplayUtility.fixLength( count - 2 + ": " + item, 14);
            count++;
            if (count == 9)
                break;
        }
        for (; count < 9; count++){
            inventory[count] = "  " + DisplayUtility.fixLength( count - 2 + ":", 14);
        }
        inventory[9] = " ".repeat(16);


        // Message Box Part
        String[] messageOutput = new String[10];
        count = 0; // The number of line that has text.
        for (String message : messageBox){
            messageOutput[count] = " > " + DisplayUtility.fixLength(message, 48) + " ";
            count++;
        }
        for (; count < 10; count++){
            messageOutput[count] = " ".repeat(52);
        }


        // Story Part
        String[] story = this.getStory().split("\n");
        story[0] = " " + DisplayUtility.fixLength(story[0],103) + " ";
        story[1] = " " + DisplayUtility.fixLength(story[1],103) + " ";
        story[2] = " " + DisplayUtility.fixLength(story[2],103) + " ";


        // Map Part
        String[] map = new String[10];
        map[0] = "------------------";
        map[9] = "------------------";

        List<Location> enemiesLoc = Unit.unitsToLocations(enemies); // Get Locations of units
        List<Location> itemsLoc = Unit.unitsToLocations(items);
        List<Location> NPCsLoc = Unit.unitsToLocations(NPCs);
        List<Location> merchantsLoc = Unit.unitsToLocations(merchants);
        String[] m = this.map.getMap();

        for (int i = 0; i < m.length; i++) {
            StringBuilder line = new StringBuilder();// JDK 18 only: ‖
            for (int j = 0; j < m[i].length(); j++) {
                // Print Player
                if (player.getLoc().equals(new Location(i, j))){
                    line.append('X').append(" ");}
                // Print Enemies
                else if ((new Location(i,j).isin(enemiesLoc))) {
                    line.append('E').append(" ");}
                // Print Items
                else if ((new Location(i,j).isin(itemsLoc))) {
                    line.append('i').append(" ");}
                // Print NPCs
                else if ((new Location(i,j).isin(NPCsLoc))) {
                    line.append('N').append(" ");}
                // Print Merchants
                else if ((new Location(i,j).isin(merchantsLoc))) {
                    line.append('M').append(" ");}
                // Print Walls
                else {
                    line.append(m[i].charAt(j)).append(" ");
                }
            }
            map[i] = line.toString();    // JDK 18 only: ‖
        }



        // Combine each part
        output.append("=".repeat(107)).append("\n"); // Line 1
        output.append("|").append(title).append("|").append(DisplayUtility.centerText("Message Box", 52)).append("|\n"); // Line 2
        output.append("|").append("=".repeat(52)).append("|").append(" ".repeat(52)).append("|\n"); // Line 3
        for (int i = 0; i < 10; i++){
            output.append("|").append(myStats[i]).append("|").append(map[i]).append("|").append(inventory[i]).append("|").append(messageOutput[i]).append("|").append("\n"); // Line 4-13
        }
        output.append("=".repeat(107)).append("\n"); // Line 14
        output.append("|").append(story[0]).append("|").append("\n"); // Line 15
        output.append("|").append(story[1]).append("|").append("\n"); // Line 16
        output.append("|").append(story[2]).append("|").append("\n"); // Line 17
        output.append("=".repeat(107)).append("\n"); // Line 18
        return output.toString();
    }
}
