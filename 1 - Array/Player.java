/**
 *  The <code>Player</code> class creates and stores <code>Player</code> objects
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class Player {
    /**
     * Player's name
     */
    private String name;

    /**
     * Number of Hits
     */
    private int numHits;

    /**
     * Number of Errors
     */
    private int numErrors;

    /**
     * Constructs an instance of the Player class
     */
    public Player() {}

    /**
     * Accessor method for name
     *
     * @return
     *  Returns the name of the player
     */
    public String getName() { return this.name; }

    /**
     * Accessor method for numHits
     *
     * @return
     *  Returns the number of hits of the player
     */
    public int getNumHits() { return this.numHits; }

    /**
     * Accessor method for numErrors
     *
     * @return
     *  Returns the number of errors of the player
     */
    public int getNumErrors() { return this.numErrors; }

    /**
     * Mutator method for name
     *
     * @param name
     *  Updated name of the player
     */
    public void setName(String name) { this.name = name; }

    /**
     * Mutator method for numHits
     *
     * @param numHits
     *  Updated number of hits of the player
     *
     * @throws IllegalArgumentException
     *  Indicates that the number of hits is less than 0.
     */
    public void setNumHits(int numHits) throws IllegalArgumentException {
        if (numHits < 0)
            throw new IllegalArgumentException("Number of Hits cannot be less than 0.");
        this.numHits = numHits;
    }

    /**
     *  Mutator method for numErrors
     *
     * @param numErrors
     *  Updated number of errors of the player
     *
     * @throws IllegalArgumentException
     *  Indicates that the number of hits is less than 0.
     */
    public void setNumErrors(int numErrors) throws IllegalArgumentException {
        if (numErrors < 0)
            throw new IllegalArgumentException("Number of Errors cannot be less than 0.");
        this.numErrors = numErrors;
    }

    /**
     * Generates a clone of this Player
     *
     * @return
     *  Returns a clone of this Player. Subsequent changes to the clone will not affect the original, nor vice versa.
     */
    public Object clone() {
        Player person = new Player();
        person.setName(name);
        person.setNumHits(numHits);
        person.setNumErrors(numErrors);
        return person;
    }

    /**
     * Compares this Player to another object for equality
     *
     * @param obj
     *  An Object to which this Player is compared
     *
     * @return
     *  A return value of true indicates that obj refers to a Player object with the same name, numHits, and numErrors.
     *  The return value is false otherwise or if the obj is not a Player object.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Player) {
            Player p = (Player) obj;
            return name.equals(p.name) && numHits == p.numHits && numErrors == p.numErrors;
        }
        return false;
    }

    /**
     * Returns a string representation of the Player
     *
     * @return
     *  A string in the format [Name] - [numHits] hits, [numErrors] errors
     */
    public String toString() { return this.name+" - "+this.numHits+" hits, "+this.numErrors+" errors"; }
}
