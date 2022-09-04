package src.main;
public class Enemy extends Character{
    boolean isDead;

    public Enemy(String name, int hp, int atk, int def, Location loc, boolean isDead,Map map) {
        super(name, hp, atk, def, loc,map);
        this.isDead = isDead;
    }
}
