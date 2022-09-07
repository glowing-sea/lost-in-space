package main;

/**
 * This class stores the requirement for a current game state to move to the next game state
 */
public class GameLevelUpRequirement {

    private Location location;

    public GameLevelUpRequirement(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    // FIXME
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
        return location.equals(st.player.getLoc());
    }
}
