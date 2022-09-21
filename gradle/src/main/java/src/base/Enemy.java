package src.base;


/**
 * An object storing the attributes and method of an enemy.
 */

public class Enemy extends Character {
    boolean isDead;

    public Enemy(String name, int hp, int atk, int def, Location loc, boolean isDead) {
        super(name, hp, atk, def, loc);
        this.isDead = isDead;
    }
    public boolean getisDead(){
        return isDead;
    }

    /**
     * fight against nearby enemy
     * notice: our player still survive even if his health <=0
     * @param st the game state
     * @return the enemy you killed
     */
    public Enemy fight(State st){
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
//                    refreshLocation(st,this.getLoc(),' ');

                    System.out.println("remove "+this.getName());
                    System.out.println("left hp: "+st.player.getHp());
                }
            }
            Location tomb = new Location(-1, -1); //sent enemies to tomb, get out of my way
            this.setLoc(tomb);
            System.out.println("you defeat "+this.getName());
            return this;
        }
        return null;
    }


}
