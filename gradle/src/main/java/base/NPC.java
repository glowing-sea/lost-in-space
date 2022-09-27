package base;

import jdk.swing.interop.SwingInterOpUtils;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * An object storing the attributes and method of an NPC
 * When the player interact with a NPC, their conversation will be shown in the message box.
 */
public class NPC extends Character {

    // The text that will show when the player interact with them
    String[] dialogue;
    int operations; // this is what user option is
    Boolean contacting = false;


    public NPC(String name, Location loc,  int operations,String[] dialogue) {
        super(name, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, loc, 'N');
        this.dialogue = dialogue;
    }
    public void setOperations(int operations) {
        this.operations = operations;
    }
    public void setDialogue(String[] dialogue){this.dialogue = dialogue;}

    /**
     * The function tell what happens when the player interact with an NPC
     * When the player interact with a NPC, their conversation will be shown in the message box.
     * @param st the current game state
     * @return ture always
     */
    public void setContacting(Boolean b){this.contacting = b;}
    @Override
    public boolean interact(State st) {
        int index = 0;
        String Story = "";
        String[] newDia;
        if (this.nearby(st.player.getLoc())) {
            if (operations == 0) {

                for (int i = index; i < this.dialogue.length; i++) {
                    String talk = this.dialogue[i];

                    if (talk.charAt(talk.length() - 1) == 'O') { // Talked my the player
                        //finish talking or player speaks;
                        st.messageBox.putMessage(this.getName() + " (" + this.getSYMBOL() + "): " + talk.substring(0,talk.length()-2));
                        if (this.dialogue[i + 1] == null || this.dialogue[i + 2] == null) {
                            st.messageBox.putMessage("no options set");
                            return true;
                        }
                        if (this.dialogue[i + 1] != null) {
                            Story += this.dialogue[i + 1] + "\n";
                        }
                        if (this.dialogue[i + 2] != null) {
                            Story += this.dialogue[i + 2] + "\n";
                        }
                        if (this.dialogue[i + 3] != null) {
                            Story += this.dialogue[i + 3] + "\n";
                        }
                        st.setStory(Story);
                        index = i + 2;
                        break;
                    } else {
                        st.messageBox.putMessage(this.getName() + " (" + this.getSYMBOL() + "): " + talk);
                        index = i;
                    }
                }
                newDia = Arrays.copyOfRange(this.dialogue, index + 1, this.dialogue.length);
                st.NPCs.get(0).setDialogue(newDia);
                this.setContacting(true);
            }
            if (operations == 1) {
                for (int i = index; i < this.dialogue.length; i++) {
                    String talk = this.dialogue[i];
                    if (talk.substring(0, 3).equals("~Ar")) {
                        st.messageBox.putMessage(talk.substring(3));
                        this.setOperations(0);
                    } else if (talk.substring(0, 3).equals("~Br")) {
                        break;
                    }
                }
            }
            if (operations == 2) {
                for (int i = index; i < this.dialogue.length; i++) {
                    String talk = this.dialogue[i];
                    if (talk.substring(0, 3).equals("~Br")) {
                        st.messageBox.putMessage(talk.substring(3));
                        this.setOperations(0);
                    } else if (talk.substring(0, 3).equals("~Lr")) {
                        break;
                    }
                }
            }
            if (operations == 3) {
                for (int i = index; i < this.dialogue.length; i++) {
                    String talk = this.dialogue[i];
                    if (talk.substring(0, 3).equals("~Lr")) {
                        st.messageBox.putMessage(talk.substring(3));
                        this.setOperations(0);
                        st.messageBox.putMessage("you leave NPC "+this.getName());
                        st.setStory("go on your adventure");
                        this.setContacting(false);
                        break; // only display one line Lr
                    }
                }
            }
            return true;
            }else{
                return false;
            }
        }


}
