package base;

import java.util.List;
/**
 * A character that has a list of trades.
 * Each trade is a pair of items (trade-in and trade-out)
 * If the player proceed an item same as the first element of a trade, the player can give out this item, and
 * in return, the player will obtain the item same as the second element of the trade
 */

public class Merchant extends Character{

    String dialogue;
    List<Trade> trades;

    public Merchant(String name, Location loc, String dialogue, List<Trade> trades) {
        super(name, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, loc, 'M');
        this.dialogue = dialogue;
        this.trades = trades;
    }
    
}
