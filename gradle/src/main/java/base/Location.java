package base;

import java.util.List;

/**
 * An object shoring a location (x and y coordinate).
 */

public class Location {
    private int x;
    private int y;

    public Location(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    /**
     * Check if two locations are equal
     * @param otherLoc the other location
     * @return true if the two location are equal
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Location loc))
            return false;
        else{
            return this.x == loc.x && this.y == loc.y;
        }
    }


    /**
     * Check if one location is in an arraylist of locations
     * @param locs a set of locations where u want to check if "this" is in it.
     * @return true if "this" is in the arraylist
     */
    public boolean isin(List<Location> locs){
        if(locs == null || locs.size()==0){
            return false;
        }
        for(Location loc : locs){
            if(loc.equals(this)){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if there is any unit, such as an enemy at this location
     * @param st current game state
     * @return return the unit if found.
     */
    public Unit findUnit (State st){
        // Enemy
        for (Enemy enemy :st.enemies){
            if (enemy.getLoc().equals(this))
                return enemy;
        }
        // NPC
        for (NPC npc :st.NPCs){
            if (npc.getLoc().equals(this))
                return npc;
        }
        // Item
        for (Item item :st.items) {
            if (item.getLoc().equals(this))
                return item;
        }
        // Merchant
        for (Merchant merchant :st.merchants) {
            if (merchant.getLoc().equals(this))
                return merchant;
        }
        return null;
    }

    /**
     * Make an exact copy of the location object
     * @return a copy of this location
     */
    public Location locCopy (){
        return new Location(x, y);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
