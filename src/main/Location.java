package src.main;

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


    // Check if two locations are equal
    public boolean equals(Location obj) {
        return this.x == obj.x && this.y == obj.y;
    }
}
