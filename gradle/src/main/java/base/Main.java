package base;

/**
 * This the main class where the game runs.
 */

public class Main {
    public static void main(String[] args) {

        // Get and print the initial game state from starr
        State state = GameConfiguration.starr[0];
        System.out.println(state);
        // Pass the initial state
        key_event_handler key_event_handler = new key_event_handler();
        key_event_handler.keyEventHandler(state);
    }
}
