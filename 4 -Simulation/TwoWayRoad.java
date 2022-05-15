/**
 *  The <code>TwoWayRoad</code> class represents one of the roads in our intersection. Each road is bi-directional,
 *  with each direction having <STRONG>three</STRONG> lanes, a <code>LEFT_LANE</code>, a <code>MIDDLE_LANE</code>, and
 *  a <code>RIGHT_LANE</code>, modeled by <code>VehicleQueues</code>. Each lane can also travel <code>FORWARD</code> or
 *  <code>BACKWARD</code>, and these lanes are stored in a two-dimensional array.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class TwoWayRoad {
    /**
     * value representing the FORWARD road
     */
    public final int FORWARD_WAY = 0;
    /**
     * value representing the BACKWARD road
     */
    public final int BACKWARD_WAY = 1;
    /**
     * number of directions
     */
    public final int NUM_WAYS = 2;
    /**
     * value representing the LEFT lane
     */
    public final int LEFT_LANE = 0;
    /**
     * value representing the MIDDLE lane
     */
    public final int MIDDLE_LANE = 1;
    /**
     * value representing the RIGHT lane
     */
    public final int RIGHT_LANE = 2;
    /**
     * number of lanes
     */
    public final int NUM_LANES = 3;
    /**
     * name of the street
     */
    private String name;
    /**
     * The maximum total number of steps this road can be active
     */
    private int greenTime;

    /**
     * Getter method for <code>greenTime</code>
     * @return
     *  the value of greenTime
     */
    public int getGreenTime() { return greenTime; }

    /**
     * The number of steps this road remains in the <code>LEFT_SIGNAL</code> state.
     * Initialized to <code>1.0/NUM_LANES * greenTime</code> in the constructor.
     */
    private int leftSignalGreenTime;
    /**
     * Matrix of VehicleQueues to represent the lanes in <code>TwoWayRoad</code>
     */
    private VehicleQueue[][] lanes = new VehicleQueue[NUM_WAYS][NUM_LANES];
    /**
     * current traffic light state of the <code>TwoWayRoad</code>
     */
    private LightValue lightValue;

    /**
     * getter method for <code>lightValue</code>
     * @return
     *  current traffic state of the <code>TwoWayRoad</code>
     */
    public LightValue getLightValue() { return lightValue; }

    /**
     * Default constructor
     * @param initName
     *  Name of the street
     * @param initGreenTime
     *  The amount of time that the light will be active on the road.
     * @throws IllegalArgumentException
     *  if <code>initGreenTime</code> is less than or equal to 0 or <code>initName==null</code>
     */
    public TwoWayRoad(String initName, int initGreenTime) throws IllegalArgumentException {
        if (initGreenTime<=0||initName==null)
            throw new IllegalArgumentException("Illegal Argument");
        name = initName;
        greenTime = initGreenTime;
        leftSignalGreenTime = (int) (1.0/NUM_LANES * initGreenTime);
        for (int way=0; way<NUM_WAYS; way++)
            for (int lane=0; lane<NUM_LANES; lane++)
                lanes[way][lane] = new VehicleQueue();
    }

    /**
     * Executes the passage of time in the simulation.
     * @param timerVal
     *  the current value of a countdown timer counting down total green time steps
     * @return
     *  An array of vehicles that have been dequeued during this time step
     * @throws IllegalArgumentException
     *  if <code>timerVal</code> is less than or equal to 0
     */
    public Vehicle[] proceed(int timerVal) throws IllegalArgumentException {
        if (timerVal<=0)
            throw new IllegalArgumentException();
        Vehicle[] passed = new Vehicle[4];
        boolean empty=true;
        for (int way=0; way<NUM_WAYS; way++)
            for (int lane=MIDDLE_LANE; lane<NUM_LANES; lane++)
                if (!isEmpty(way,lane)) {
                    empty=false;
                    break;
                }
        lightValue = (timerVal>leftSignalGreenTime && !empty ? LightValue.GREEN : LightValue.LEFT_SIGNAL);
        int carCounter=0;
        for (int way = 0; way < NUM_WAYS; way++)
            for (int lane = 0; lane < NUM_LANES; lane++) {
                if (lightValue == LightValue.GREEN && !lanes[way][lane].isEmpty() && lane != LEFT_LANE)
                    passed[carCounter++] = lanes[way][lane].dequeue();
                if (lightValue == LightValue.LEFT_SIGNAL && !lanes[way][lane].isEmpty() && lane == LEFT_LANE)
                    passed[carCounter++] = lanes[way][lane].dequeue();

            }
        return passed;
    }

    /**
     * Enqueues a vehicle into the specified lane
     * @param wayIndex
     *  the direction the car is going in
     * @param laneIndex
     *  the lane the car arrives in
     * @param vehicle
     *  the vehicle to enqueue
     * @throws IllegalArgumentException
     *  if <code>wayIndex</code> is not within [0,<code>NUM_WAYS</code>),
     *  <code>laneIndex</code> is not within [0,<code>NUM_LANES</code>),
     *  or if <code>vehicle</code> is null.
     */
    public void enqueueVehicle(int wayIndex, int laneIndex, Vehicle vehicle) throws IllegalArgumentException {
        if (wayIndex>1||wayIndex<0||laneIndex<0||laneIndex>2||vehicle==null)
            throw new IllegalArgumentException();
        lanes[wayIndex][laneIndex].enqueue(vehicle);
    }

    /**
     * Checks if a specified lane is empty
     * @param wayIndex
     *  direction of the lane
     * @param laneIndex
     *  index of the lane to check
     * @return
     *  <code>true</code> if the lane is empty, else <code>false</code>
     * @throws IllegalArgumentException
     *  if <code>wayIndex</code> is not within [0,<code>NUM_WAYS</code>) or
     *  if <code>laneIndex</code> is not within [0,<code>NUM_LANES</code>)
     */
    public boolean isEmpty(int wayIndex, int laneIndex) throws IllegalArgumentException {
        if (wayIndex>1||wayIndex<0||laneIndex<0||laneIndex>2)
            throw new IllegalArgumentException();
        return lanes[wayIndex][laneIndex].isEmpty();
    }

    /**
     * Peek function for a lane on the road
     * @param wayIndex
     *  direction of the lane
     * @param laneIndex
     *  index of the lane
     * @return
     *  <code>VehicleQueue</code> representing the lane on the street
     */
    public VehicleQueue peek(int wayIndex, int laneIndex) { return lanes[wayIndex][laneIndex]; }

    /**
     * toString method for <code>TwoWayRoad</code>
     * @return
     *  String representation of the street
     */
    public String toString() { return name; }
}
