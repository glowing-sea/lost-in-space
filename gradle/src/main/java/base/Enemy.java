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

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Enemy e))
            return false;
        if (this.expReward != e.expReward)
            return false;
        if (this.itemReward == null) {
            if (e.itemReward != null)
                return false;
        } else {
            if (!this.itemReward.equals(e.itemReward))
                return false;
        }
        return super.equals(e);
    }

    /**
     * Check if an enemy hp is less than zero
     * @return if the enemy is dead
     */
    public boolean isDead(){
        return this.getHp() <= 0;
    }

    /**
     * fight against nearby enemy
     * notice: the game will exit if the player loses all their hp
     * @param st the game state
     * @return the enemy you killed
     */
    public Enemy fight(State st){

        int enemyAtk = this.getAtk()-st.player.getDef();// enemy attack you
        if(enemyAtk >0) {st.player.setHp(st.player.getHp() - enemyAtk);}

        int yourAtk = st.player.getAtk() - this.getDef(); // you attack enemy
        if(yourAtk >0) {this.setHp(this.getHp() - yourAtk);}

        if (this.getHp() <= 0) { // Reset HP to 0 if dead
            this.setHp(0);
        }
        if (st.player.getHp() <= 0) { // Reset HP to 0 if dead
            st.player.setHp(0);
        }

        return this;
    }

    /**
     * The function tells what happen if the player interact with an enemy.
     * 1. Fight: update their hp.
     * 2. IF the enemy is dead, remove them from the map.
     * @param st the game state
     */
    @Override
    public boolean interact(State st, int option) {
        this.fight(st);
        st.messageBox.putMessage("You are fighting with the enemy " + this.getName() + "!");
        st.messageBox.putMessage("Enemy's HP: " + this.getHp() + " in this turn.");

        if (this.getHp() <= 0){
            st.messageBox.putMessage("You killed the enemy " + this.getName() + "!");
            st.enemies.remove(this);
            st.player.collectExp(this.expReward, st);
            if (this.itemReward != null) {
                st.player.getInventory().add(this.itemReward); // Forcibly Add
                st.messageBox.putMessage("You obtained " + "[ " + this.itemReward + " ]" + " from the enemy.");
            }
        }
        return true;
    }
}
