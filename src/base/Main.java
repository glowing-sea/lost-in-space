package src.base;
import java.util.Objects;
import java.util.Scanner;

import static src.base.Player.interact;

/**
 * This the main class where the game runs.
 */

public class Main {
    public static void main(String[] args) {

        // Get and print the initial game state from starr
        State state = GameConfiguration.starr[0];
        System.out.println(state);

        // Pass the current state
        playerMovement playerMovement = new playerMovement();
        playerMovement.playerMovement(state);
    }
}
