package src.base;

import java.util.ArrayList;
import java.util.List;

/**
 * An object storing the attributes and method of the current player.
 */

public class Player extends Character {
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
     * @param st the board state
     */
    public static void interact(State st){
        //interact with enemies nearby you!
        ArrayList<Enemy> deletethem = new ArrayList<>();
        if(st.enemies != null && !st.enemies.isEmpty()) { //there exist enemies
            for (Enemy enemy : st.enemies) {
                Enemy deleteit = enemy.fight(st); //fight against them
                deletethem.add(deleteit);
            }
        }
        if(st.enemies != null) {
            st.enemies.removeAll(deletethem);
        }

        //interact with items nearby
        if(st.Items != null && !st.Items.isEmpty()) {
            for (Item item : st.Items) {
                if ((item.pickupitemvalid(st)) && (st.player.canaddItem(item))) {
                    st.player.addItem(item);
                    item.pickupitem();

                }
            }
        }

        }

    public static void useItem(State st, int inventorynumber){

        if ((st.player.ItemsHeld != null) && !(st.player.ItemsHeld.isEmpty())) {
            switch (st.player.getItem(inventorynumber -1).getType()) {
                case HP_Boost:
                    //boost hp by 1 when item used
                    st.player.setHp(st.player.getHp() + 1);
                    st.player.ItemsHeld.remove(st.player.getItem(inventorynumber -1));
                    st.Items.get(inventorynumber -1).use_item();
                    System.out.print("Used Boost");
                    break;

                default:
                    break;
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
    public boolean left(Map map) {
        int newY = getLoc().getY() - 1;
        Location newLoc = new Location(getLoc().getX(), newY);
        if (map.reachable(newLoc)) {
            getLoc().setY(newY);
            return true;
        } else
            return false;


    }

    public int getExp() {
        return exp;
    }
    public boolean canaddItem(Item item) {
        if(this.ItemsHeld.size() <= this.maxitemsheld) {
            return true;
        } else {

            return false;
        }
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

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
}



