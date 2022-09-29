package base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * A character that has a list of trades.
 * Each trade is a pair of items (trade-in and trade-out)
 * If the player proceed an item same as the first element of a trade, the player can give out this item, and
 * in return, the player will obtain the item same as the second element of the trade
 */

public class Merchant extends Character{

    private final String[] dialogue; // length 3 only
    private List<Trade> trades; // up to 6 deals

    private String storyBackup = ""; // Backup the story box when first interact with the NPC

    public String[] getDialogue() {
        return dialogue;
    }
    public List<Trade> getTrades() {
        return trades;
    }

    public Merchant(String name, Location loc, String[] dialogue, List<Trade> trades) {
        super(name, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, loc, 'M');
        // Default dialogue and trade
        if (dialogue == null)
            dialogue = GameConfiguration.DEFAULT_NPC_DIALOGUE;
        if (trades == null)
            trades = GameConfiguration.DEFAULT_TRADES;
        // Check format
        if (!isDialogueWellForm(dialogue))
            throw new IllegalArgumentException("The dialogue of this merchant is not well-formed.");
        if (!isTradesWellForm(trades))
            throw new IllegalArgumentException("The trades of this merchant is not well-formed.");
        this.dialogue = dialogue;
        this.trades = trades;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Merchant m))
            return false;
        return Arrays.equals(this.dialogue, m.dialogue) &&
                this.trades.equals(m.trades) &&
                super.equals(m);
    }

    public boolean isDialogueWellForm (String[] dialogue){
        return dialogue.length == 3;
    }

    public boolean isTradesWellForm (List<Trade> trades){
        return trades.size() <= 6;
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
            st.messageBox.putMessage(this.getName() + " (" + this.getSYMBOL() + "): " + dialogue[2]); // NPC talks
            st.story = "\n\n(G) " + this.dialogue[0];
        }
        // The user say goodbye to this NPC
        if (option == 3) {
            st.interacting = null;
            st.messageBox.putMessage(st.player.getName() + " (" + st.player.getSYMBOL() + "): " + dialogue[0]);
            st.messageBox.putMessage(this.getName() + " (" + this.getSYMBOL() + "): " + dialogue[1]);
            st.messageBox.putMessage("System: you left the merchant " + this.getName());
            st.story = this.storyBackup; // Reset the story box
            return true;
        }
        // Print out the deals
        st.messageBox.putMessage("=== Here are the deals ===");
        for (int i = 0; i < trades.size() && i < 6; i++){
            st.messageBox.putMessage((i + 1) + ": " + "[ " + trades.get(i).getTRADE_IN() + " ] -> [ " + trades.get(i).getTRADE_OUT() + " ]");
        }
        // The user press A or B or interact with the merchant for the first time.
        if (option >= 0 && option <=2){
            return true;
        }
        int tradeSelected = option - 4;

        Item playerWantToSell = this.trades.get(tradeSelected).getTRADE_IN();
        Item playerWillGet = this.trades.get(tradeSelected).getTRADE_OUT();
        System.out.println("sell" + playerWantToSell);
        for (Item i : st.player.getInventory()){
            System.out.println(i);
        }
        if (st.player.getInventory().contains(playerWantToSell)){
            st.player.getInventory().remove(playerWantToSell);
            st.player.getInventory().add(playerWillGet);
            st.messageBox.putMessage("You sold [ " + playerWantToSell + " ] and got [ " + playerWillGet + " ].");
        } else {
            st.messageBox.putMessage("You don't have the item to sell.");

        }

        return true;
    }
}
