/**
 *  The <code>LightValue</code> class lists the phases a particular stoplight lane may be in.
 *  Each <code>TwoWayRoad</code> instance will have its own <code>LightValue</code>, which should correspond
 *  to the following rules.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public enum LightValue {
    /**
     * <code>GREEN</code> indicates that the right and middle lanes may proceed, but
     * the left lane cannot (for both directions).
     */
    GREEN {
        /**
         * toString for <code>GREEN</code>
         * @return
         *  String representation for GREEN LightValue
         */
        public String toString() { return "Green Light"; }
    },
    /**
     * <code>LEFT_SIGNAL</code> indicates that left can proceed, but
     * the right and middle lanes cannot (for both directions).
     */
    LEFT_SIGNAL {
        /**
         * toString for <code>LEFT_SIGNAL</code>
         * @return
         *  String representation for LEFT_SIGNAL LightValue
         */
        public String toString() { return "Left Signal"; }
    },
    /**
     * <code>RED</code> indicates that no lane may proceed (for both directions).
     */
    RED {
        /**
         * toString for <code>RED</code>
         * @return
         *  String representation for RED LightValue
         */
        public String toString() { return "Red Light"; }
    }
}
