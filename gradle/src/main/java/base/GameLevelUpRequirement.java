package base;

/**
 * This class stores the requirement for a current game state to move to the next game state
 */
public class GameLevelUpRequirement {

    private Location location;
    private Boolean enemies;
    private Boolean key;

    public GameLevelUpRequirement(Location location, Boolean enemies, Boolean key) {
        this.location = location;
        this.enemies = enemies;
        this.key = key;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    /*
    The current requirement of moving to the next game level is only based on the character's location.
    There may be more requirements (fields) in the future.

    For example, if the player get to (5,5), the game moves to the next level.
     */

    /**
     * This function check if the current game state satisfies the requirement to move to the next level
     * @param st the current game state
     * @return satisfies or not
     */
    public boolean requirementSatisfied(State st){

        boolean locationTrue;
        boolean enemiesTrue = false;
        boolean keyTrue = false;

        //Are requirements satisfied
        locationTrue = location.equals(st.player.getLoc());
        if (st.enemies.isEmpty()) enemiesTrue = true;
        for (Item item: st.player.getInventory()) {
            if (item.getType() == ItemType.Key) keyTrue = true;
        }


        //Do requirements need to be satisfied
        if (locationTrue) {
        if (enemies) {
            if (!(enemiesTrue)) {
                st.messageBox.putMessage("You must beat all enemies before you leave");
                if (key && !keyTrue) st.messageBox.putMessage("You must have a key to enter");
                return false;
            }

        }
        if (key) {
            if (!(keyTrue)) {
                st.messageBox.putMessage("You must have a key to enter");
                return false;
            }

            //Use the key item when leveling up
            for (Item item: st.player.getInventory()) {
                String newstr = "" + (st.player.getInventory().indexOf(item) + 1);
                if (item.getType() == ItemType.Key) {
                    st.player.takeOutItem(st,newstr, 0) ;
                    break;
                }
            }

        }
        return true;
        }

        return false;
    }
}
