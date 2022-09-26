package base;

import java.util.ArrayList;
import java.util.List;

/**
 * An object storing the attributes and method of the current player.
 */

public class Player extends Character implements PlayerInterface {
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
        unit = unitLoc.findUnit(unitLoc, st);
        if (unit == null) {
            st.messageBox.putMessage("There is nothing to interact with.");
        } else if (!unit.interact(st)){
            st.messageBox.putMessage("Interact failed.");
        }



/*
        //interact with items nearby to pick them up
        if((st.items != null && !st.items.isEmpty()) && canaction) {

            for (Item item : st.items) {
                //check for items with Instant usage
                if (item.canBePicked(st)) {
                switch (item.getType()) {
                    case Inventory_Boost:
                        //boost inventory by 1 when item used up to a max of 5 places
                        if (!(st.player.getMaxitemsheld() +1 > 5)) st.player.setMaxitemsheld(st.player.getMaxitemsheld() + 1);
                        //remove item from existance
                            item.useItem();
                            item.pickUpItem();
                        System.out.print("Got Inventory Boost");
                        canaction = false;
                        break;
                    case EX_Boost:
                        //boost exp by 1 when item used
                        st.player.setExp(st.player.getExp() + 1);
                        //remove item from existance
                        item.useItem();
                        item.pickUpItem();
                        System.out.print("Got EXP Boost");
                        canaction = false;
                        break;
                    default:
                        break;
                }

                // items to be stored in inventory if they are valid
                 if (st.player.canaddItem() && canaction) {
                    st.player.addItem(item);
                    item.pickUpItem();
                    canaction = false;
                }
            }
            }

        }

        //If No items or enemys are nearby, drop items
        return true;

 */
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




//        if (tempinventorynum > st.player.getItemCount()) {
//        tempinventorynum = st.player.getItemCount();
//        }
//
//        if ((st.player.inventory != null) && !(st.player.inventory.isEmpty())) {
//            if (st.player.getItem(tempinventorynum - 1).getType() == ItemType.HP_Boost) {//boost hp by 1 when item used
//                st.player.setHp(st.player.getHp() + 1);
//
//                if (st.items.contains(st.player.getItem(tempinventorynum - 1))) {
//                    st.items.get(tempinventorynum - 1).useItem();
//                }
//
//                st.player.inventory.remove(st.player.getItem(tempinventorynum - 1));
//                System.out.print("Used HP Boost");
//            }
//
//        }
    }



    public static void getDestination(State st){
        Map map = st.map;
        Location current =  st.player.getLoc(); //player location
        Location destination = new Location(-1, -1); //avoid exception
        for (int i = 0; i < map.getMap().length; i++) {
            for (int j = 0; j < map.getMap()[i].length(); j++) {
                if (map.getMap()[i].charAt(j) == 'H') {
                    destination = new Location(i, j);
                }
            }
        }
        System.out.println("You are in (" + current.getX() + ", " + current.getY() + "), go to: (" + destination.getX() + ", " + destination.getY() + ")!");

    }

    /**
     * Move the character one step forward provided that they can go there.
     * @param map the map where the character is in
     * @return true if the character has moved
     */
    @Override
    public boolean forward(Map map) {
        int newX = getLoc().getX() - 1;
        Location newLoc = new Location(newX, getLoc().getY());
        if (map.reachable(newLoc)) {
            getLoc().setX(newX);
            return true;
        } else
            return false;
    }

    /**
     * Move the character one step backward provided that they can go there.
     * @param map the map where the character is in
     * @return true if the character has moved
     */
    @Override
    public boolean backward(Map map) {
        int newX = getLoc().getX() + 1;
        Location newLoc = new Location(newX, getLoc().getY());
        if (map.reachable(newLoc)) {
            getLoc().setX(newX);
            return true;
        } else
            return false;
    }

    /**
     * Move the character one step to the right provided that they can go there.
     * @param map the map where the character is in
     * @return true if the character has moved
     */
    @Override
    public boolean right(Map map) {
        int newY = getLoc().getY() + 1;
        Location newLoc = new Location(getLoc().getX(), newY);
        if (map.reachable(newLoc)) {
            getLoc().setY(newY);
            return true;
        } else
            return false;
    }

    /**
     * Move the character one step to the left provided that they can go there.
     * @param map the map where the character is in
     * @return true if the character has moved
     */
    @Override
    public boolean left(Map map) {
        int newY = getLoc().getY() - 1;
        Location newLoc = new Location(getLoc().getX(), newY);
        if (map.reachable(newLoc)) {
            getLoc().setY(newY);
            return true;
        } else
            return false;
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



