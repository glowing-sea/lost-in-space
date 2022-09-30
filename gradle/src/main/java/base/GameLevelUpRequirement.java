package base;

import java.util.List;

/**
 * This class stores the requirement for a current game state to move to the next game state
 */
public class GameLevelUpRequirement {

    private final Location destination;
    private final List<Enemy> enemiesMustBeKilled;
    private final Item itemRequired;

    public GameLevelUpRequirement(Location destination, List<Enemy> enemiesMustBeKilled, Item itemRequired) {
        if (destination == null){
            throw new NullPointerException("A level up requirement must contain a location check!");
        }
        this.destination = destination;
        this.enemiesMustBeKilled = enemiesMustBeKilled;
        this.itemRequired = itemRequired;
    }

    public Item getItemRequired() {
        return itemRequired;
    }
    public List<Enemy> getEnemiesMustBeKilled() {
        return enemiesMustBeKilled;
    }
    public Location getDestination() {
        return destination;
    }



    /**
     * This function check if the current game state satisfies the requirement to move to the next level
     * @param st the current game state
     * @return satisfies or not
     * @author William (Created)
     * @author Haoting (Refactor)
     */
    public boolean requirementSatisfied(State st){

        boolean locationCheck;
        boolean enemiesCheck = true;
        boolean keyCheck = true;

        // location Check
        locationCheck = this.destination.equals(st.player.getLoc());

        // enemies Check
        if (this.enemiesMustBeKilled != null){
            for (Enemy enemy : this.enemiesMustBeKilled){
                if (st.enemies.contains(enemy)) {
                    enemiesCheck = false;
                    break;
                }
            }
        }
        // key Check
        if (this.itemRequired != null){
            keyCheck = st.player.getInventory().contains(this.itemRequired);
        }

        if (locationCheck){
            if (enemiesCheck){
                if (keyCheck){
                    if (itemRequired != null) // Remove the key from the player
                        st.player.getInventory().remove(itemRequired);
                    return true;
                } else {
                    st.messageBox.putMessage("You must obtain [ " + this.itemRequired + " ]");
                    st.messageBox.putMessage("to enter.");
                    return false;
                }
            } else {
                st.messageBox.putMessage("You must beat some enemies before you leave");
                return false;
            }
        } else {
            return false;
        }
    }
}
