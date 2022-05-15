/**
 * The <code>NodeNotPresentException</code> class is a custom exception class thrown when
 * the user attempts to select a <code>StoryTreeNode</code>
 * with a position variable that cannot be found in the <code>StoryTree</code>.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class NodeNotPresentException extends Exception {
    /**
     * A custom exception method that extends the <code>Exception</code> class
     * @param message
     *  Custom message to be displayed for the NodeNotPresentException
     */
    public NodeNotPresentException(String message) { super(message); }
}
