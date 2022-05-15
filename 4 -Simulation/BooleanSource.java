/**
 *  The <code>BooleanSource</code> class abstracts a random occurrence generator.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class BooleanSource {
    /**
     * double variable for the probability value of the event
     */
    private double probability;

    /**
     * Getter method for <code>probability</code>
     * @return
     *  probability for BooleanSource
     */
    public double getProbability() { return probability; }

    /**
     * Setter method for <code>probability</code>
     * @param probability
     *  new probability value for <code>probability</code>
     */
    public void setProbability(double probability) { this.probability = probability; }

    /**
     * Constructor which initializes the <code>probability</code> to the indicated parameter
     * <p>Preconditions:</p>
     * <p><code>probability</code> is within (0,1]</p>
     * @param initProbability
     *  Probability used to construct this <code>BooleanSource</code> object. Should be within (0,1]
     * @throws IllegalArgumentException
     *  If <code>initProbability</code> is less than or equal to 0 or greater than 1
     */
    public BooleanSource(double initProbability) throws IllegalArgumentException {
        if (initProbability <= 0.0 || initProbability > 1.0)
            throw new IllegalArgumentException();
        probability = initProbability;
    }

    /**
     * Method which returns <code>true</code> with the probability indicated by
     * the member variable <code>probability</code>
     * <p>Preconditions:</p>
     * <p><code>probability</code> is a valid probability (0,1]</p>
     * @return
     *  Boolean value indicating whether an event occurs or not
     */
    public boolean occurs() { return (Math.random() < probability); }
}
