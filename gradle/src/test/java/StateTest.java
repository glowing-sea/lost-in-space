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

    GameStateExamples ex;

    @BeforeEach
    public void setup() {
        ex = new GameStateExamples();
        st = new GameStateExamples().TEST_STATE_ONE;
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
