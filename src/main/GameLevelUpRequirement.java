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
}
