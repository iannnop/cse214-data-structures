/**
 *  The <code>Intersection</code> class represents the crossing of two or more <code>TwoWayRoads</code> at a stoplight
 *  in our simulation.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class Intersection {
    /**
     * Array of roads which cross at this intersection
     */
    private TwoWayRoad[] roads;
    /**
     * road that is currently at the active light
     */
    private TwoWayRoad road;

    /**
     * Method to access the number of roads in the intersection
     * @return
     *  the number of roads at the intersection
     */
    public int getNumRoads() { return roads.length; }

    /**
     * Indicates the road in <code>roads</code> with the active light
     */
    private int lightIndex;

    /**
     * Getter method for <code>lightIndex</code>
     * @return
     *  Returns the index of the road with the active light
     */
    public int getLightIndex() { return lightIndex; }

    /**
     * Tracks the remaining time steps available for the road currently indicated by <code>lightIndex</code>
     */
    private int countdownTimer;

    /**
     * Getter method for <code>countdownTimer</code>
     * @return
     *  the remaining time steps available for the road currently indicated by <code>lightIndex</code>
     */
    public int getCountdownTimer() { return countdownTimer; }

    /**
     * Checks the current light value for the road with the active light
     * @return
     *  LightValue of the road with the active light. (Either <code>GREEN</code> or <code>LEFT_SIGNAL</code>)
     */
    public LightValue getCurrentLightValue() { return road.getLightValue(); }

    /**
     * Default constructor
     * @param initRoads
     *  Array of roads to be used in the intersection
     */
    public Intersection(TwoWayRoad[] initRoads) {
        if (initRoads==null||initRoads.length>4)
            throw new IllegalArgumentException();
        for (TwoWayRoad road : initRoads)
            if (road==null)
                throw new IllegalArgumentException();
        roads = new TwoWayRoad[initRoads.length];
        for (int i=0; i<roads.length; i++)
            roads[i] = initRoads[i];
        lightIndex=0;
        road = roads[lightIndex];
        countdownTimer=road.getGreenTime();
    }

    /**
     * Performs a single iteration through the intersection.
     * @return
     *  An array of <code>Vehicles</code> which have passed through the intersection during this time step
     */
    public Vehicle[] timeStep() {
        boolean empty=true;
        for (int way=0; way<road.NUM_WAYS; way++)
            for (int lane=road.MIDDLE_LANE; lane<road.NUM_LANES; lane++)
                if (!road.isEmpty(way,lane)) {
                    empty=false;
                    break;
                }
        if (empty) {
            for (int way = 0; way<road.NUM_WAYS; way++)
                if (!road.isEmpty(way,road.LEFT_LANE)) {
                    empty=false;
                    break;
                }
        }
        if (countdownTimer==0||empty) {
            lightIndex = ++lightIndex % getNumRoads();
            road = roads[lightIndex];
            countdownTimer=road.getGreenTime();
        }
        Vehicle[] passed = roads[lightIndex].proceed(countdownTimer);
        return passed;
    }

    /**
     * Enqueues a vehicle onto a lane in the intersection
     * @param roadIndex
     *  Index of the road in <code>roads</code> which contains the to enqueue onto
     * @param wayIndex
     *  Index of the direction the vehicle is headed
     * @param laneIndex
     *  Index of the lane on which the vehicle is to enqueue
     * @param vehicle
     *  <code>Vehicle</code> to enqueue onto the lane
     * @throws IllegalArgumentException
     *  If <code>vehicle</code> is null,
     *  if <code>roadIndex</code> is not within [0,<code>roads.length</code>),
     *  if <code>wayIndex</code> is not within [0,<code>TwoWayRoad.NUM_WAYS</code>), or
     *  if <code>laneIndex</code> is not within [0,<code>TwoWayRoad.NUM_LANES</code>)
     */
    public void enqueueVehicle(int roadIndex,int wayIndex,int laneIndex,Vehicle vehicle)
      throws IllegalArgumentException {
        if (vehicle==null||roadIndex<0||roadIndex>=roads.length||wayIndex<0||wayIndex>=2||laneIndex<0||laneIndex>=3)
            throw new IllegalArgumentException();
        roads[roadIndex].enqueueVehicle(wayIndex,laneIndex,vehicle);
    }

    /**
     * Prints the current state of the interesction
     */
    public void display() {
        String queue;
        String[] laneLetter = {"L","M","R"};

        for (TwoWayRoad road : roads) {
            System.out.printf("%n%n    %s:%n",road);
            System.out.printf("    %30s%24s%n","FORWARD","BACKWARD");
            System.out.println("    "+"=".repeat(30)+" ".repeat(16)+"=".repeat(30));
            for (int lane=0; lane<road.NUM_LANES*2; lane++) {
                if (lane%2==0) {
                    for (int way = 0; way < road.NUM_WAYS; way++) {
                        if (way == 0) {
                            queue="";
                            for (int i=road.peek(way,lane/2).size()-1;i>-1;i--)
                                queue+=road.peek(way,lane/2).get(i);
                            System.out.printf("    %30s",queue);
                            System.out.printf(" [%s] %s    %s [%s] ",
                              laneLetter[lane/2],(this.road==road&&((road.getLightValue()==LightValue.GREEN&&lane/2>0)||
                                (road.getLightValue()==LightValue.LEFT_SIGNAL&&lane/2==0)) ? " " : "X"),
                                  (this.road==road&&((road.getLightValue()==LightValue.GREEN && 2-lane/2>0)||
                                    (road.getLightValue()==LightValue.LEFT_SIGNAL&&2-lane/2==0)) ? " " : "X"),
                                      laneLetter[laneLetter.length-1-lane/2]);
                        }
                        else
                            for (Vehicle v : road.peek(way, road.NUM_LANES-1-lane/2))
                                System.out.print(v);
                    }
                }
                else if (lane<5)
                    System.out.println("\n    "+"-".repeat(30)+" ".repeat(16)+"-".repeat(30));
            }
            System.out.println("\n    "+"=".repeat(30)+" ".repeat(16)+"=".repeat(30));
        }
        countdownTimer--;
    }
}
