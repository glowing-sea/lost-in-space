package base;

import java.util.ArrayList;
import java.util.List;

/**
 * A character that is controlled by the user.
 */

public class Player extends Character implements Movable{
    private int exp;
    private int playerLevel;
    private final List<Item> inventory;
    private int capacity;


    // For testing only. Manually set attributes
    public Player(String name, int hp, int atk, int def, Location loc, int exp, int playerLevel) {
        super(name, hp, atk, def, loc, 'X');
        this.exp = exp;
        this.playerLevel = playerLevel;
        this.inventory = new ArrayList<>();
        this.capacity = 6;
    }

    /**
     * A quick constructor that make a player according the attributes written in GameConfiguration
     * @param loc
     */
    public Player(Location loc) {
        super(GameConfiguration.YOUR_NAME, GameConfiguration.INITIAL_HP, GameConfiguration.INITIAL_ATK, GameConfiguration.INITIAL_DEF, loc, 'X');
        this.exp = GameConfiguration.INITIAL_EXP;
        this.playerLevel = GameConfiguration.INITIAL_LEVEL;
        this.inventory = GameConfiguration.INITIAL_INVENTORY;
        this.capacity = GameConfiguration.INITIAL_CAPACITY;
    }


    /**
     * This function handle user's input of interaction
     * 1. Find the Unit that the player want to interact with
     * 2. Call the interact function of that Unit
     * @param st current game state
     * @return if the interaction succeeded
     */
    public static boolean interact(State st, String direction){

        // Get the location that the player want to interact with.
        Unit unit;
        Location unitLoc = st.player.getLoc().locCopy();
        switch (direction){
            case "fa" -> {unitLoc.setY(unitLoc.getY() - 1);}
            case "fd" -> {unitLoc.setY(unitLoc.getY() + 1);}
            case "fw" -> {unitLoc.setX(unitLoc.getX() - 1);}
            case "fs" -> {unitLoc.setX(unitLoc.getX() + 1);}
        }
        // Search if there is a unit to interact in this location
        unit = unitLoc.findUnit(st);
        if (unit == null) {
            st.messageBox.putMessage("There is nothing to interact with.");
            return false;
        } else if (!unit.interact(st)){
            st.messageBox.putMessage("Interact failed.");
            return false;
        }
        return true;
    }


    /**
     * Use the items within the players inventory at the requested number.
     * @param st the state the game is currently in
     * @param  itemIndex the inventory position which item is being used
     * @param action 0 for use. 1 for drop. 2 for view.
     * @param whether an item is taken out successfully
     */
    public static boolean takeOutItem(State st, String itemIndex, int action){
        int itemIdx;
        try {
            itemIdx = Integer.parseInt(itemIndex);
        } catch(NumberFormatException e){
            st.messageBox.putMessage("Item index is not well-formed.");
            return false;
        }
        itemIdx -= 1; // Start from 0
        if (itemIdx >= st.player.getInventory().size() || itemIdx < 0){
            st.messageBox.putMessage("Item not found.");
            return false;
        }
        Item item = st.player.getInventory().get(itemIdx);

        if (action == 0){
            st.player.getInventory().remove(itemIdx);
            item.useItem(st);
            st.messageBox.putMessage(st.player.getName() + " uses the item [ " + item + " ].");
        } else if (action == 1) {
            st.player.getInventory().remove(itemIdx);
            st.messageBox.putMessage(st.player.getName() + " drops the item [ " + item + " ].");
        } else {
            st.messageBox.putMessage("The item at index " + (itemIdx + 1) + " is: [ " + item + " ].");
        }
        return true;
    }


    /**
     * Output the requirement to advance to the next game level
     * @param st current game state
     * @return true there is a next level and vice versa.
     */
    public static boolean getQuest(State st){
        int level = st.level;
        GameLevelUpRequirement levelUpRequirement= GameConfiguration.LEVEL_UP_REQUIREMENTS[level];
        if (levelUpRequirement != null){
            Location destination = levelUpRequirement.getLocation();
            st.messageBox.putMessage("Your goal is to reach " + destination + ".");
            return true;
        } else {
            st.messageBox.putMessage("You have finished all quests");
            return false;
        }
    }




    /**
     * Move the character according to the input direction and speed
     * @param st current game state
     * @param direction forward (w), backward (s), right (a), or left (d)
     * @param speed how many step to move
     * @return true if the character has moved
     */
    public boolean move (State st, String direction, int speed){
        Location newLoc;
        Location adjLoc;
        switch (direction) {
            case "w", "ww" -> {
                int newX = getLoc().getX() - speed;
                int adjX = getLoc().getX() - 1;
                newLoc = new Location(newX, getLoc().getY());
                adjLoc = new Location(adjX, getLoc().getY());
            }
            case "s", "ss" -> {
                int newX = getLoc().getX() + speed;
                int adjX = getLoc().getX() + 1;
                newLoc = new Location(newX, getLoc().getY());
                adjLoc = new Location(adjX, getLoc().getY());
            }
            case "d", "dd" -> {
                int newY = getLoc().getY() + speed;
                int adjY = getLoc().getY() + 1;
                newLoc = new Location(getLoc().getX(), newY);
                adjLoc = new Location(getLoc().getX(), adjY);
            }
            case "a", "aa" -> {
                int newY = getLoc().getY() - speed;
                int adjY = getLoc().getY() - 1;
                newLoc = new Location(getLoc().getX(), newY);
                adjLoc = new Location(getLoc().getX(), adjY);
            }
            default -> {return false;}
        }

            if (!st.map.reachable(adjLoc))
                return false;
            if (newLoc.findUnit(st) != null)
                return false;

            st.player.setLoc(newLoc);
            return true;
    }


    /**
     * Add an item to the player's inventory if it is not full
     * @param item item that is going to be added
     * @return whether the item has been added
     */
    public boolean addItem(Item item) {
        if (this.capacity - 1 >= this.inventory.size()){
            this.inventory.add(item);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Increase Player EXP by a certain amount.
     * Whenever the EXP exceed 100, increase the level by 1.
     * @param exp amount of EXP to increase
     * @param st current state
     */
    public void collectExp(int exp, State st){
        this.exp += exp;
        int levelIncreased = exp / 100;
        if (levelIncreased > 0){ // can level up
            this.playerLevel += levelIncreased;
            this.exp %= 100;
            st.messageBox.putMessage("System: you have leveled up!");
        }
    }


    // Simple setting and getter methods
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getCapacity() {
        return capacity;
    }
    public List<Item> getInventory() {
        return this.inventory;
    }
    public int getExp() {
        return exp;
    }
    public int getPlayerLevel() {
        return playerLevel;
    }


    // For testing only, please use collectExp if you want to increase player's level.
    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
}



