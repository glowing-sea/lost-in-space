package base;
import java.util.Objects;
import java.util.Scanner;

import static base.Player.interact;

/**
 * This the main class where the game runs.
 */

public class Main {
    public static void main(String[] args) {

        // Get and print the initial game state from starr
        State state = GameConfiguration.starr[0];
        System.out.println(state);

        // Set up the scanner
        Scanner scanner = new Scanner(System.in);
        String input;

        // keep taking key input until a "q" is found.
        do {
            input = scanner.next();

            // Update the game state according to the inputs
            switch (input){
                case "w" -> state.player.forward(state.map);
                case "s" -> state.player.backward(state.map);
                case "d" -> state.player.right(state.map);
                case "a" -> state.player.left(state.map);
                case "i" -> Player.getDestination(state); //print current location and destination location
                case "f" -> Player.interact(state);

                //For each potential inventory state, code won't break and will use the most currently occupied slot
                case "1" -> Player.useItem(state,1);
                case "2" -> Player.useItem(state,2);
                case "3" -> Player.useItem(state,3);
                case "4" -> Player.useItem(state,4);
                case "5" -> Player.useItem(state,5);
            }
            Location initial = new Location(0,1);
            // If the requirement to moving to the next game level is satisfied, move to the next game level.
            if (GameConfiguration.LEVEL0_LEVEL_UP.requirementSatisfied(state)) {
                System.out.println("continue adventure");
                state.clearall();
                state.gameLevelUp(GameConfiguration.starr[state.level + 1]);
            }
            if (GameConfiguration.LEVEL1_LEVEL_UP.requirementSatisfied(state)) {
                System.out.println("next level: continue");
                state.clearall();
                state.gameLevelUp(GameConfiguration.starr[state.level + 1]);
            }
            if (GameConfiguration.LEVEL3_LEVEL_UP.requirementSatisfied(state)) {
                System.out.println("next level: finish");
                state.clearall();
                state.gameLevelUp(GameConfiguration.starr[state.level + 1]);
            }
            // Print out the updated game state
            System.out.println(state);
        } while (!Objects.equals(input, "q"));
    }
}
