import java.util.Date;

/**
 *  The <code>NearEarthObject</code> class represents a record in the database of asteroids currently tracked by NASA
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */
public class NearEarthObject {
    /**
     * Unique ID
     */
    private int referenceID;
    /**
     * Unique name
     */
    private String name;
    /**
     * Absolute brightness of the asteroid or orbital body in the sky
     */
    private double absoluteMagnitude;
    /**
     * Estimated diameter of the asteroid or orbital body in kilometers
     */
    private double averageDiameter;
    /**
     * Boolean value indicating whether the asteroid or orbital body is a potential impact threat
     */
    private boolean isDangerous;
    /**
     * the date of closest approach
     */
    private Date closestApproachDate;
    /**
     * Distance in kilometers at which the asteroid or orbital body will pass by the Earth on the date of its closest
     * approach
     */
    private double missDistance;
    /**
     * Planet or other orbiting body which this <code>NearEarthObject</code> orbits
     */
    private String orbitingBody;

    /**
     * Default constructor
     * @param referenceID
     *  Unique ID
     * @param name
     *  Unique name
     * @param absoluteMagnitude
     *  Absolute brightness of the asteroid or orbital body in the sky
     * @param minDiameter
     *  Estimated minimum diameter of the asteroid or orbital body in kilometers
     * @param maxDiameter
     *  Estimated maximum diameter of the asteroid or orbital body in kilometers
     * @param isDangerous
     *  Boolean value indicating whether the asteroid or orbital body is a potential impact threat
     * @param closestDateTimestamp
     *  Unix timestamp representing the date of closest approach
     * @param missDistance
     *  Distance in kilometers at which the asteroid or orbital body will pass by the Earth on the date of its closest
     *  approach
     * @param orbitingBody
     *  Planet or other orbital body which this NEO orbits
     */
    public NearEarthObject(int referenceID, String name, double absoluteMagnitude, double minDiameter,
      double maxDiameter, boolean isDangerous, long closestDateTimestamp, double missDistance, String orbitingBody) {
        this.referenceID = referenceID;
        this.name = name;
        this.absoluteMagnitude = absoluteMagnitude;
        this.averageDiameter = (minDiameter+maxDiameter)/2.0;
        this.isDangerous = isDangerous;
        this.closestApproachDate = new Date(closestDateTimestamp);
        this.missDistance = missDistance;
        this.orbitingBody = orbitingBody;
    }

    /**
     * Getter method for <code>referenceID</code>
     * @return
     *  Unique ID for the <code>NearEarthObject</code>
     */
    public int getReferenceID() { return referenceID; }

    /**
     * Setter method for <code>referenceID</code>
     * @param referenceID
     *  new unique ID for the <code>NearEarthObject</code>
     */
    public void setReferenceID(int referenceID) { this.referenceID = referenceID; }

    /**
     * Getter method for <code>name</code>
     * @return
     *  Unique name for the <code>NearEarthObject</code>
     */
    public String getName() { return name; }

    /**
     * Setter method for <code>name</code>
     * @param name
     *  new unique name for the <code>NearEarthObject</code>
     */
    public void setName(String name) { this.name = name; }

    /**
     * Getter method for <code>absoluteMagnitude</code>
     * @return
     *  Absolute brightness for the <code>NearEarthObject</code>
     */
    public double getAbsoluteMagnitude() { return absoluteMagnitude; }

    /**
     * Setter method for <code>absoluteMagnitude</code>
     * @param absoluteMagnitude
     *  new absolute brightness for the <code>NearEarthObject</code>
     */
    public void setAbsoluteMagnitude(double absoluteMagnitude) { this.absoluteMagnitude = absoluteMagnitude; }

    /**
     * Getter method for <code>averageDiameter</code>
     * @return
     *  Estimated diameter of the <code>NearEarthObject</code> in kilometers
     */
    public double getAverageDiameter() { return averageDiameter; }

    /**
     * Setter method for <code>averageDiameter</code>
     * @param averageDiameter
     *  new estimated diameter of the <code>NearEarthObject</code> in kilometers
     */
    public void setAverageDiameter(double averageDiameter) { this.averageDiameter = averageDiameter; }

    /**
     * Getter method for <code>isDangerous</code>
     * @return
     *  Boolean value indicating whether the <code>NearEarthObject</code> is a potential impact threat
     */
    public boolean getDangerous() { return isDangerous; }

    /**
     * Setter method for <code>isDangerous</code>
     * @param isDangerous
     *  new boolean value indicating whether the <code>NearEarthObject</code> is a potential impact threat
     */
    public void setDangerous(boolean isDangerous) { this.isDangerous = isDangerous; }

    /**
     * Getter method for <code>closestApproachDate</code>
     * @return
     *  the date of closest approach
     */
    public Date getClosestApproachDate() { return closestApproachDate; }

    /**
     * Setter method for <code>closestApproachDate</code>
     * @param closestApproachDate
     *  new date of closest approach
     */
    public void setClosestApproachDate(Date closestApproachDate) { this.closestApproachDate = closestApproachDate; }

    /**
     * Getter method for <code>missDistance</code>
     * @return
     *  Distance in kilometers at which the <code>NearEarthObject</code> will pass by the Earth on
     *  the date of its closest approach
     */
    public double getMissDistance() { return missDistance; }

    /**
     * Setter method for <code>missDistance</code>
     * @param missDistance
     *  new distance in kilometers at which the <code>NearEarthObject</code> will pass by the Earth on
     *  the date of its closest approach
     */
    public void setMissDistance(double missDistance) { this.missDistance = missDistance; }

    /**
     * Getter method for <code>orbitingBody</code>
     * @return
     *  Planet or other orbiting body which this <code>NearEarthObject</code> orbits
     */
    public String getOrbitingBody() { return orbitingBody; }

    /**
     * Setter method for <code>orbitingBody</code>
     * @param orbitingBody
     *  new planet or other orbiting body which this <code>NearEarthObject</code> orbits
     */
    public void setOrbitingBody(String orbitingBody) { this.orbitingBody = orbitingBody; }
}
