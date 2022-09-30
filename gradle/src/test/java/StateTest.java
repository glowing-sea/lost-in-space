package tests;
import base.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.GameStateExamples;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Test functions in the Enemy Class
 */

public class StateTest {

    // Initialise all fields in the GameConfiguration class
    @BeforeAll
    public static void initialise(){
        GameConfiguration.initialise();
    }

    State st;
    Player p;

    GameStateExamples ex;

    @BeforeEach
    public void setup() {
        GameStateExamples.initialise();
        st = GameStateExamples.TEST_STATE_ONE;
        p = st.player;
    }

    @Test
    public void equalState() {
        GameStateExamples ex = new GameStateExamples();
        State s1 = ex.TEST_STATE_ONE;
        State s2 = ex.TEST_STATE_TWO;
        GameStateExamples ex1 = new GameStateExamples();
        State s3 = ex1.TEST_STATE_TWO;
        assertEquals(s1,s1);
        assertEquals(s2,s3);
        assertNotEquals(s1,s3);
    }

    @Test
    public void gameOverTest() {
        p.setHp(0);
        assertTrue(st.isGameOver());
        st.level = GameConfiguration.GAME_STATES.length - 1;
        assertTrue(st.isFinish());
    }
    @Test
    public void gameLevelUpMaintainAll(){
        State stNew = new State(null,null,3,null,null,null,null,null);
        st = new GameStateExamples().TEST_STATE_TWO;
        st.gameLevelUp(stNew);
        assertNotNull(st.map);
        assertNotNull(st.story);
        assertNotNull(st.player);
        assertNotNull(st.enemies);
        assertNotNull(st.items);
        assertNotNull(st.NPCs);
        assertNotNull(st.merchants);
        assertEquals(3,st.level);
    }

    @Test
    public void gameLevelUpResetAll(){
        State stNew = ex.TEST_STATE_ONE;
        State st = ex.TEST_STATE_TWO;
        st.gameLevelUp(stNew);
        assertEquals(stNew.map, st.map);
        assertEquals(stNew.story, st.story);
        assertEquals(stNew.player.getLoc(), st.player.getLoc());
        assertEquals(stNew.enemies, st.enemies);
        assertEquals(stNew.items, st.items);
        assertEquals(stNew.NPCs, st.NPCs);
        assertEquals(stNew.merchants, st.merchants);
        assertEquals(stNew.level, st.level);
    }
}
