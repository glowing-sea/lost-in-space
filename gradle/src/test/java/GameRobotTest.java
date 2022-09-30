package tests;
import base.*;
import org.junit.jupiter.api.BeforeEach;
import utility.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests all functions and inputs in the game
 */
public class GameRobotTest {
    @BeforeAll
    public static void initialise(){
        GameConfiguration.initialise();
    }


    List<String> Level0 = new RobotInstructions().Level0();
    List<String> Level1 = new RobotInstructions().Level1();
    List<String> Level2 = new RobotInstructions().Level2();

    @Test
    public void levelUpWhenReachALocation() {

        boolean gamewon = false;
        State loadingState = GameConfiguration.GAME_STATES[0];
        for (String input:Level0) {
            keyEventHandler.keyEventHandler(loadingState, input);

        }
        for (String input:Level1) {
            keyEventHandler.keyEventHandler(loadingState, input);

        }
        for (String input:Level2) {
            keyEventHandler.keyEventHandler(loadingState, input);

        }
        if (loadingState.level == 3) gamewon = true;

        if (!gamewon) System.out.print(loadingState);
        assertEquals(gamewon, true);
    }

}
