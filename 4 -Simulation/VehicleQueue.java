import java.util.LinkedList;

/**
 *  The <code>VehicleQueue</code> class extends <code>java.util.LinkedList</code> to implement a queue for the lanes
 *  of traffic.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class VehicleQueue extends LinkedList<Vehicle> {
    /**
     * Enqueues a vehicle to the lane
     * @param v
     *  Vehicle to be queued into the lane
     */
    public void enqueue(Vehicle v) { add(v); }

    /**
     * Dequeues a vehicle from the lane
     * @return
     *  Head Vehicle object in the queue to be dequeued
     */
    public Vehicle dequeue() { return remove(); }
}
