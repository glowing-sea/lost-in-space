package src.base;

public class Item {
    private Location loc;
    private boolean valid;
    private Item_Type type;

    public Item(Location NewLoc, Item_Type type) {
        this.loc = NewLoc;
        this.type = type;
        valid = true;
    }

    public Item_Type getType() {
        return this.type;
    }

    public void use_item() {
        valid = false;
    }

    public void dropitem(Location NewLoc) {
        loc = NewLoc;
    }
    public boolean pickupitemvalid(State st){
        if ((st.player.nearby(this.loc)) && valid) {
            return true;
        } else {
            return false;
        }
    }
    public void pickupitem() {
        Location outofmap = new Location(-1, -1); //sent items to tomb, get out of my way
        this.loc = (outofmap);
    }



}
