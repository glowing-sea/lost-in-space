package tests;
import base.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.GameStateExamples;

import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test functions in the Enemy Class
 */
public class EnemyTests {

    State st;
    Enemy enemyEasy;
    Enemy enemyStrong;
    Enemy enemyTough;

    @BeforeEach
    public void setup(){
        st = new GameStateExamples().TEST_STATE_TWO;
        enemyEasy = st.enemies.get(0);
        enemyStrong = st.enemies.get(1);
        enemyTough = st.enemies.get(2);
    }
    @Test
    public void equalPlayer(){
        Item item1 = new Item(new Location(3,2), ItemType.Inventory_Boost);
        Item item2 = new Item(new Location(5,2), ItemType.HP_Boost);
        Enemy e1 = new Enemy("Goblin1",10,150,50,new Location(0,4),20, item1);
        Enemy e2 = new Enemy("Goblin1",10,150,50,new Location(0,4),20, item1);
        Enemy e3 = new Enemy("Goblin2",10,150,50,new Location(0,4),20, item1);
        Enemy e4 = new Enemy("Goblin1",11,150,50,new Location(0,4),20, item1);
        Enemy e5 = new Enemy("Goblin1",10,151,50,new Location(0,4),20, item1);
        Enemy e6 = new Enemy("Goblin1",10,150,51,new Location(0,4),20, item1);
        Enemy e7 = new Enemy("Goblin1",10,150,50,new Location(0,1),20, item1);
        Enemy e8 = new Enemy("Goblin1",10,150,50,new Location(1,4),20, item1);
        Enemy e9 = new Enemy("Goblin1",10,150,50,new Location(0,4),21, item1);
        Enemy e10 = new Enemy("Goblin1",10,150,50,new Location(0,4),20, item2);
        assertEquals(e1, e1);
        assertEquals(e1, e2);
        assertNotEquals(e1, e3);
        assertNotEquals(e1, e4);
        assertNotEquals(e1, e5);
        assertNotEquals(e1, e6);
        assertNotEquals(e1, e7);
        assertNotEquals(e1, e8);
        assertNotEquals(e1, e9);
        assertNotEquals(e1, e10);
    }


    @Test
    public void expReward(){
        Player.interact(st, "f","");
        assertEquals(0,st.player.getExp()); // Before battle
        Player.interact(st,"fa",""); // Kill enemyEasy
        assertEquals(20,st.player.getExp());
        Player.interact(st,"fd",""); // Kill enemyHard
        assertEquals(70,st.player.getExp());
        assertEquals(1,st.player.getPlayerLevel()); // Leveled up
    }

    @Test
    public void itemReward(){
        Player.interact(st, "f","");
        assertTrue(st.player.getInventory().isEmpty()); // Before battle
        Player.interact(st,"fa",""); // Kill enemyEasy
        assertEquals(ItemType.HP_Boost,st.player.getInventory().get(0).getType());
        Player.interact(st,"fd",""); // Kill enemyHard
        assertEquals(2,st.player.getInventory().size());
    }


    @Test
    public void playerDead(){
        Player.interact(st, "fs","");
        assertEquals(0, st.player.getHp());
    }


    @Test
    public void farAway(){
        st.player.move(st, "w", 1);
        st.player.move(st, "dd", 2);
        st.player.move(st, "dd", 2); // Move away
        Player.interact(st, "fs","");
        Player.interact(st, "fa","");
        Player.interact(st, "fw","");
        Player.interact(st, "fd","");
        assertFalse(enemyEasy.isDead() | enemyTough.isDead() | enemyStrong.isDead());
    }

    @Test
    public void freeWin(){
        Player.interact(st, "fa","");
        assertEquals(100,st.player.getHp());
        assertEquals(0,enemyEasy.getHp());
        assertTrue(enemyEasy.isDead());
    }

    @Test
    public void fightStrongEnemy(){
        System.out.println(st);
        assertEquals(100,st.player.getHp());
        assertFalse(enemyStrong.isDead());

        Player.interact(st, "fd","");
        System.out.println(st);

        assertEquals(50,st.player.getHp());
        assertEquals(0,enemyStrong.getHp());
        assertTrue(enemyStrong.isDead());
    }



    @Test
    public void fightTwo(){
        Player.interact(st, "fa","");
        Player.interact(st, "fd","");
        assertTrue(enemyStrong.isDead() && enemyEasy.isDead());
        assertEquals(50,st.player.getHp());
    }
}
