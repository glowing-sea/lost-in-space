package src.base;

import java.util.ArrayList;
import java.util.List;

/**
 * An object storing the attributes and method of the current player.
 */

public class Player extends Character implements PlayerInterface {
    private int exp;
    private int playerLevel;
    private List<Item> ItemsHeld=new ArrayList<>();
    private int maxitemsheld;

    public Player(String name, int hp, int atk, int def, Location loc, int exp, int playerLevel) {
        super(name, hp, atk, def, loc);
        this.exp = exp;
        this.playerLevel = playerLevel;
        this.maxitemsheld = 2;
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
    public static void interact(State st){
        //interact with enemies nearby you!
        ArrayList<Enemy> deletethem = new ArrayList<>();
        //boolean used to make sure only 1 action is done for each press of interact
        boolean canaction = true;

        if(st.enemies != null && !st.enemies.isEmpty()) { //there exist enemies
            for (Enemy enemy : st.enemies) {
                Enemy deleteit = enemy.fight(st); //fight against them
                deletethem.add(deleteit);
            }
            if (deletethem.isEmpty()) canaction = false;
        }
        if(st.enemies != null) {
            st.enemies.removeAll(deletethem);
        }

        //interact with items nearby to pick them up
        if((st.Items != null && !st.Items.isEmpty()) && canaction) {

            for (Item item : st.Items) {
                //check for items with Instant usage
                if (item.pickupitemvalid(st)) {
                switch (item.getType()) {
                    case Inventory_Boost:
                        //boost inventory by 1 when item used up to a max of 5 places
                        if (!(st.player.getMaxitemsheld() +1 > 5)) st.player.setMaxitemsheld(st.player.getMaxitemsheld() + 1);
                        //remove item from existance
                            item.use_item();
                            item.pickupitem();
                        System.out.print("Got Inventory Boost");
                        canaction = false;
                        break;
                    case EX_Boost:
                        //boost exp by 1 when item used
                        st.player.setExp(st.player.getExp() + 1);
                        //remove item from existance
                        item.use_item();
                        item.pickupitem();
                        System.out.print("Got EXP Boost");
                        canaction = false;
                        break;
                    default:
                        break;
                }

                // items to be stored in inventory if they are valid
                 if (st.player.canaddItem() && canaction) {
                    st.player.addItem(item);
                    item.pickupitem();
                    canaction = false;
                }
            }
        }
        }

        //If No items or enemys are nearby, drop items

        }
    /**
     * Use the items within the players inventory at the requested number.
     * @param st the state the game is currently in
     * @param  inventorynumber the inventory position which item is being used
     */
    public static void useItem(State st, int inventorynumber){

        int tempinventorynum = inventorynumber;

        if (tempinventorynum > st.player.getItemCount()) {
        tempinventorynum = st.player.getItemCount();
        }

        if ((st.player.ItemsHeld != null) && !(st.player.ItemsHeld.isEmpty())) {
            if (st.player.getItem(tempinventorynum - 1).getType() == Item_Type.HP_Boost) {//boost hp by 1 when item used
                st.player.setHp(st.player.getHp() + 1);

                if (st.Items.contains(st.player.getItem(tempinventorynum - 1))) {
                    st.Items.get(tempinventorynum - 1).use_item();
                }

                st.player.ItemsHeld.remove(st.player.getItem(tempinventorynum - 1));
                System.out.print("Used HP Boost");
            }

        }
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

    public boolean canaddItem() {
        return this.ItemsHeld.size() + 1 <= this.maxitemsheld;
    }

    public int getMaxitemsheld() {
        return this.maxitemsheld;
    }

    public void setMaxitemsheld(int newItemsheld){
        this.maxitemsheld = newItemsheld;
    }

    public int getExp() {
        return exp;
    }

    public void addItem(Item item) {
            this.ItemsHeld.add(item);
    }

    public int getPlayerLevel() {
        return playerLevel;
    }

    public Item getItem(int inventorynumber) {
        return this.ItemsHeld.get(inventorynumber);
    }

    public void setExp(int exp) {
        this.exp = exp;
    }
    public int getItemCount() {
        return this.ItemsHeld.size();
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
}



