package base;

/**
 * Objects implement this class can move on the map, such as Player.
 */
public interface Movable {
    boolean move (State st, String direction, int speed);
}
