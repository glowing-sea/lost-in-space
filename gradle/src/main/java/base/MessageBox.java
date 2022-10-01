package base;

import java.util.LinkedList;

/**
 * A linked list with a maximum length of 10.
 * When an element is added and the length exceeds 10. The oldest element will be removed.
 */
public class MessageBox extends LinkedList<String> {

    final int SIZE = 10;

    /**
     * @author Haoting chen
     * put message in message box
     * @param message
     */
    public void putMessage (String message){
        if (this.size() <= SIZE - 1){
            addLast(message);
        } else {
            removeFirst();
            addLast(message);
        }
    }

}
