package base;

import java.util.ArrayList;
import java.util.List;

// Any unit on the map, such as a character or a item.
public abstract class Unit{
    private String name;
    private Location loc;

    private final char SYMBOL;

    public Unit(String name, Location loc, char symbol) {
        this.name = name;
        this.loc = loc;
        this.SYMBOL = symbol;
    }

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
     * check if a particulr is nearby this character
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
     * Extra a list of locations from a list of unit
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

    @Override
    public String toString() {
        return getSYMBOL() + "";
    }
}
