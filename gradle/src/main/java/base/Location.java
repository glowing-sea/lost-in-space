package base;

import java.util.ArrayList;
import java.util.List;

/**
 * A object shoring a location (x and y coordinate).
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
    public boolean equals(Location otherLoc) {
        return this.x == otherLoc.x && this.y == otherLoc.y;
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
    public Unit findUnit (Location loc, State st){
        // Enemy
        for (Enemy enemy :st.enemies){
            if (enemy.getLoc().equals(loc))
                return enemy;
        }
        // NPC
        for (NPC npc :st.NPCs){
            if (npc.getLoc().equals(loc))
                return npc;
        }
        // Item
        for (Item item :st.items) {
            if (item.getLoc().equals(loc))
                return item;
        }
        // Merchant
        for (Merchant merchant :st.merchants) {
            if (merchant.getLoc().equals(loc))
                return merchant;
        }
        return null;
    }
    public Location locCopy (){
        return new Location(x, y);
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}
