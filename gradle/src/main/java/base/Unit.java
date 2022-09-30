package base;

import java.util.ArrayList;
import java.util.List;

/**
 * An object storing the attributes any unit on the map, such as a character or an item.
 * <p>
 *
 *            Unit
 *       _______|_____
 *      |            |
 * Character        Item
 *   |    |_____________________________
 *   |           |           |         |
 * Player      Enemies      NPC       Merchant
 *
 */


// Any unit on the map, such as a character or a item.
public abstract class Unit implements Interactive{
    private String name; // every unit on the map has a name
    private Location loc; // every unit on the map has a location

    private final char SYMBOL; // every unit on the map has a symbol


    public Unit(String name, Location loc, char symbol) {
        if (name == null) name = GameConfiguration.DEFAULT_UNIT_NAME;
        if (symbol < 'A' || symbol > 'Z') symbol = GameConfiguration.DEFAULT_UNIT_SYMBOL;
        if (loc == null) loc = GameConfiguration.DEFAULT_UNIT_LOC;
        this.name = name;
        this.loc = loc;
        this.SYMBOL = symbol;
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass())
            return false;
        Unit u = (Unit) obj;
        return this.name.equals(u.name) &&
                this.loc.equals(u.loc) &&
                this.SYMBOL == u.SYMBOL;
    }


    // Basic setter and getter method
    public Location getLoc() {
        return loc;
    }
    public String getName() {
        return name;
    }
    public char getSYMBOL() {
        return SYMBOL;
    }
    public void setLoc(Location loc) {
        this.loc = loc;
    }
    public void setName(String name) {
        this.name = name;
    }



    /**
     * check if a unit is adjacent to the input location
     * @param other a location you want to test
     * @return nearby you or not
     */
    public boolean nearby(Location other) {
        Location my = this.loc;
        int X = my.getX();
        int Y = my.getY();
        int EX = other.getX();
        int EY = other.getY();
        return ((X == EX && Y == EY) || X == (EX - 1) && Y == EY) || (X == (EX + 1) && Y == EY) || (X == EX && Y == (EY + 1)) || (X == EX && Y == (EY - 1));
    }

    /**
     * Extra a list of locations from a list of units
     * @param units a list of unit
     * @return a list of location
     */
    static public List<Location> unitsToLocations (List<? extends Unit> units){
        ArrayList<Location> locations = new ArrayList<>();
        if (units == null)
            return locations;
        for (Unit unit : units) {
            locations.add(unit.getLoc());
            }
        return locations;
    }

    /**
     * The default interact function to all unit
     * @param st current state
     * @param option different way to interact
     * @return if an interaction succeeds
     */
    @Override
    public boolean interact(State st, int option) {
        st.messageBox.putMessage("You interact with " + this.name + "!");
        return true;
    }


    @Override
    public String toString() {
        return getSYMBOL() + "";
    }
}
