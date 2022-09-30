package tests;
import base.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.GameStateExamples;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test functions in the Enemy Class
 */

public class PlayerTest {

    // Initialise all fields in the GameConfiguration class
    @BeforeAll
    public static void initialise(){
        GameConfiguration.initialise();
    }

    State st;
    Player p;

    @BeforeEach
    public void setup(){
        st = new GameStateExamples().TEST_STATE_ONE;
        p = st.player;
    }

    @Test
    public void equalPlayer(){
        Player p1 = new Player("Jack", 100, 100, 100, new Location(0,1),20, 20);
        Player p2 = new Player("Jack", 100, 100, 100, new Location(0,1),20, 20);
        Player p3 = new Player("Amy", 100, 100, 100, new Location(0,1),20, 20);
        Player p4 = new Player("Jack", 101, 100, 100, new Location(0,1),20, 20);
        Player p5 = new Player("Jack", 100, 101, 100, new Location(0,1),20, 20);
        Player p6 = new Player("Jack", 100, 101, 101, new Location(0,1),20, 20);
        Player p7 = new Player("Jack", 100, 101, 101, new Location(1,1),20, 20);
        Player p8 = new Player("Jack", 100, 101, 101, new Location(1,0),20, 20);
        Player p9 = new Player("Jack", 100, 101, 101, new Location(1,0),21, 20);
        Player p10 = new Player("Jack", 100, 101, 101, new Location(1,0),21, 21);
        assertEquals(p1,p1);
        assertEquals(p1,p2);
        assertNotEquals(p1,p3);
        assertNotEquals(p1,p4);
        assertNotEquals(p1,p5);
        assertNotEquals(p1,p6);
        assertNotEquals(p1,p7);
        assertNotEquals(p1,p8);
        assertNotEquals(p1,p9);
        assertNotEquals(p1,p10);
    }

    @Test
    public void interactTest(){
        // Kill Enemy
        p.setLoc(new Location(0,3));
        assertTrue(Player.interact(st, "fd",""));
        // Talk to an NPC
        p.setLoc(new Location(2,0));
        assertTrue(Player.interact(st, "f",""));
        st.interacting = null; // Force leave
        // Talk to a Merchant
        p.setLoc(new Location(3,5));
        assertTrue(Player.interact(st, "f",""));
        st.interacting = null; // Force leave
        // Pick an item
        p.setLoc(new Location(3,3));
        assertTrue(Player.interact(st, "fa",""));
        // Nothing to interact with
        p.setLoc(new Location(0,0));
        assertFalse(Player.interact(st, "fa",""));
        System.out.println(st);
    }

    @Test
    public void takeOutItemTest(){
        Item item = new Item(new Location(-1,-1), ItemType.HP_Boost);
        p.addItem(item);
        p.addItem(item);
        p.addItem(item);
        p.addItem(item);
        p.addItem(item);

        // Normal Use
        assertTrue(Player.takeOutItem(st,"5",0));
        // Normal drop
        assertTrue(Player.takeOutItem(st,"4",1));
        // Normal view
        assertTrue(Player.takeOutItem(st,"3",2));
        assertTrue(Player.takeOutItem(st,"3",2));

        // Not well-formed
        assertFalse(Player.takeOutItem(st,"NotANUmber",2));
        // Out of bound
        assertFalse(Player.takeOutItem(st,"30",2));
    }
    @Test
    public void getQuestTest(){
        // Pretend to be a finished Game State
        st.level = GameConfiguration.GAME_STATES.length - 1;
        assertFalse(Player.getQuest(st));
        // Unfinished Game State
        st.level = 0;
        assertTrue(Player.getQuest(st));
    }

    @Test
    public void moveWalkTest() {
        st.player.setLoc(new Location(2, 3));

        // Walk
        p.move(st, "w", 1);
        assertEquals(new Location(1, 3), p.getLoc());
        p.move(st, "s", 1);
        assertEquals(new Location(2, 3), p.getLoc());
        p.move(st, "a", 1);
        assertEquals(new Location(2, 2), p.getLoc());
        p.move(st, "d", 1);
        assertEquals(new Location(2, 3), p.getLoc());
    }

    @Test
    public void moveJumpTest() {
        st.player.setLoc(new Location(2, 3));

        // Jump
        p.move(st, "w", 2);
        assertEquals(new Location(0, 3), p.getLoc());
        p.move(st, "s", 2);
        assertEquals(new Location(2, 3), p.getLoc());
        p.move(st, "a", 2);
        assertEquals(new Location(2, 1), p.getLoc());
        p.move(st, "d", 2);
        assertEquals(new Location(2, 3), p.getLoc());
    }
    @Test
    public void moveBlockTest() {
        // Blocked
        st.player.setLoc(new Location(3,5));
        System.out.println(st);
        p.move(st, "w", 2);
        assertEquals(new Location(3,5),p.getLoc());
        p.move(st, "s", 2);
        assertEquals(new Location(3,5),p.getLoc());
        p.move(st, "a", 2);
        assertEquals(new Location(3,5),p.getLoc());
        st.player.setLoc(new Location(3,3));
        p.move(st, "d", 2);
        assertEquals(new Location(3,3),p.getLoc());
    }

    @Test
    public void addItemTest() {
        Item item = new Item(new Location(-1,-1),ItemType.HP_Boost);
        p.addItem(item);
        p.addItem(item);
        p.addItem(item);
        p.addItem(item);
        p.addItem(item);
        // Almost Full
        assertTrue(p.addItem(item));
        // Already Full
        assertFalse(p.addItem(item));
        // Capacity Expended
        p.setCapacity(p.getCapacity() + 2);
        assertTrue(p.addItem(item));
        assertTrue(p.addItem(item));
        assertFalse(p.addItem(item));
        assertEquals(8, p.getInventory().size());
    }

    @Test
    public void collectExpTest() {
        assertEquals(20,p.getExp());
        p.collectExp(150, st);
        assertEquals(70,p.getExp());
        // Leveled UP
        assertEquals(21,p.getPlayerLevel());
        p.collectExp(10,st);
        // Not Leveled UP
        assertEquals(21,p.getPlayerLevel());
    }
}
