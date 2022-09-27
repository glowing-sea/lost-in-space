package base;

/**
 * An object storing the attributes and method of an NPC
 * When the player interact with a NPC, their conversation will be shown in the message box.
 */
public class NPC extends Character {

    // The text that will show when the player interact with them
    String[] dialogue;

    public NPC(String name, Location loc, String[] dialogue) {
        super(name, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, loc, 'N');
        this.dialogue = dialogue;
    }

    /**
     * The function tell what happens when the player interact with an NPC
     * When the player interact with a NPC, their conversation will be shown in the message box.
     * @param st the current game state
     * @return ture always
     */
    @Override
    public boolean interact(State st) {
        for (String talk : dialogue){
            if (talk.charAt(0) == '`'){ // Talked my the player
                talk = talk.substring(1);
                st.messageBox.putMessage(st.player.getName() + " (" + st.player.getSYMBOL() + "): " + talk);
            } else {
                st.messageBox.putMessage(this.getName() + " (" + this.getSYMBOL() +"): " + talk);
            }
        }
        return true;
    }
}
