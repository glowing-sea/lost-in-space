package src.main;

/**
 * An object storing the attributes and method of the current player.
 */

public class Player extends Character {
    private int exp;
    private int playerLevel;

    public Player(String name, int hp, int atk, int def, Location loc, int exp, int playerLevel) {
        super(name, hp, atk, def, loc);
        this.exp = exp;
        this.playerLevel = playerLevel;
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
        if(st.enemies != null && !st.enemies.isEmpty()) { //there exist enemies
            for (Enemy enemy : st.enemies) {
                enemy.fight(st); //fight against them
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

    public int getPlayerLevel() {
        return playerLevel;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setPlayerLevel(int playerLevel) {
        this.playerLevel = playerLevel;
    }
}



