package base;

public interface Interactive {
    /**
     * Change the game state when the player interact with a unit such as an item or character.
     * @param st the current game state
     * @return whether the interaction is successful.
     */
    boolean interact (State st);
}
