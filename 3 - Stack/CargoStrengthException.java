/**
 * The <code>CargoStrengthException</code> class is a custom exception class thrown when
 * the user attempts to push a <code>Cargo</code> object onto another <code>Cargo</code> object that
 * violates the <code>CargoStrength</code> rules indicated above.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class CargoStrengthException extends Exception {
    /**
     * A custom exception method that extends the <code>Exception</code> class
     * @param message
     *  Custom message to be displayed for the EmptyStackException
     */
    public CargoStrengthException(String message) { super(message); }
}
