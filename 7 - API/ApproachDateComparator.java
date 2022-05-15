/**
 *  The <code>ApproachDateComparator</code> class implements the <code>Comparator</code> interface which allows the
 *  <code>NearEarthObjects</code> to be arranged in sorted order based on the value of <code>closestApproachDate</code>
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */
public class ApproachDateComparator implements java.util.Comparator<NearEarthObject>{
    /**
     * Compares two <code>NearEarthObjects</code> based on the value of <code>approachDate</code>
     * @param leftSide
     *  first <code>NearEarthObject</code> being compared
     * @param rightSide
     *  second <code>NearEarthObject</code> being compared
     * @return
     *  <p>1 if leftSide approachDate is after rightSide approachDate</p>
     *  <p>-1 if leftSide approachDate is before rightSide approachDate</p>
     *  <p>0 if the approachDates are equal</p>
     */
    @Override
    public int compare(NearEarthObject leftSide, NearEarthObject rightSide) {
        if (leftSide.getClosestApproachDate().before(rightSide.getClosestApproachDate()))
            return -1;
        else if (leftSide.getClosestApproachDate().after(rightSide.getClosestApproachDate()))
            return 1;
        else
            return 0;
    }
}
