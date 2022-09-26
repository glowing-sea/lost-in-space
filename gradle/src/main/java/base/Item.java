package base;

public class Item {
    private Location loc;
    private boolean valid;
    private ItemType type;

    private char SYMBOL;

    //construction
    public Item(Location NewLoc, ItemType type) {
        this.loc = NewLoc;
        this.type = type;
        valid = true;
        this.SYMBOL = 'I';
    }

    public ItemType getType() {
        return this.type;
    }

    public Location getLocation() {
        return this.loc;
    }

    public void useItem() {
        valid = false;
    }

    public void dropItem(Location NewLoc) {
        loc = NewLoc;
    }
    /**
     * Returns if the item is in a valid location to be picked up by the player
     * @param st the map where the character is in
     * @return true if the item can be picked up
     */
    public boolean isValid(State st){
        return (st.player.nearby(this.loc)) && valid;
    }

    /**
     * Moves item off of the map once picked up
     */
    public void pickUpItem() {
        this.loc = (new Location(-1, -1));
    }

    public char getSYMBOL() {
        return SYMBOL;
    }
}
