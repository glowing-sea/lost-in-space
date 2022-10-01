package tests;
import base.*;
import utility.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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


    List<String> Level0 = RobotInstructions.Level0();
    List<String> Level1 = RobotInstructions.Level1();
    List<String> Level2 = RobotInstructions.Level2();

    @Test
    public void levelUpWhenReachALocation() {

        GameConfiguration.initialise();

        boolean gameWon = false;
        State loadingState = GameConfiguration.GAME_STATES[0];
        System.out.println(loadingState);
        for (String input:Level0) {
            KeyEventHandler.keyEventHandler(loadingState, input);
            System.out.println("User Input: " + input);
            System.out.println(loadingState);
        }
        for (String input:Level1) {
            KeyEventHandler.keyEventHandler(loadingState, input);
            System.out.println("User Input: " + input);
            System.out.println(loadingState);

        }
        for (String input:Level2) {
            KeyEventHandler.keyEventHandler(loadingState, input);
            System.out.println("User Input: " + input);
            System.out.println(loadingState);
        }
        if (loadingState.level == 3) gameWon = true;

        if (!gameWon) System.out.print(loadingState);
        assertEquals(gameWon, true);
    }
}
