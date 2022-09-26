package base;

public class Item extends Unit{

    private boolean valid;
    private final ItemType type;


    //construction
    public Item(Location loc, ItemType type) {
        super(type.name(), loc, 'I');
        this.type = type;
        valid = true;
    }

    public ItemType getType() {
        return this.type;
    }



    public void useItem(State st) {
        Player p = st.player;
        switch(type){
            case Key -> st.messageBox.putMessage("Key function is not supported yet.");
            case HP_Boost -> p.setHp(p.getHp() + 20);
            case ATK_Boost -> p.setAtk(p.getAtk() + 20);
            case DEF_Boost -> p.setDef(p.getDef() + 20);
            case EXP_Boost -> p.setExp(p.getExp() + 20);
            case Inventory_Boost -> p.setCapacity(p.getCapacity() + 2);
        }
        valid = false;
    }

// Deprecated
//    /**
//     * Returns if the item is in a valid location to be picked up by the player
//     * @param st the map where the character is in
//     * @return true if the item can be picked up
//     */
//    public boolean canBePicked(State st){
//        return (st.player.nearby(getLoc())) && valid;
//    }
//
//    /**
//     * Moves item off of the map once picked up
//     */
//    public void pickUpItem() {
//        this.setLoc(new Location(-1, -1));
//    }

    @Override
    public boolean interact(State st) {
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
}
