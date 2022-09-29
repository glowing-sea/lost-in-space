package tests;
import base.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        ArrayList<Enemy> enemies = new ArrayList<>();
        Item item = new Item(new Location(-1,-1), ItemType.HP_Boost);
        enemyEasy = new Enemy("easy",10,30,30,new Location(1,0),20,item);
        enemyStrong = new Enemy("strong",10,150,50,new Location(1,2),150,item);
        enemyTough = new Enemy("tough",100,1000,80,new Location(2,1),350,item);
        Player player = new Player("Ben",100,100,100,new Location(1,1),0,0);

        enemies.add(enemyEasy);
        enemies.add(enemyStrong);
        enemies.add(enemyTough);

        String[] mapTest = new String[] {
                "         ",
                "EXE      ",
                "-E---- --",
                "         ",
                "-- ------",
                "         ",
                "       H ",
                "---------",
                "         "};
        char[] testWalls = new char[] {'-'};
        Map testMap = new Map(-1,mapTest,testWalls);
        st = new State(testMap,"abc",0, player,enemies,new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
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
