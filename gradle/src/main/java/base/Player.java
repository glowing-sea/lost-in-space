package base;

import java.util.ArrayList;
import java.util.List;

/**
 * An object storing the attributes and method of the current player.
 */

public class Player extends Character implements Movable{
    private int exp;
    private int playerLevel;
    private List<Item> inventory;
    private int capacity;


    // For testing only
    public Player(String name, int hp, int atk, int def, Location loc, int exp, int playerLevel) {
        super(name, hp, atk, def, loc, 'X');
        this.exp = exp;
        this.playerLevel = playerLevel;
        this.inventory = new ArrayList<>();
        this.capacity = 6;
    }

    public Player(Location loc) {
        super(GameConfiguration.YOUR_NAME, GameConfiguration.INITIAL_HP, GameConfiguration.INITIAL_ATK, GameConfiguration.INITIAL_DEF, loc, 'X');
        this.exp = GameConfiguration.INITIAL_EXP;
        this.playerLevel = GameConfiguration.INITIAL_LEVEL;
        this.inventory = GameConfiguration.INITIAL_INVENTORY;
        this.capacity = GameConfiguration.INITIAL_CAPACITY;
    }


    /**
     * interact with objects on map nearby player
     * In the future, this function contains the interaction with different object;
     *
     * currently realize:
     *  interact with enemies without UI
     *  interact with items nearby
     *      Store Items in Inventory
     *      Use Items which don't need to be stored eg. exp boost
     * @param st the board state
     */
    public static void interact(State st, String direction){

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
        } else if (!unit.interact(st)){
            st.messageBox.putMessage("Interact failed.");
        }
    }


    /**
     * Use the items within the players inventory at the requested number.
     * @param st the state the game is currently in
     * @param  itemIndex the inventory position which item is being used
     * @param action 0 for use. 1 for drop. 2 for view.
     */
    public static void takeOutItem(State st, String itemIndex, int action){
        int itemIdx;
        try {
            itemIdx = Integer.parseInt(itemIndex);
        } catch(NumberFormatException e){
            st.messageBox.putMessage("Item index is not well-formed.");
            return;
        }
        itemIdx -= 1; // Start from 0
        if (itemIdx >= st.player.getInventory().size() || itemIdx < 0){
            st.messageBox.putMessage("Item not found.");
            return;
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
    }



    public static void getQuest(State st){
        int level = st.level;
        GameLevelUpRequirement levelUpRequirement= GameConfiguration.LEVEL_UP_REQUIREMENTS[level];
        if (levelUpRequirement != null){
            Location destination = levelUpRequirement.getLocation();
            st.messageBox.putMessage("Your goal is to reach " + destination + ".");
        } else {
            st.messageBox.putMessage("You have finished all quests");
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
        switch (direction) {
            case "w", "ww" -> {
                int newX = getLoc().getX() - speed;
                newLoc = new Location(newX, getLoc().getY());
            }
            case "s", "ss" -> {
                int newX = getLoc().getX() + speed;
                newLoc = new Location(newX, getLoc().getY());
            }
            case "d", "dd" -> {
                int newY = getLoc().getY() + speed;
                newLoc = new Location(getLoc().getX(), newY);
            }
            case "a", "aa" -> {
                int newY = getLoc().getY() - speed;
                newLoc = new Location(getLoc().getX(), newY);
            }
            default -> {return false;}
        }

            if (!st.map.reachable(newLoc))
                return false;
            if (newLoc.findUnit(st) != null)
                return false;

            st.player.setLoc(newLoc);
            return true;

    }


    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean addItem(Item item) {
        if (this.capacity - 1 >= this.inventory.size()){
            this.inventory.add(item);
            return true;
        } else {
            return false;
        }
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

    public Item getItem(int inventoryNumber) {
        return this.inventory.get(inventoryNumber);
    }
    public int getItemCount() {
        return this.inventory.size();
    }

    // If you only want to increase exp, use collectExp
    public void setExp(int exp) {
        this.exp = exp;
    }

    public void collectExp(int exp, State st){
        this.exp += exp;
        int levelIncreased = exp / 100;
        if (levelIncreased > 0){ // can level up
            this.playerLevel += levelIncreased;
            this.exp =  exp % 100;
            if (this.getHp() < 100)
                this.setHp(100); // Restore HP when leveling up
            st.messageBox.putMessage("System: you have leveled up!");
        }
    }


    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
}



