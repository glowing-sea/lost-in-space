package base;

/**
 * An object that store the item the seller accepts and the item the seller will give out.
 *  * @Albert Yu: FIXME this class is not required:
 *  * 1. Design is not so great.
 *  * 2. Also, when implementing load function, these fixed final constants cause a run-time error.
 *
 * UPDATE: Above comments done by Albert, when investigating bug.
 *         Haoting Chen has updated this class (see code below). It functions better than before, and the bug no longer
 *         manifests.
 */

public class Trade {
    private final Item TRADE_IN;
    private final Item TRADE_OUT;
    public Trade(Item in, Item out) {
        this.TRADE_IN = in;
        this.TRADE_OUT = out;
    }

    public Item getTRADE_OUT() {
        return TRADE_OUT;
    }

    public Item getTRADE_IN() {
        return TRADE_IN;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Trade t))
            return false;
        return this.TRADE_OUT.equals(t.TRADE_OUT) &&
                this.TRADE_IN.equals(t.TRADE_IN);
    }
}