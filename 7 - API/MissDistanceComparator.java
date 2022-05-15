/**
 *  The <code>MissDistanceComparator</code> class implements the <code>Comparator</code> interface which allows the
 *  <code>NearEarthObjects</code> to be arranged in sorted order based on the value of <code>missDistance</code>
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */
public class MissDistanceComparator implements java.util.Comparator<NearEarthObject>{
    /**
     * Compares two <code>NearEarthObjects</code> based on the value of <code>missDistance</code>
     * @param leftSide
     *  first <code>NearEarthObject</code> being compared
     * @param rightSide
     *  second <code>NearEarthObject</code> being compared
     * @return
     *  <p>1 if leftSide missDistance is greater than rightSide missDistance</p>
     *  <p>-1 if leftSide missDistance is less than rightSide missDistance</p>
     *  <p>0 if the missDistances are equal</p>
     */
    @Override
    public int compare(NearEarthObject leftSide, NearEarthObject rightSide) {
        if (leftSide.getMissDistance()-rightSide.getMissDistance() < 0)
            return -1;
        else if (leftSide.getMissDistance()-rightSide.getMissDistance() > 0)
            return 1;
        else
            return 0;
    }
}
