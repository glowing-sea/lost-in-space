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
            int op = -1;
            switch (tokens[0]) {
                // Movement & Interaction
                case "w","s","a","d" -> state.player.move(state, tokens[0], 1);
                case "ww","ss","aa","dd" -> state.player.move(state, tokens[0], 2);
                case "fw","fa","fs","fd", "f" -> Player.interact(state, input);

                //talking with NPCs

                case "A","(A)" -> op = 1;
                case "B","(B)" -> op = 2;
                case "L","(L)" -> op = 3;
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

                case "q" -> { // Should we save our game here? --Zhishang
                    System.out.println("=== Thank you for playing our game. See you soon. ===");
                    continue;
                }

                default -> {
                    state.messageBox.putMessage("Sorry, '" + input + "' is not a value input.");
                }
            }

            NPC npc1= null;
            for (NPC npc: state.NPCs){
                if(npc.contacting){
                    npc1 = npc;
                    break;
                }
            }
            if(npc1!=null) {
                npc1.setOperations(op);
                npc1.interact(state);
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
                break;
            }

            System.out.println(state);
        } while (!Objects.equals(input, "q"));
    }
}
