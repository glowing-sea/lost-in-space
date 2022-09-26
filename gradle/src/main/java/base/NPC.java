package base;

/**
 * An object storing the attributes and method of an NPC
 */
public class NPC extends Character {

    // The text that will show when the player interact with them
    String[] dialogue;

    public NPC(String name, Location loc, String[] dialogue) {
        super(name, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, loc, 'N');
        this.dialogue = dialogue;
    }

    @Override
    public boolean interact(State st) {
        for (String talk : dialogue){
            if (talk.charAt(0) == '`'){ // Talked my the player
                talk = talk.substring(1);
                st.messageBox.putMessage(st.player.getName() + " (" + this.getSYMBOL() + "): " + talk);
            } else {
                st.messageBox.putMessage(this.getName() + " (" + this.getSYMBOL() +"): " + talk);
            }
        }
        return true;
    }
}
