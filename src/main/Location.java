package src.main;

/**
 * A object shoring a location (x and y coordinate).
 */

public class Location {
    private int x;
    private int y;

    Location (int x, int y){
        this.x = x;
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    /**
     * Check if two locations are equal
     * @param otherLoc the other location
     * @return true if the two location are equal
     */
    public boolean equals(Location otherLoc) {
        return this.x == otherLoc.x && this.y == otherLoc.y;
    }
}
