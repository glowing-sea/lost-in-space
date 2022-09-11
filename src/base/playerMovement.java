package src.base;

import java.util.Objects;
import java.util.Scanner;

public class playerMovement {
    /**
     * Display the current game state according to the player's movement.
     * Any invalid input display's a Try again message
     * @param state the current game state.
     */
    public void playerMovement(State state) {
        Scanner scanner = new Scanner(System.in);
        String input;
        // keep taking key input until a "q" is found.
        do {
            input = scanner.next();

            // Update the game state according to the inputs
            switch (input) {
                case "w" -> state.player.forward(state.map);
                case "s" -> state.player.backward(state.map);
                case "d" -> state.player.right(state.map);
                case "a" -> state.player.left(state.map);
                case "i" -> Player.getDestination(state); //print current location and destination location
                case "f" -> Player.interact(state);
                case "q" -> {
                    System.out.println("Thank you for playing our game. See you soon.");
                    continue;
                }
                default -> System.out.println("Sorry, the game cannot understand '" + input + "'. Please try again.");
            }

            // If the requirement to moving to the next game level is satisfied, move to the next game level.
            if (GameConfiguration.LEVEL0_LEVEL_UP.requirementSatisfied(state))
                state.gameLevelUp(GameConfiguration.starr[state.level + 1]);
            if (GameConfiguration.LEVEL1_LEVEL_UP.requirementSatisfied(state))
                state.gameLevelUp(GameConfiguration.starr[state.level + 1]);
            // Print out the updated game state
            System.out.println(state);
        } while (!Objects.equals(input, "q"));

    }
}
