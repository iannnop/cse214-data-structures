/**
 *  The <code>ReferenceIDComparator</code> class implements the <code>Comparator</code> interface which allows the
 *  <code>NearEarthObjects</code> to be arranged in sorted order based on the value of <code>referenceID</code>
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */
public class ReferenceIDComparator implements java.util.Comparator<NearEarthObject> {
    /**
     * Compares two <code>NearEarthObjects</code> based on the value of <code>referenceID</code>
     * @param leftSide
     *  first <code>NearEarthObject</code> being compared
     * @param rightSide
     *  second <code>NearEarthObject</code> being compared
     * @return
     *  <p>1 if leftSide referenceID is greater than rightSide referenceID</p>
     *  <p>-1 if leftSide referenceID is less than rightSide referenceID</p>
     *  <p>0 if the referenceIDs are equal</p>
     */
    @Override
    public int compare(NearEarthObject leftSide, NearEarthObject rightSide) {
        return Integer.compare(leftSide.getReferenceID()-rightSide.getReferenceID(),0);
    }
}
