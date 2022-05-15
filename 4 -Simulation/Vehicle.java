/**
 *  The <code>Vehicle</code> class represents a car which passes through the intersection.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class Vehicle {
    /**
     * incrementer to create serialID for a vehicle
     */
    private static int serialCounter = 0;
    /**
     * serial ID number for the vehicle
     */
    private int serialID;
    /**
     * arrival time for vehicle
     */
    private int timeArrived;
    /**
     * Default constructor
     * @param initTimeArrived
     *  time arrived for the vehicle
     * @throws IllegalArgumentException
     *  if <code>initTimeArrived</code> is less than or equal to 0
     */
    public Vehicle(int initTimeArrived) throws IllegalArgumentException {
        if (initTimeArrived<=0)
            throw new IllegalArgumentException();
        timeArrived = initTimeArrived;
        serialID = ++serialCounter;
    }

    /**
     * Getter method for serialID
     * @return
     *  the serial ID of the vehicle
     */
    public int getSerialID() {
        return serialID;
    }

    /**
     * Getter method for timeArrived
     * @return
     *  the arrival time of the vehicle
     */
    public int getTimeArrived() {
        return timeArrived;
    }

    /**
     * toString method for <code>Vehicle</code>
     * @return
     *  String representation for the vehicle based on its serialID
     */
    public String toString() { return String.format("[%03d]",serialID);}
}
