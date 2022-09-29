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
    private final String[] dialogue;

    private int progress = 2; // where the dialogue is up to
    // the first two talks in the dialogue is saying goodbye

    private String storyBackup = ""; // Backup the story box when first interact with the NPC

    public String[] getDialogue() {
        return dialogue;
    }

    public NPC(String name, Location loc, String[] dialogue) {
        super(name, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, loc, 'N');

        // Set default
        if (dialogue == null) dialogue = GameConfiguration.DEFAULT_NPC_DIALOGUE;
        this.dialogue = dialogue;

        if (!isDialogueWellForm(this.dialogue)){
            throw new IllegalArgumentException("The dialogue of this NPC written in GameConfiguration is not well-form.");
        }
    }

    public static boolean isDialogueWellForm (String[] dialogue){
        if (dialogue.length < 3)
            return false;
        int i = 2;
        while (i <dialogue.length){
            if (dialogue[i].charAt(0) == '>'){
                if (i + 3 >= dialogue.length // Too short
                        || dialogue[i + 1].charAt(0) != '>'
                        || dialogue[i + 2].charAt(0) != '<'
                        || dialogue[i + 3].charAt(0) != '<'){
                    return false;
                }
                i += 4;
            } else {
                i++;
            }
        }
        return true;
    }

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
                case '>' -> { // The player can choose one of the two sentences to talk to the NPC
                    String option1 = dialogue[progress].substring(2);
                    String option2 = dialogue[progress + 1].substring(2);
                    st.story = "(A) " + option1 + "\n" + "(B) " + option2 + "\n" + "(G) " + this.dialogue[0];
                    progress += 2;
                    return true; // stop printing further dialogue because we need to get the user response.
                }
                case '<' -> { // Print the NPC answer according to what the player say previously
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
                default -> { // Print this talk directly
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
}
