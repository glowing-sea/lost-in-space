// package gradle.src.test.java;
import base.*;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
public class enemies_test {
    ArrayList<Enemy> enemies = new ArrayList<>();
    Enemy enemy1 = new Enemy(null,10,30,30,new Location(0,2),20,null);
    Enemy enemyStrong = new Enemy("strong",10,150,50,new Location(1,0),20,null);
    Enemy enemyTough = new Enemy("tough",100,101,80,new Location(1,2),20,null);
//    enemies.add(enemy1);
    Player player1 = new Player(null,100,100,100,new Location(0,1),0,0);

    String[] maptest = new String[] {

            " XE      ",
            "E        ",
            "-E---- --",
            "         ",
            "-- ------",
            "         ",
            "       H ",
            "---------",
            "         "};
    char[] testwalls = new char[] {'-','E'};
    Map testmap = new Map(-1,maptest,testwalls);
    State teststate = new State(testmap,"abc",0, player1,enemies,null, null, null);

    @Test
    public void farAway(){
        enemies.add(enemy1);
        enemies.add(enemyStrong);
        enemies.add(enemyTough);
        Location previous = player1.getLoc();
        player1.setLoc(new Location(5,5));
        Player.interact(teststate, "f");
        assertFalse(enemy1.isDead() || enemyStrong.isDead() || enemyTough.isDead());
        player1.setLoc(previous);

    }
    @Test
    public void freewin(){
        enemies.add(enemy1);
        Player.interact(teststate, "f");
        assertEquals(100,player1.getHp());
        assertEquals(-60,enemy1.getHp());
        assertTrue(enemy1.isDead());

    }
    @Test
    public void fightStrongEnemy(){
        enemies.add(enemyStrong);
        Player.interact(teststate, "f");
        assertEquals(100,player1.getHp());
        //move near it
        player1.left(testmap);
        Player.interact(teststate, "f");
        assertEquals(50,player1.getHp());
        assertEquals(-40,enemyStrong.getHp());
        assertTrue(enemyStrong.isDead());
    }
    @Test
    public void fightToughEnemy(){
        enemies.add(enemyTough);

        Player.interact(teststate, "f");
        assertEquals(100,player1.getHp());
        //move near it
        player1.setLoc(new Location(2,2));
        assertTrue(player1.nearby(enemyTough.getLoc()));
        Player.interact(teststate, "f");
        assertEquals(95,player1.getHp());
        assertEquals(0,enemyTough.getHp());
        assertTrue(enemyTough.isDead());
    }

    @Test
    public void fightTwo(){
        enemies.add(enemyStrong);
        enemies.add(enemyTough);
        Player.interact(teststate, "f");
        assertEquals(100,player1.getHp());
        //move near them
        player1.setLoc(new Location(1,1));
        Player.interact(teststate, "f");
        System.out.println("enemyStrong "+enemyStrong.getHp()+" enemyTough "+enemyTough.getHp());
        assertTrue(enemyStrong.isDead() && enemyTough.isDead());
        assertEquals(45,player1.getHp());
    }
}
