/**
 * The <code>EmptyStackException</code> class is a custom exception class thrown when
 * the user attempts to pop a <code>CargoStack</code> that currently has no <code>Cargo</code> objects on it.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class EmptyStackException extends Exception {
    /**
     * A custom exception method that extends the <code>Exception</code> class
     * @param message
     *  Custom message to be displayed for the EmptyStackException
     */
    public EmptyStackException(String message) { super(message); }
}
