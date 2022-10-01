package base;

/**
 * An object storing the attributes of a character, such as the player or an enemy.
 *
 */

public abstract class Character extends Unit{
    private int hp;
    private int atk;
    private int def;


    public Character(String name, int hp, int atk, int def, Location loc, char symbol) {
        super(name,loc,symbol);
        this.hp = hp;
        this.atk = atk;
        this.def = def;
    }

    /**
     * @author Haoting Chen
     * @param obj enemy or player
     * @return True if two unit is same
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != getClass())
            return false;
        Character c = (Character) obj;
        return this.hp == c.hp &&
                this.atk == c.atk &&
                this.def == c.def &&
                super.equals(c);
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

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setDef(int def) {
        this.def = def;
    }
}
