package utility;
/**
 * @author Haoting Chen
 *
 * This class contains utility methods for display a game state
 *
 */


public class DisplayUtility {

    /**
     * @author Haoting Chen
     * This function adjust a string to a specific length by adding more space or remove exceeding characters.
     * @param str a string
     * @param newLength the length of the output string
     * @return a string with a specific length
     */
    public static String fixLength (String str, int newLength){
        int oldLength = str.length();
        if (oldLength < newLength) {
            str = str + " ".repeat(newLength - oldLength);}
        else if (oldLength > newLength) {
            str = str.substring(0,newLength);
        }
        return str;
    }

    /**
     * @author Haoting Chen
     * display text in middle of text block
     * @param text text
     * @param length length of text
     * @return text with appropriate space in front of it and after it
     */
    public static String centerText (String text, int length){
        int textLength = text.length();
        if (textLength >= length)
            return fixLength(text, length);
        int spaces = length - textLength;
        int rightSpaces = (length - textLength) / 2;
        int leftSpaces = spaces - rightSpaces;
        return " ".repeat(rightSpaces) + text + " ".repeat(leftSpaces);
    }

    // Tests
    public static void main(String[] args) {
        System.out.println("|" + centerText("Jack", 10) + "|");
    }
}
