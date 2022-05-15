/**
 *  The <code>CargoStrength</code> <code>Enum</code> lists the strength values a cargo container may have.
 *  <p>The rules for these <code>CargoStrength</code> types are described as follows:</p>
 *  <p><code>FRAGILE</code> containers can only support other <code>FRAGILE</code> containers</p>
 *  <p><code>MODERATE</code> containers can only support other <code>MODERATE</code> containers, as well as
 *  other <code>FRAGILE</code> containers</p>
 *  <p><code>STURDY</code> containers can support all other containers</p>
 *  <p>The number of containers stacked above any other container does not matter. The only restrictions are the
 *  three rules above</p>
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public enum CargoStrength {
    /**
     * <code>FRAGILE</code> containers can only support other <code>FRAGILE</code> containers
     */
    FRAGILE,
    /**
     * <code>MODERATE</code> containers can only support other <code>MODERATE</code> containers, as well as other
     * <code>FRAGILE</code> containers
     */
    MODERATE,
    /**
     * <code>STURDY</code> containers can support all other containers
     */
    STURDY
}
