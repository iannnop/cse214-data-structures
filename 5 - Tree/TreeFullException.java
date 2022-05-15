/**
 * The <code>TreeFullException</code> class is a custom exception class thrown when
 * all three child spots in a <code>StoryTree</code> is full.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class TreeFullException extends Exception {
    /**
     * A custom exception method that extends the <code>Exception</code> class
     * @param message
     *  Custom message to be displayed for the TreeFullException
     */
    public TreeFullException(String message) { super(message); }
}
