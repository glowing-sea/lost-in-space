package src.main;
public class Player extends Character{
    private int exp;
    private int playerLevel;

    public Player(String name, int hp, int atk, int def, Location loc, int exp, int playerLevel) {
        super(name, hp, atk, def, loc);
        this.exp = exp;
        this.playerLevel = playerLevel;
    }

    public void forward(){
        int newX = getLoc().getX() - 1;
        if (newX >= 0 && newX <= 8)
            getLoc().setX(newX);
    }
    public void backward(){
        int newX = getLoc().getX() + 1;
        if (newX >= 0 && newX <= 8)
            getLoc().setX(newX);}
    public void right(){
        int newY = getLoc().getY() + 1;
        if (newY >= 0 && newY <= 8)
            getLoc().setY(newY);}
    public void left(){
        int newY = getLoc().getY() - 1;
        if (newY >= 0 && newY <= 8)
            getLoc().setY(newY);}

}

