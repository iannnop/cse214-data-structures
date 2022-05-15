/**
 * The <code>EndOfListException</code> class is a custom exception class which indicates
 * that the user attempted to access a <code>SlideListNode</code> that does not exist
 * (either before the head node or after the tail node).
 * <p>This exception can also be thrown when an operation is performed on an empty list.</p>
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class EndOfListException extends Exception {

    /**
     * A custom exception method that extends the Exception class
     *
     * @param message
     *  Displays a custom message for the EndOfListException
     */
    public EndOfListException(String message) { super(message); }
}
