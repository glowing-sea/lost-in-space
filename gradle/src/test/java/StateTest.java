package tests;
import base.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utility.GameStateExamples;


import static org.junit.jupiter.api.Assertions.*;

/**
 * Test functions in the Enemy Class
 */

public class StateTest {

    State st;
    Player p;

    @BeforeEach
    public void setup() {
        st = GameStateExamples.TEST_STATE_ONE;
        p = st.player;
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
        State st = GameStateExamples.TEST_STATE_TWO;
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
        State stNew = GameStateExamples.TEST_STATE_TWO;
        State st = GameStateExamples.TEST_STATE_TWO;
        st.gameLevelUp(stNew);
        assertEquals(GameStateExamples.TEST_STATE_TWO.map, st.map);
        assertEquals(GameStateExamples.TEST_STATE_TWO.story, st.story);
        assertEquals(GameStateExamples.TEST_STATE_TWO.player.getLoc(), st.player.getLoc());
        assertEquals(GameStateExamples.TEST_STATE_TWO.enemies, st.enemies);
        assertEquals(GameStateExamples.TEST_STATE_TWO.items, st.items);
        assertEquals(GameStateExamples.TEST_STATE_TWO.NPCs, st.NPCs);
        assertEquals(GameStateExamples.TEST_STATE_TWO.merchants, st.merchants);
        assertEquals(GameStateExamples.TEST_STATE_TWO.level, st.level);
    }
}
