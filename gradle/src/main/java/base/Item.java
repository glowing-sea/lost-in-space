package base;

public class Item extends Unit{

    private boolean valid;
    private ItemType type;

    private char SYMBOL;

    //construction
    public Item(Location loc, ItemType type) {
        super("Item", loc, 'I');
        this.type = type;
        valid = true;
    }

    public ItemType getType() {
        return this.type;
    }



    public void useItem() {
        valid = false;
    }

    /**
     * Returns if the item is in a valid location to be picked up by the player
     * @param st the map where the character is in
     * @return true if the item can be picked up
     */
    public boolean canBePicked(State st){
        return (st.player.nearby(getLoc())) && valid;
    }

    /**
     * Moves item off of the map once picked up
     */
    public void pickUpItem() {
        this.setLoc(new Location(-1, -1));
    }
}
