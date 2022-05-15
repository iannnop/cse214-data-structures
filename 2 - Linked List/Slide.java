/**
 *  The <code>Slide</code> class creates and stores <code>Slide</code> objects
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class Slide {

    /**
     * Maximum number of bullet points
     */
    private static final int MAX_BULLETS = 5;

    /**
     * Title of the Slide
     */
    private String title;

    /**
     * Array of bullet points
     */
    private String[] bullets;

    /**
     * Duration of the slide in minutes
     */
    private double duration;

    /**
     * Default constructor
     *
     * <p>Postconditions:</p>
     * <p>This object has been initialized to an empty slide with title and bullets null, duration 0.0</p>
     */
    public Slide() {
        title = null;
        bullets = new String[MAX_BULLETS];
        duration = 0.0;
    }

    /**
     * Getter method for the <code>title</code> variable
     *
     * @return
     *  Returns the title of the <code>Slide</code>
     */
    public String getTitle() { return title; }

    /**
     * Setter method for the <code>title</code> variable
     *
     * <p>Preconditions:</p>
     * <p><code>newTitle</code> is not <code>null</code></p>
     *
     * @param newTitle
     *  The new title of the slide (should not be <code>null</code>)
     *
     * @throws IllegalArgumentException
     *  Thrown if <code>newTitle</code> is <code>null</code>
     */
    public void setTitle(String newTitle) throws IllegalArgumentException {
        if (newTitle == null)
            throw new IllegalArgumentException("Title cannot be null");
        this.title = newTitle;
    }

    /**
     * Getter method for the <code>duration</code> variable
     *
     * @return
     *  Returns the duration of the <code>Slide</code>
     */
    public double getDuration() { return duration; }

    /**
     * Setter method for the <code>duration</code> variable
     * <p>Preconditions:</p>
     * <p><code>newDuration</code> is greater than 0</p>
     *
     * @param newDuration
     *  The new duration of the slide (parameter must be greater than 0)
     *
     * @throws IllegalArgumentException
     *  Thrown if <code>newDuration</code> is less than or equal to 0
     */
    public void setDuration(double newDuration) throws IllegalArgumentException {
        if (newDuration <= 0)
            throw new IllegalArgumentException("Duration must be positive");
        duration = newDuration;
    }

    /**
     * Method that deletes the <code>duration</code> of the slide
     */
    public void deleteDuration() { duration = -1; }

    /**
     * Gets the total number of bullet points in the <code>Slide</code>
     *
     * @return
     *  Returns the number of bullet points in the <code>Slide</code>
     */
    public int getNumBullets() {
        int count = 0;
        for (int i=0; i<MAX_BULLETS; i++) {
            if (bullets[i] == null)
                break;
            count++;
        }
        return count;
    }

    /**
     * Gets the bullet point at index <code>i</code>
     * <p>Preconditions:</p>
     * <p>i must be between 1 and <code>MAX_BULLETS</code>, inclusive</p>
     *
     * @param i
     *  The index to retrieve from the array. <code>i</code> must be between 1 and <code>MAX_BULLETS</code>, inclusive
     *
     * @return
     *  Returns the <code>String</code> representing the bullet point at the given index (may be <code>null</code>,
     *    meaning that there is no bullet point at this index)
     *
     * @throws IllegalArgumentException
     *  Thrown if <code>i</code> is not in the valid range
     */
    public String getBullet(int i) throws IllegalArgumentException {
        if (i<1 || i>MAX_BULLETS)
            throw new IllegalArgumentException("Index not within the valid range.");
        return bullets[i-1];
    }

    /**
     * Sets <code>newBullet</code> as the <code>i</code>'th bullet point for <code>bullets</code>.
     * If <code>newBullet</code> is <code>null</code>, this method erases the bullet point at index <code>i</code>
     * and pushes all bullet points greater than <code>i</code> back one index.
     * <p>Preconditions:</p>
     * <p>i must be between 1 and <code>MAX_BULLETS</code>, inclusive</p>
     * <p>Postconditions:</p>
     * <p>The bullet point at <code>i</code> has been updated to the <code>String</code> <code>newBullet</code></p>
     * <p>There are no holes in the <code>bullets</code> array</p>
     *
     * @param newBullet
     * The <code>String</code> to place in the <code>i</code>th bullet point in <code>bullets</code>
     * This parameter may be <code>null</code>, which erases the bullet from the <code>Slide</code>
     *
     * @param i
     * The index to place <code>newBullet</code> into the array.
     * Must be between 1 and <code>MAX_BULLETS</code>, inclusive
     *
     * @throws IllegalArgumentException
     *  Thrown if <code>i</code> is not within the valid range
     */
    public void setBullet(String newBullet, int i) throws IllegalArgumentException {
        if (i<1 || i>MAX_BULLETS)
            throw new IllegalArgumentException("Index not within the valid range");
        if (newBullet == null) {
            if (i == MAX_BULLETS) {
                bullets[i-1] = null;
            }
            else {
                for (int j=i; j<getNumBullets()+1; j++)
                    bullets[j-1] = bullets[j];
            }
        }
        else {
            bullets[i-1] = newBullet;
        }
    }

    /**
     * Gets the String representation for the <code>Slide</code> object
     *
     * @return
     *  Returns the String representation of the <code>Slide</code> and its data members
     */
    public String toString() {
        String summary = String.format("%s%n  %s%n%s%n","=".repeat(46),title,"=".repeat(46));
        for (int i=0; i<getNumBullets(); i++)
            summary+=String.format(" %d. %s%n",i+1,bullets[i]);
        summary+= String.format("%s%n  Duration: %.1f minute(s)%n%s","=".repeat(46),
          (duration==-1 ? null: duration),"=".repeat(46));
        return summary;
    }
}
