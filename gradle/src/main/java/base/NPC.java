package base;

public class NPC extends Character{

    // The text that will show when the player interact with them
    String dialogue;

    public NPC(String name, Location loc, String dialogue) {
        super(name, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, loc, 'N');
    }
}
