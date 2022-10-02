package tests;
import base.*;
import org.junit.jupiter.api.BeforeEach;
import utility.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


/**
 * Perform a finished gameplay.
 * @author Haoting
 */


public class GameAutomaticTester {

    State st;

    @BeforeEach
    public void setup(){
        GameConfiguration.initialise();
        st = GameConfiguration.GAME_STATES[0];
    }

    @Test
    public void playerWinTheGame(){
        assertEquals(0,st.level); // At Level 0

        String inputs = "s fa buy-6 buy-6 buy-6 buy-6 G use-1 use-2 use-3 use-4 dd d fw dd d ss a fa d ww aa " +
                "aa ss fa ss fa fd dd dd s fa ss fa fd dd dd s";
        RobotInstructions.inputReader(st,inputs);
        assertEquals(1,st.level); // At Level 1

        inputs = "use-1 use-1 use-1 use-1 s fa dd dd d ss aa fa fs ss ss s a fa dd dd ";
        RobotInstructions.inputReader(st,inputs);
        assertEquals(2,st.level); // At Level 2

        inputs = "use-1 use-1 use-1 ss ss s fs fd dd fs dd d fd fs s fs ww w d fd ss use-1 use-1 use-1 use-1 use-1 use-1 s";
        RobotInstructions.inputReader(st,inputs);
        assertEquals(3,st.level); // At Level 3

        inputs = "dd dd ss ss s aa aa w dd aa ww w fw fw fw ww dd dd s dd fs a a w aa aa ss s dd d s fd dd d s use-1 ";
        RobotInstructions.inputReader(st,inputs);
        assertEquals(5,st.level); // At Level 4

        assertTrue(st.isFinish());
    }

    @Test
    public void playerFailTheGame(){
        assertEquals(0,st.level); // At Level 0

        String inputs = "d d fd s dd dd ss a fa d ww aa a a ss ss s dd dd s dd dd d ss aa fa fs ss s dd d ss ss ss d fd ";
        RobotInstructions.inputReader(st,inputs);
        assertEquals(2,st.level);
        assertTrue(st.isGameOver());
    }
}
