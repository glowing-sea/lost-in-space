package base;
import java.util.Objects;
import java.util.Scanner;

/**
 * A class managing the key inputs from the user
 */

public class keyEventHandler {
    /**
     * Update and display the game state after the use input a valid command.
     * Any invalid input display's a Try again message.
     *
     * @param state the current game state.
     * @return 0 if the game continue and 100 if the game ends.
     */
    public static int keyEventHandler(State state, String input) {

        String[] tokens = input.split("-");
        String value = "";
        if (tokens.length == 2)
            value = tokens[1];

        // Update the game state according to the inputs
        int op = -1;
        switch (tokens[0]) {
            // Movement & Instant Interaction
            case "w","s","a","d" -> state.player.move(state, tokens[0], 1); // disable when st.interacting = true;
            case "ww","ss","aa","dd" -> state.player.move(state, tokens[0], 2); // disable when st.interacting = true;
            case "fw","fa","fs","fd", "f" -> Player.interact(state, input, value); // disable when st.interacting = true;


            // Continuous Interaction
            case "A","(A)", "B","(B)", "G","(G)", "buy"-> Player.interact(state, tokens[0], value); // disable when st.interacting = false;

            // save game        
            case "save" -> Main.saveGame(state,value);
            case "load" -> Main.loadGame(state,value);

            case "q" -> { // Should we save our game here? --Zhishang
                    System.out.println("=== Thank you for playing our game. See you soon. ===");
                    return 100;
            }    

            // Inventory Management
            case "use" -> Player.takeOutItem(state, value, 0);
            case "drop" -> Player.takeOutItem(state, value, 1);
            case "view" -> Player.takeOutItem(state, value, 2);

            // Special Inputs
            case "tips" -> {for (String st : GameConfiguration.tips){state.messageBox.putMessage(st);}}
            case "quest" -> Player.getQuest(state); //print current location and destination location
            case "levelup" -> {
                if (!state.isFinish())
                    state.gameLevelUp(GameConfiguration.GAME_STATES[state.level + 1]);} // For testing only, direct get to the next level.

            default -> {
                state.messageBox.putMessage("Sorry, '" + input + "' is not a value input.");
            }
        }

        // If the player has not reached the final game level, check if the player can proceed to the next level
        if (!state.isFinish()){
            int level = state.level;
            GameLevelUpRequirement levelUpRequirement = GameConfiguration.LEVEL_UP_REQUIREMENTS[level];
            if (levelUpRequirement.requirementSatisfied(state)){
                state.gameLevelUp(GameConfiguration.GAME_STATES[level + 1]);
            }
        }
        // If the player's hp become zero, the game is over.
        if (state.isGameOver()){
            System.out.println("=== Oh no, you lost all your HP. ===");
            return 100;
        }

        return 0;
    }
}
