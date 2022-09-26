package base;

/**
 * An object storing the attributes and method of an enemy.
 */

public class Enemy extends Character {

    int expReward; // The amount of exp that can obtain by the player after killing it.
    Item itemReward;

    public Enemy(String name, int hp, int atk, int def, Location loc, int expReward, Item itemReward) {
        super(name, hp, atk, def, loc, 'E');
        this.expReward = expReward;
        this.itemReward = itemReward;
    }
    public boolean isDead(){
        return this.getHp() <= 0;
    }

    /**
     * fight against nearby enemy
     * notice: our player still survive even if his health <=0
     * @param st the game state
     * @return the enemy you killed
     */
    public Enemy fight(State st){

        int enemyAtk = this.getAtk()-st.player.getDef();// enemy attack you
        if(enemyAtk >0) {st.player.setHp(st.player.getHp() - enemyAtk);}

        int yourAtk = st.player.getAtk() - this.getDef(); // you attack enemy
        if(yourAtk >0) {this.setHp(this.getHp() - yourAtk);}

        if (this.getHp() <= 0) {
            this.setHp(0);
        }
        return this;
    }


    @Override
    public boolean interact(State st) {
        this.fight(st);
        st.messageBox.putMessage("System: you are fighting with the enemy" + this.getName() + "!");
        st.messageBox.putMessage("System: enemy's HP: " + this.getHp() + ".");

        if (this.getHp() <= 0){
            st.messageBox.putMessage("System: you killed the enemy" + this.getName() + "!");
            st.enemies.remove(this);
            st.player.collectExp(this.expReward, st);
        }
        return true;
    }
}
