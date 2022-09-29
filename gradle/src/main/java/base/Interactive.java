package base;

/**
 * Objects implement this interface are interactive in the game. Example of them are enemies or items.
 */
public interface Interactive {
    /**
     * Change the game state when the player interact with a unit such as an item or character.
     * @param st the current game state
     * @return whether the interaction is successful.
     */
    boolean interact (State st, int option);
}
