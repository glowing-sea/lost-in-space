package src.main;
import java.util.Objects;
import java.util.Scanner;

/**
 * This the main class where the game runs.
 */

public class Main {
    public static void main(String[] args) {

        // Get and print the initial game state from starr
        State st = GameConfiguration.starr[0];
        System.out.println(st);

        // Set up the scanner
        Scanner in = new Scanner(System.in);
        String input;

        // keep taking key input until a "q" is found.
        do {
            input = in.next();

            // Update the game state according to the inputs
            switch (input){
                case "w" -> st.player.forward(st.map);
                case "s" -> st.player.backward(st.map);
                case "d" -> st.player.right(st.map);
                case "a" -> st.player.left(st.map);
            }

            // If the requirement to moving to the next game level is satisfied, move to the next game level.
            if (GameConfiguration.LEVEL0_LEVEL_UP.requirementSatisfied(st))
                st.gameLevelUp(GameConfiguration.starr[st.level + 1]);


//            if(input.equals("f")) {
//                if(st.finish()){
//                    break;
//                }else {
//                    Location current =  st.player.getLoc();
//                    Location destination = GameConfiguration.LEVEL0_LEVEL_UP.getLocation();
//                    System.out.println("You are in (" + current.getX() + ", " + current.getY() + "), go to: (" + destination.getX() + ", " + destination.getY() + ")!");
//                }
//            }

            // Print out the updated game state
            System.out.println(st);
        } while (!Objects.equals(input, "q"));
    }
}
