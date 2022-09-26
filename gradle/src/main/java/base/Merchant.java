package base;

import java.util.List;

public class Merchant extends Character{

    String dialogue;
    List<Trade> trades;

    public Merchant(String name, Location loc, String dialogue, List<Trade> trades) {
        super(name, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, loc, 'M');
        this.dialogue = dialogue;
        this.trades = trades;
    }
}
