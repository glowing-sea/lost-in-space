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

    int progress = 2; // where the dialogue is up to
    // the first two talks in the dialogue is saying goodbye

    String storyBackup = ""; // Backup the story box when first interact with the NPC

    public NPC(String name, Location loc, String[] dialogue) {
        super(name, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, loc, 'N');
        this.dialogue = dialogue;
    }

    public void setDialogue(String[] dialogue){this.dialogue = dialogue;}
    /**
     * The function tell what happens when the player interact with an NPC
     * When the player interact with a NPC, their conversation will be shown in the message box.
     * @param st the current game state
     * @return ture always
     */

    @Override
    public boolean interact(State st, int option) {

        // Start a conversation with this NPC
        if (st.interacting == null) {
            storyBackup = st.story; // backup the story box
            st.interacting = this;
        }
        // The user say goodbye to this NPC
        if (option == 3) {
            st.interacting = null;
            progress = 2; // Reset the talking progress
            st.messageBox.putMessage(st.player.getName() + " (" + st.player.getSYMBOL() + "): " + dialogue[0]); // NPC say goodbye to the player
            st.messageBox.putMessage(this.getName() + " (" + this.getSYMBOL() + "): " + dialogue[1]); // NPC say goodbye to the player
            st.messageBox.putMessage("System: you left NPC " + this.getName());
            st.story = this.storyBackup; // Reset the story box
            return true;
        }
        // Go through the dialogue
        while (progress < this.dialogue.length) {
            String talk = this.dialogue[progress];

            switch (talk.charAt(0)) {
                case '>' -> {
                    String option1 = dialogue[progress].substring(2);
                    String option2 = dialogue[progress + 1].substring(2);
                    st.story = "(A) " + option1 + "\n" + "(B) " + option2 + "\n" + "(G) " + this.dialogue[0];
                    progress += 2;
                    return true; // stop printing further dialogue because we need to get the user response.
                }
                case '<' -> {
                    String option1 = dialogue[progress - 2].substring(2);
                    String option2 = dialogue[progress - 1].substring(2);
                    String answer1 = dialogue[progress].substring(2);
                    String answer2 = dialogue[progress + 1].substring(2);
                    if (option == 1) {
                        st.messageBox.putMessage(st.player.getName() + " (" + st.player.getSYMBOL() + "): " + option1); // you talk
                        st.messageBox.putMessage(this.getName() + " (" + this.getSYMBOL() + "): " + answer1); // NPC talks
                    } else {
                        st.messageBox.putMessage(st.player.getName() + " (" + st.player.getSYMBOL() + "): " + option2); // you talk
                        st.messageBox.putMessage(this.getName() + " (" + this.getSYMBOL() + "): " + answer2); // NPC talks
                    }
                    st.story = "\n\n(G) " + this.dialogue[0];
                    progress += 2;
                }
                default -> {
                    if (talk.charAt(0) == '`') {
                        talk = talk.substring(2);
                        st.messageBox.putMessage(st.player.getName() + " (" + st.player.getSYMBOL() + "): " + talk); // the player talks
                    } else {
                        st.messageBox.putMessage(this.getName() + " (" + this.getSYMBOL() + "): " + talk); // NPC talks
                    }
                    st.story = "\n\n(G) " + this.dialogue[0];
                    progress += 1;
                }
            }
        }
        return true;
    }


//            else // Talk with options
//            {
//
//            }
//                st.messageBox.putMessage(this.getName() + " (" + this.getSYMBOL() + "): " + talk.substring(0,talk.length() - 2)); // -2 or -1?
//
//                if (this.dialogue[progress + 1] == null || this.dialogue[progress + 2] == null) {
//                    st.messageBox.putMessage("Exception: dialogue is not well-formed");
//                    st.messageBox.putMessage("talk ends with '0' must has two options");
//                    return true;
//                }
//                if (this.dialogue[progress + 1] != null) {
//                    st.messageBox.putMessage( this.dialogue[progress + 1]);
//                    st.messageBox.putMessage( this.dialogue[progress + 2]);
//                    progress += 3;
//                    return true;
//            }
//        }
//
//        int index = 0;
//        String Story = "";
//        this.setContacting(true);
//        if (operations == 0) {
//
//            for (int i = index; i < this.dialogue.length; i++) {
//                String talk = this.dialogue[i];
//
//                if (talk.charAt(talk.length() - 1) == 'O') { // Talked my the player
//                    //finish talking or player speaks;
//                    st.messageBox.putMessage(this.getName() + " (" + this.getSYMBOL() + "): " + talk.substring(0,talk.length()-2)); // -2 or -1?
//                    if (this.dialogue[i + 1] == null || this.dialogue[i + 2] == null) {
//                        st.messageBox.putMessage("no options set");
//                        return true;
//                    }
//                    if (this.dialogue[i + 1] != null) {
//                        Story += this.dialogue[i + 1] + "\n";
//                    }
//                    if (this.dialogue[i + 2] != null) {
//                        Story += this.dialogue[i + 2] + "\n";
//                    }
//                    if (this.dialogue[i + 3] != null) {
//                        Story += this.dialogue[i + 3] + "\n";
//                    }
//                    st.setStory(Story);
//                    index = i + 3;
//                    break;
//                } else {
//                    st.messageBox.putMessage(this.getName() + " (" + this.getSYMBOL() + "): " + talk);
//                    index = i+1;
//                }
//            }


//        }
//
//        if (operations == 1) {
//            for (int i = index; i < this.dialogue.length; i++) {
//                String talk = this.dialogue[i];
//                if (talk.substring(0, 3).equals("~Ar")) {
//                    st.messageBox.putMessage(talk.substring(3));
//                    this.setOperations(0);
//                } else if (talk.substring(0, 3).equals("~Br")) {
//                    break;
//                }
//            }
//        }
//        if (operations == 2) {
//            for (int i = index; i < this.dialogue.length; i++) {
//                String talk = this.dialogue[i];
//                if (talk.substring(0, 3).equals("~Br")) {
//                    st.messageBox.putMessage(talk.substring(3));
//                    this.setOperations(0);
//                } else if (talk.substring(0, 3).equals("~Lr")) {
//                    break;
//                }
//            }
//        }
//        if (operations == 3) {
//            for (int i = index; i < this.dialogue.length; i++) {
//                String talk = this.dialogue[i];
//                if (talk.substring(0, 3).equals("~Lr")) {
//                    st.messageBox.putMessage(talk.substring(3));
//                    this.setOperations(0);
//                    st.messageBox.putMessage("you leave NPC "+this.getName());
//                    st.setStory("go on your adventure");
//                    this.setContacting(false);
//                    break; // only display one line Lr
//                }
//            }
//        }
//        return true;

}
