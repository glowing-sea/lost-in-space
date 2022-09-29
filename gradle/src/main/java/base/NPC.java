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
    int operations = 0; // this is what user option is
    // FIXME: changed to public for the time being (Albert Yu)
    // FIXME: for the purposes of StateLoadTests.java
    // FIXME: previously, had no modifier.
    public Boolean contacting = false;


    public NPC(String name, Location loc, String[] dialogue) {
        super(name, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, loc, 'N');
        this.dialogue = dialogue;
    }
    public void setOperations(int operations) {
        this.operations = operations;
    }
    public void setDialogue(String[] dialogue){this.dialogue = dialogue;}
    public void setContacting(Boolean b){this.contacting = b;}
    /**
     * The function tell what happens when the player interact with an NPC
     * When the player interact with a NPC, their conversation will be shown in the message box.
     * @param st the current game state
     * @return ture always
     */

    @Override
    public boolean interact(State st) {
        int index = 0;
        String Story = "";

        if (this.nearby(st.player.getLoc())) {
            this.setContacting(true);
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
                        index = i + 3;
                        break;
                    } else {
                        st.messageBox.putMessage(this.getName() + " (" + this.getSYMBOL() + "): " + talk);
                        index = i+1;
                    }
                }


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
