package base;

import utility.DisplayUtility;

import java.util.List;

/**
 * An object shoring the current game state
 */

public class State {
    public Map map;
    public String story; // The Main story at this level
    public int level;
    public Player player;
    public List<Enemy> enemies;
    public List<Item> items;
    public List<NPC> NPCs;
    public List<Merchant> merchants;

    // Display Variable
    public MessageBox messageBox; // Length must be <= 10

    public Unit interacting; // The Unit that the play is currently interact with

    @Override
    public boolean equals(Object obj){
        if (! (obj instanceof State st))
            return false;
        boolean result = this.map.equals(st.map) &&
                this.story.equals(st.story) &&
                this.level == st.level &&
                this.player.equals(st.player) &&
                this.enemies.equals(st.enemies) &&
                this.items.equals(st.items) &&
//                this.NPCs.equals(st.NPCs) &&
                this.merchants.equals(st.merchants) &&
                this.messageBox.equals(st.messageBox);
        if (this.interacting == null)
            return st.interacting == null & result;
        else
            return this.interacting.equals(st.interacting);
    }


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
        this.messageBox.putMessage("Need Help? Type 'tips'");
        this.interacting = null;
    }
    public void setStory(String story){this.story = story;}

    /**
     * Check whether the game state is a finish state (last state of the whole game).
     * @return ture or false
     */
    public Boolean isFinish(){ // check the player win or not
        return level == GameConfiguration.FINAL_LEVEL;
    }

    /**
     * Check whether the game state is over
     * @return ture or false
     */
    public Boolean isGameOver(){ // check the player win or not
        return player.getHp() <= 0;
    }

    /**
     * Update the current game state according to the input game state
     * Any fields in the new game state that has null or Integer.MIN_VALUE means that this field do not need to update.
     * @param newGameState the next game state.
     */
    public void gameLevelUp(State newGameState){

        // Update the attributes of the current game state
        // If an attribute of newGameState is null or MIN_VALUE, it means keep the current attribute.
        if (newGameState.map != null)
            map = newGameState.map; // Set Map
        if (newGameState.player != null && newGameState.player.getLoc() != null)
            player.setLoc(newGameState.player.getLoc()); // Set Player initial location
        if (newGameState.enemies != null)
            enemies = newGameState.enemies; // Set enemies
        if (newGameState.level != Integer.MIN_VALUE)
            level = newGameState.level; // Set Level
        if (newGameState.story != null)
            story = newGameState.story; // Set Story
        if (newGameState.items != null)
            items = newGameState.items; // Set items
        if (newGameState.NPCs != null)
            NPCs = newGameState.NPCs; // Set NPCs
        if (newGameState.merchants != null)
            merchants = newGameState.merchants; // Set merchants
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
         2 |                   Lost in Space                    |                    Message Box                     |
         3 |====================================================|                                                    |
         4 |   Your Name    |__________________|   Inventory    | > Need Help? Type 'tips'                           |
         5 |      Jack      |  X     E - - - - |      6/6       |                                                    |
         6 |                |                  |                |                                                    |
         7 |   LV: 0        |N       + - -     |  1: HP+        |                                                    |
         8 |   HP: 100      |    i   | M       |  2: HP+        |                                                    |
         9 |   ATK: 100     |        + - - - - |  3: HP+        |                                                    |
        10 |   DEF: 100     |    i   i     A   |  4: HP+        |                                                    |
        11 |   EXP: 0       |            A H A |  5: HP+        |                                                    |
        12 |   LOC: (0,1)   |- - - - - - - - - |  6: HP+        |                                                    |
        13 |                |                  |                |                                                    |
        14 ===========================================================================================================
        15 | This is where your story written in GameConfiguration is displayed.                                     |
        16 | You can have up to three lines. In each line, you can write up to 103 character.                        |
        17 | If a line exceed 103 character, the exceed part will not be displayed.................................. |
        18 ===========================================================================================================
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
        String[] story = new String[3];
        String[] storySplit = this.story.split("\n");
        int length = storySplit.length;
        for (int i = 0; i < length; i++){
            story[i] = " " + DisplayUtility.fixLength(storySplit[i],103) + " ";
        }
        if (length < 3)
            story[2] = " ".repeat(105);
        if (length < 2)
            story[1] = " ".repeat(105);
        if (length < 1)
            story[0] = " ".repeat(105);


        // Map Part
        String[] map = new String[10];
        map[0] = "__________________";

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
            map[i + 1] = line.toString();    // JDK 18 only: ‖
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
