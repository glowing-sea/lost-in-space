package base;

/**
 * An object storing the attributes of an item.
 * An item is a unit and interactive. It can be picked up, dropped, or used.
 */
public class Item extends Unit{

    private final ItemType type;  // NOTE final keyword used here before. It is not right.

    /**
     * @author Haoting
     * @param obj item
     * @return true if item.equals(item)
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Item i))
            return false;
        return this.type == i.type && this.getName().equals(i.getName());
    }

    /**
     * @author William Barter (created)
     * @author Haoting (refactor)
     * @param loc item location
     * @param type item type
     */
    //construction
    public Item(Location loc, ItemType type) {
        super(type.name(), loc, 'I');
        this.type = type;
    }
    /**

     * @author Haoting
     * @param keyItemName item name
     * @param loc item location
     */
    //construction for making a key item with a specified name
    public Item(String keyItemName, Location loc) {
        super(keyItemName, loc, 'I');
        this.type = ItemType.Key;
    }

    public ItemType getType() {
        return this.type;
    }


    /**
     * @author William Barter (created)
     * @author Haoting Chen (refactor)
     * Helper function of the interact function
     * 1. Increase player's stats according to the type of the input item
     * 2. Make the item invalid
     * @param st current game state
     */
    public void useItem(State st) {
        Player p = st.player;
        switch(type){
            case HP_Boost -> p.setHp(p.getHp() + 20);
            case ATK_Boost -> p.setAtk(p.getAtk() + 20);
            case DEF_Boost -> p.setDef(p.getDef() + 20);
            case EXP_Boost -> p.collectExp(20,st);
            case Inventory_Boost -> p.setCapacity(p.getCapacity() + 2);
        }
    }

    /**
     * @author William Barter (created)
     * @author Haoting Chen (refactor)
     * The function tells what happens when the player interact with an item.
     * Increase player's stats according to the type of the input item.
     * @param st current game state
     * @return false if the item is invalid or the player's bag is full
     */
    @Override
    public boolean interact(State st, int option) {
        if (!st.player.addItem(this)){
            st.messageBox.putMessage("You bag is full!");
            return false; // Pick up unsuccessfully
        } else {
            st.items.remove(this); // Remove from the map
            st.messageBox.putMessage(st.player.getName() + " has picked up an item " + "[ " + this + " ]" + ".");
            // System.out.println(st.player.getName() + " has picked up an item " + "[ " + this.getName() + " ]" + ".");
        }

        return true;
    }

    /**
     * @author Haoting
     * @return Display usage of items
     */
    @Override
    public String toString() {
        String name;
        switch(type){
            case Key -> name = getName();
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
