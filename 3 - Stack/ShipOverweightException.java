/**
 * The <code>ShipOverweightException</code> class is a custom exception class thrown when
 * the user attempts to push a <code>Cargo</code> object onto any stack of the <code>CargoShip</code>
 * which would put it over it's maximum weight limit.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class ShipOverweightException extends Exception {
    /**
     * A custom exception method that extends the <code>Exception</code> class
     * @param message
     *  Custom message to be displayed for the EmptyStackException
     */
    public ShipOverweightException(String message) { super(message); }
}
