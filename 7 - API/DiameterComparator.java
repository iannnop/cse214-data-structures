/**
 *  The <code>DiameterComparator</code> class implements the <code>Comparator</code> interface which allows the
 *  <code>NearEarthObjects</code> to be arranged in sorted order based on the value of <code>averageDiameter</code>
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */
public class DiameterComparator implements java.util.Comparator<NearEarthObject> {
    /**
     * Compares two <code>NearEarthObjects</code> based on the value of <code>averageDiameter</code>
     * @param leftSide
     *  first <code>NearEarthObject</code> being compared
     * @param rightSide
     *  second <code>NearEarthObject</code> being compared
     * @return
     *  <p>1 if leftSide averageDiameter is greater than rightSide averageDiameter</p>
     *  <p>-1 if leftSide averageDiameter is less than rightSide averageDiameter</p>
     *  <p>0 if the averageDiameters are equal</p>
     */
    @Override
    public int compare(NearEarthObject leftSide, NearEarthObject rightSide) {
        if (leftSide.getAverageDiameter()-rightSide.getAverageDiameter() < 0)
            return -1;
        else if (leftSide.getAverageDiameter()-rightSide.getAverageDiameter() > 0)
            return 1;
        else
            return 0;
    }
}
