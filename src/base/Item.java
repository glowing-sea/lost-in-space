package base;

public class Item {
    private Location loc;
    private boolean valid;
    private Item_Type type;

    //construction
    public Item(Location NewLoc, Item_Type type) {
        this.loc = NewLoc;
        this.type = type;
        valid = true;
    }

    public Item_Type getType() {
        return this.type;
    }

    public Location getLocation() {
        return this.loc;
    }

    public void use_item() {
        valid = false;
    }

    public void dropitem(Location NewLoc) {
        loc = NewLoc;
    }
    /**
     * Returns if the item is in a valid location to be picked up by the player
     * @param st the map where the character is in
     * @return true if the item can be picked up
     */
    public boolean pickupitemvalid(State st){
        if ((st.player.nearby(this.loc)) && valid) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Moves item off of the map once picked up
     * @return void
     */
    public void pickupitem() {
        Location outofmap = new Location(-1, -1); //sent items to tomb, get out of my way
        this.loc = (outofmap);
    }



}
