package src.base;

/**
 * An object storing the attributes and method of an enemy.
 */

public class Enemy extends Character{
    boolean isDead;

    public Enemy(String name, int hp, int atk, int def, Location loc, boolean isDead) {
        super(name, hp, atk, def, loc);
        this.isDead = isDead;
    }
    public boolean getisDead(){
        return isDead;
    }
    public void fight(State st){
        // if an enemy is nearby player, fight against it
        if (st.player.nearby(this.getLoc())) {
            while (!this.isDead) { //fight until death!

                int enemyatk = this.getAtk()-st.player.getDef();// enemy attack you
                if(enemyatk >0) {st.player.setHp(st.player.getHp() - enemyatk);}

                int youratk = st.player.getAtk() -this.getDef(); // you attack enemy
                if(youratk >0) {this.setHp(this.getHp() - youratk);}

                System.out.println(" enemies hp left: "+this.getHp());
                if (this.getHp() <= 0) {
                    this.isDead = true;
                    refreshLocation(st,this.getLoc(),' ');
                    System.out.println("left hp: "+st.player.getHp());
                }
            }
            Location tomb = new Location(-1, -1); //sent enemies to tomb, get out of my way
            this.setLoc(tomb);
            System.out.println("you defeat "+this.getName());
        }
    }

    /**
     * input location, the char you will replace with
     * output replace the location with the char you set
     * @param location
     * @param newchar
     * @param st
     */
    // usage: kill enemies
    public static void refreshLocation(State st, Location location, char newchar){ // entre the new string and location where you want the string to be
        int X = location.getX();
        int Y = location.getY();
        Map map = st.map;
        if(X<0||X>8||Y<0||Y>8){
            throw new NullPointerException("this is <0 or >8");
        }else{
//            Map newmap = new Map(this.mapID,this.map,this.walls);
            String[] thenew = map.getMap();
            char[] newline = thenew[X].toCharArray();
            newline[Y] = newchar;
            thenew[X] = String.valueOf(newline);
            st.map = new Map(map.getMapID(),thenew,map.getWalls());

        }
    }
}
