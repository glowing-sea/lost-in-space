package base;

/**
 * An object storing the attributes of an item.
 * An item is a unit and interactive. It can be picked up, dropped, or used.
 */
public class Item extends Unit{

    private boolean valid;
    private ItemType type;  // NOTE final keyword used here before. It is not right.

    //construction
    public Item(Location loc, ItemType type) {
        super(type.name(), loc, 'I');
        this.type = type;
        valid = true;
    }

    public ItemType getType() {
        return this.type;
    }


    /**
     * Helper function of the interact function
     * 1. Increase player's stats according to the type of the input item
     * 2. Make the item invalid
     * @param st current game state
     */
    public void useItem(State st) {
        Player p = st.player;
        switch(type){
            case Key -> st.messageBox.putMessage("Key function is not supported yet.");
            case HP_Boost -> p.setHp(p.getHp() + 20);
            case ATK_Boost -> p.setAtk(p.getAtk() + 20);
            case DEF_Boost -> p.setDef(p.getDef() + 20);
            case EXP_Boost -> p.collectExp(20,st);
            case Inventory_Boost -> p.setCapacity(p.getCapacity() + 2);
        }
        valid = false;
    }

    /**
     * The function tells what happens when the player interact with an item.
     * Increase player's stats according to the type of the input item.
     * @param st current game state
     * @return false if the item is invalid or the player's bag is full
     */
    @Override
    public boolean interact(State st, int option) {
        if (!this.valid)
            return false;  // Invalid Item
        if (!st.player.addItem(this)){
            st.messageBox.putMessage("You bag is full!");
            return false; // Pick up unsuccessfully
        } else {
            st.items.remove(this); // Remove from the map
            st.messageBox.putMessage(st.player.getName() + " has picked up an item " + "[ " + this + " ]" + ".");
        }

        return true;
    }

    @Override
    public String toString() {
        String name;
        switch(type){
            case Key -> name = "Key Item";
            case HP_Boost -> name = "HP+";
            case ATK_Boost -> name = "ATK+";
            case DEF_Boost -> name = "DEF+";
            case EXP_Boost -> name = "EXP+";
            case Inventory_Boost -> name = "Inventory+";
            default -> name = type.name();
        }
        return name;
    }

    // For testing only, do not use this method alone.
    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Item loc))
            return false;
        else{
            return this.getType() == ((Item) obj).getType();
        }
    }
}
