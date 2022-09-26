package base;


import java.util.Objects;
import java.util.Scanner;

public class keyEventHandler {
    /**
     * Display the current game state according to the player's movement.
     * Any invalid input display's a Try again message
     *
     * @param state the current game state.
     */
    public void keyEventHandler(State state) {
        Scanner scanner = new Scanner(System.in);
        String input;
        // keep taking key input until a "q" is found.
        do {
            input = scanner.next();
            String[] tokens = input.split("-");
            String value = "";
            if (tokens.length == 2)
                value = tokens[1];

            // Update the game state according to the inputs

            switch (tokens[0]) {
                // Movement & Interaction
                case "w","s","a","d" -> state.player.move(state, tokens[0], 1);
                case "ww","ss","aa","dd" -> state.player.move(state, tokens[0], 2);
                case "fw","fa","fs","fd", "f" -> Player.interact(state, input);

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

                case "q" -> {
                    System.out.println("=== Thank you for playing our game. See you soon. ===");
                    continue;
                }

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

            System.out.println(state);
        } while (!Objects.equals(input, "q"));
    }
}
