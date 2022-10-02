package base;

import java.util.ArrayList;
import java.util.List;

/**
 * A character that is controlled by the user.
 */

public class Player extends Character implements Movable{
    private int exp;
    private int playerLevel;
    private List<Item> inventory;
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
     * @author Haoting Chen
     * check player == player
     * @param obj  player
     * @return true if player == player
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player p))
            return false;
        return this.exp == p.exp &&
                this.playerLevel == p.playerLevel &&
                this.inventory.equals(p.inventory) &&
                this.capacity == p.capacity &&
                super.equals(p);
    }

    /**
     * @Haoting Chen
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
     * @author ZHishang Bian (created)
     * @author Haoting Chen (refactor)
     * This function handle user's input of interaction
     * 1. Find the Unit that the player want to interact with
     * 2. Call the interact function of that Unit
     * @param st current game state
     * @return if the interaction succeeded
     */
    public static boolean interact(State st, String option, String value){

        // The player is currently interacting with an NPC or merchant
        if (st.interacting != null) {
            Unit unit = st.interacting;
            switch (option){
                case "A","(A)" -> {if (unit instanceof NPC) unit.interact(st,1);} // Only NPC can be interacted with A
                case "B","(B)" -> {if (unit instanceof NPC) unit.interact(st,2);} // Only NPC can be interacted with B
                case "G","(G)" -> {if (unit instanceof NPC || unit instanceof Merchant) unit.interact(st,3);} // Only NPC and merchant can be interacted with G
                case "buy" ->{
                    if (unit instanceof Merchant){
                        int goodIdx;
                        try {
                            goodIdx = Integer.parseInt(value);
                        } catch(NumberFormatException e) {
                            st.messageBox.putMessage("The good index is not well-formed.");
                            return false;
                        }
                        if (goodIdx < 1 || goodIdx > ((Merchant) unit).getTrades().size()) {
                            st.messageBox.putMessage("The good index is out of bound!");
                        } else {
                            unit.interact(st, goodIdx + 3);
                        }
                    }
                }
            }
        }
        else // The player is currently not interacting
        {
            // Get the location that the player want to interact with.
            Unit unit;
            Location unitLoc = st.player.getLoc().clone();
            switch (option){
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
            } else if (!unit.interact(st, 0)){
                st.messageBox.putMessage("Interact failed.");
                return false;
            }
        }
        return true;
    }


    /**
     * @author William Barter (created)
     * @author Hoating Chen (refactor)
     * Use the items within the players inventory at the requested number.
     * @param st the state the game is currently in
     * @param  itemIndex the inventory position which item is being used
     * @param action 0 for use. 1 for drop. 2 for view.
     * @return  whether an item is taken out successfully
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

        if (action == 2){
            st.messageBox.putMessage("The item at index " + (itemIdx + 1) + " is: [ " + item + " ].");
        } else {
            if (item.getType() != ItemType.Key){
                if (action == 0){
                    st.player.getInventory().remove(itemIdx);
                    item.useItem(st);
                    st.messageBox.putMessage(st.player.getName() + " uses the item [ " + item + " ].");
                } else if (action == 1) {
                    st.player.getInventory().remove(itemIdx);
                    st.messageBox.putMessage(st.player.getName() + " drops the item [ " + item + " ].");
                }
            } else {
                st.messageBox.putMessage("You cannot drop or use a key item!");
                return false;
            }
        }
        return true;
    }


    /**
     * @author Haoting Chen
     * Output the requirement to advance to the next game level
     * @param st current game state
     * @return true there is a next level and vice versa.
     */
    public static boolean getQuest(State st){
        int level = st.level;
        GameLevelUpRequirement levelUpRequirement= GameConfiguration.LEVEL_UP_REQUIREMENTS[level];
        if (levelUpRequirement != null){
            Location destination = levelUpRequirement.getDestination();
            st.messageBox.putMessage("Your goal is to reach " + destination + ".");
            return true;
        } else {
            st.messageBox.putMessage("You have finished all quests");
            return false;
        }
    }




    /**
     * @author Haoting Chen
     * Move the character according to the input direction and speed
     * @param st current game state
     * @param direction forward (w), backward (s), right (a), or left (d)
     * @param speed how many step to move
     * @return true if the character has moved
     */
    public boolean move (State st, String direction, int speed){
        Location playLoc = st.player.getLoc();
        if (st.interacting != null) {
            st.messageBox.putMessage("System: you need to say goodbye before leaving.");
            return false;
        }
        Location newLoc;
        Location adjLoc;
        switch (direction) {
            case "w", "ww" -> {
                int newX = playLoc.getX() - speed;
                int adjX = playLoc.getX() - 1;
                newLoc = new Location(newX, playLoc.getY());
                adjLoc = new Location(adjX, playLoc.getY());
            }
            case "s", "ss" -> {
                int newX = playLoc.getX() + speed;
                int adjX = playLoc.getX() + 1;
                newLoc = new Location(newX, playLoc.getY());
                adjLoc = new Location(adjX, playLoc.getY());
            }
            case "d", "dd" -> {
                int newY = playLoc.getY() + speed;
                int adjY = playLoc.getY() + 1;
                newLoc = new Location(playLoc.getX(), newY);
                adjLoc = new Location(playLoc.getX(), adjY);
            }
            case "a", "aa" -> {
                int newY = playLoc.getY() - speed;
                int adjY = playLoc.getY() - 1;
                newLoc = new Location(playLoc.getX(), newY);
                adjLoc = new Location(playLoc.getX(), adjY);
            }
            default -> {return false;}
        }

            if (!st.map.reachable(adjLoc) || !st.map.reachable(newLoc))
                return false;
            if (adjLoc.findUnit(st) != null || newLoc.findUnit(st) != null)
                return false;

            st.player.setLoc(newLoc);
            return true;
    }


    /**
     * @author William Barter (created)
     * @authr Haoting Chen (refactor)
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
     * @author William Barter
     * @author Haoting Chen
     * Increase Player EXP by a certain amount.
     * Whenever the EXP exceed 100, increase the level by 1.
     * @param exp amount of EXP to increase
     * @param st current state
     */
    public void collectExp(int exp, State st){
        this.exp += exp;
        int levelIncreased = this.exp / 100;
        if (levelIncreased > 0){ // can level up
            this.playerLevel += levelIncreased;
            this.exp %= 100;
            st.messageBox.putMessage("System: you have leveled up!");
            st.player.setHp(st.player.getHp() + GameConfiguration.PLAYER_LEVEL_UP_EFFECT);
            st.player.setAtk(st.player.getAtk() + GameConfiguration.PLAYER_LEVEL_UP_EFFECT);
            st.player.setDef(st.player.getDef() + GameConfiguration.PLAYER_LEVEL_UP_EFFECT);
        }
    }


    // Simple setting and getter methods
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getCapacity() {
        return capacity;
    }
    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    } // For testing only
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



