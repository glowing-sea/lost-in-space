package src.main;

/**
 * An object storing the attributes of a character, such as the player or an enemy.
 */

public abstract class Character {
    private String name;
    private int hp;
    private int atk;
    private int def;
    private Location loc;

    public Character(String name, int hp, int atk, int def, Location loc) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.loc = loc;
    }

    public String getName() {
        return name;
    }

    /**
     * check if a particulr is nearby this character
     * @param other a location you want to test
     * @return nearby you or not
     */
    public boolean nearby(Location other) {
        Location my = this.loc;
        int X = my.getX();
        int Y = my.getY();
        int EX = other.getX();
        int EY = other.getY();
        return (X == (EX - 1) && Y == EY) || (X == (EX + 1) && Y == EY) || (X == EX && Y == (EY + 1)) || (X == EX && Y == (EY - 1));
    }


    public int getHp() {
        return hp;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public Location getLoc() {
        return loc;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setDef(int def) {
        this.def = def;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }
}
