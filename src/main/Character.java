package src.main;
public abstract class Character {
    private String name;
    private int hp;
    private int atk;
    private int def;
    private Location loc;

    private Map map;

    public Character(String name, int hp, int atk, int def, Location loc,Map map) {
        this.name = name;
        this.hp = hp;
        this.atk = atk;
        this.def = def;
        this.loc = loc;
        this.map = map;
    }

    public String getName() {
        return name;
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
    public Map getMap(){return map;}
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
