package src.tests;

import org.junit.Test;
import src.base.*;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class enemies_test {
    ArrayList<Enemy> enemies = new ArrayList<>();
    Enemy enemy1 = new Enemy(null,10,30,30,new Location(0,2),false);
    Enemy enemyStrong = new Enemy("strong",10,150,50,new Location(1,0),false);
    Enemy enemyTough = new Enemy("tough",100,101,80,new Location(1,2),false);
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
    State teststate = new State(testmap,player1,enemies,"abc",0,null);

    @Test
    public void farAway(){
        enemies.add(enemy1);
        enemies.add(enemyStrong);
        enemies.add(enemyTough);
        Location previous = player1.getLoc();
        player1.setLoc(new Location(5,5));
        Player.interact(teststate);
        assertFalse(enemy1.getisDead() || enemyStrong.getisDead() || enemyTough.getisDead());
        player1.setLoc(previous);

    }
    @Test
    public void freewin(){
        enemies.add(enemy1);
        Player.interact(teststate);
        assertEquals(100,player1.getHp());
        assertEquals(-60,enemy1.getHp());
        assertTrue(enemy1.getisDead());

    }
    @Test
    public void fightStrongEnemy(){
        enemies.add(enemyStrong);
        Player.interact(teststate);
        assertEquals(100,player1.getHp());
        //move near it
        player1.left(testmap);
        Player.interact(teststate);
        assertEquals(50,player1.getHp());
        assertEquals(-40,enemyStrong.getHp());
        assertTrue(enemyStrong.getisDead());
    }
    @Test
    public void fightToughEnemy(){
        enemies.add(enemyTough);
        Player.interact(teststate);
        assertEquals(100,player1.getHp());
        //move near it
        player1.setLoc(new Location(2,3));
        Player.interact(teststate);
        assertEquals(95,player1.getHp());
        assertEquals(0,enemyTough.getHp());
        assertTrue(enemyTough.getisDead());
    }

    @Test
    public void fightTwo(){
        enemies.add(enemyStrong);
        enemies.add(enemyTough);
        Player.interact(teststate);
        assertEquals(100,player1.getHp());
        //move near them
        player1.setLoc(new Location(1,1));
        Player.interact(teststate);
        System.out.println("enemyStrong "+enemyStrong.getHp()+" enemyTough "+enemyTough.getHp());
        assertTrue(enemyStrong.getisDead() && enemyTough.getisDead());
        assertEquals(45,player1.getHp());
    }
}
