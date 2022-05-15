/**
 *  The <code>Cargo</code> class represents a container on the ship
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class Cargo {
    /**
     * The name of the cargo
     */
    private String name;

    /**
     * The weight of the cargo
     */
    private double weight;

    /**
     * The CargoStrength value of the cargo
     */
    private CargoStrength strength;

    /**
     * Default Constructor
     * <p>Preconditions:</p>
     * <p><code>initName</code> is not <code>null</code></p>
     * <p><code>initWeight</code> is greater than 0</p>
     * <p>Postconditions:</p>
     * <p>This object has been initialized to a <code>Cargo</code> object with the given <code>name</code>,
     * <code>weight</code>, and <code>strength</code></p>
     *
     * @param initName
     *  Non-null name for the cargo item
     *
     * @param initWeight
     *  The weight of the cargo. Must be greater than 0.
     *
     * @param initStrength
     *  Either <code>FRAGILE</code>, <code>MODERATE</code>, or <code>STURDY</code>.
     */
    public Cargo(String initName, double initWeight, CargoStrength initStrength) {
        name = initName;
        weight = initWeight;
        strength = initStrength;
    }

    /**
     * Getter method for <code>name</code>
     *
     * @return
     *  Returns the String representation of the <code>name</code>
     */
    public String getName() { return name; }

    /**
     * Getter method for <code>weight</code>
     *
     * @return
     *  Returns the <code>weight</code> of the cargo
     */
    public double getWeight() { return weight; }

    /**
     * Getter method for <code>strength</code>
     *
     * @return
     *  Returns the <code>strength</code> of the cargo
     */
    public CargoStrength getStrength() { return strength; }

    /**
     * Clone method for Cargo
     *
     * @return
     *  Returns a newly cloned object of this <code>Cargo</code>
     */
    public Object clone() { return new Cargo(name,weight,strength); }

    /**
     * toString method for Cargo based on the CargoStrength of the container
     * @return
     *  Returns a string representation of the cargo based on this cargo's <code>CargoStrength</code>
     */
    public String toString() {
        return strength.equals(CargoStrength.FRAGILE)?"F":strength.equals(CargoStrength.MODERATE)?"M":"S";
    }
}
