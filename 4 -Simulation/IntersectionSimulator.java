import java.util.Scanner;

/**
 *  The <code>IntersectionSimulator</code> class prompts the user for the simulation arguments and runs the simulation
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class IntersectionSimulator {
    /**
     * The main method prompts the user for <code>simulationTime (int)</code>,
     * <code>arrivalProbability (double)</code>, <code>numRoads (int)</code>,
     * and a name and "green" time for each road. This method also parses command line for these args.
     *
     * @param args
     *  Array of command-line arguments that are passed to the main function
     */
    public static void main(String[] args) {
        int simTime, numRoads;
        double prob;
        String[] names;
        int[] times;
        if (args.length > 1) {
            simTime    = Integer.parseInt(args[0]);
            prob       = Double.parseDouble(args[1]);
            numRoads   = Integer.parseInt(args[2]);
            names = new String[numRoads];
            times = new int[numRoads];
            for (int i = 0; i < numRoads; ++i) {
                names[i] = args[3+i];
                times[i] = Integer.parseInt(args[3 + numRoads + i]);
            }
        }
        else {
            String streetName;
            int maxGreen;
            boolean duplicate=false;
            Scanner sc = new Scanner(System.in);
            System.out.println("Welcome to IntersectionSimulator 2021\n");
            System.out.print("Input the simulation time: ");
            simTime = sc.nextInt();
            System.out.print("Input the arrival probability: ");
            prob = sc.nextDouble();
            while (prob<=0.0||prob>1.0) {
                System.out.println("Invalid Input.\nProbability not within (0,1]");
                System.out.print("Input the arrival probability: ");
                prob = sc.nextDouble();
            }
            System.out.print("Input number of streets: ");
            numRoads = sc.nextInt();
            while (numRoads<0||numRoads>4) {
                System.out.println("Invalid Input.\nNumber of streets not within [0,4]");
                System.out.print("Input number of streets: ");
                numRoads = sc.nextInt();
            }
            sc.nextLine();
            names = new String[numRoads];
            times = new int[numRoads];
            for (int i=0; i<numRoads; i++) {
                System.out.printf("Input Street %d name: ",i+1);
                streetName = sc.nextLine();
                for (int j=0; j<i+1; j++)
                    if (streetName.equals(names[j])) {
                        duplicate = true;;
                        break;
                    }
                while (duplicate) {
                    System.out.println("Duplicate detected.");
                    streetName = sc.nextLine();
                    duplicate=false;
                    for (int j=0; j<i+1; j++)
                        if (streetName.equals(names[j])) {
                            duplicate = true;
                            break;
                        }
                }
                names[i] = streetName;
            }
            for (int i=0; i<numRoads; i++) {
                System.out.printf("Input max green time for %s: ",names[i]);
                maxGreen = sc.nextInt();
                while (maxGreen<3) {
                    System.out.println("Invalid input.\n" +
                            "A max green time less than 3 will cause either an error or infinite loop.");
                    System.out.printf("Input max green time for %s: ",names[i]);
                    maxGreen = sc.nextInt();
                }
                times[i] = maxGreen;
            }
        }
        System.out.println("\nStarting simulation...\n");
        if (numRoads==0)
            System.out.print("NO SIMULATION");
        else
            simulate(simTime,prob,names,times);
    }

    /**
     * Simulates the Intersection
     * @param simulationTime
     *  Time that the simulation can check for arrivals
     * @param arrivalProbability
     *  Probability that a <code>Vehicle</code> arrives to the intersection
     * @param roadNames
     *  Array of street names
     * @param maxGreenTimes
     *  Array of max "green" times for each array
     */
    public static void simulate(int simulationTime,double arrivalProbability,String[] roadNames,int[] maxGreenTimes) {
        int passedCars=0;
        int waitingCars=0;
        int totalWait=0;
        int maxWait=0;
        boolean empty;
        TwoWayRoad[] roads = new TwoWayRoad[roadNames.length];
        for (int i=0; i<roads.length; i++)
            roads[i] = new TwoWayRoad(roadNames[i],maxGreenTimes[i]);
        Intersection simulation = new Intersection(roads);
        BooleanSource arrival = new BooleanSource(arrivalProbability);
        Vehicle[] passed;
        String[] arrived = new String[6];
        int time = 1;
        while (true) {
            System.out.println("#".repeat(80));
            System.out.printf("%nTime Step: %d%n%n",time);
            if (time < simulationTime) {
                arrived = new String[6];
                int numArrivals=0;
                for (TwoWayRoad road : roads) {
                    for (int way = 0; way < road.NUM_WAYS; way++) {
                        String direction = (way == road.FORWARD_WAY ? "FORWARD" : "BACKWARD");
                        for (int lane = 0; lane < road.NUM_LANES; lane++) {
                            String laneString =
                              (lane == road.LEFT_LANE ? "LEFT" : lane == road.MIDDLE_LANE ? "MIDDLE" : "RIGHT");
                            if (arrival.occurs()&&numArrivals<6) {
                                Vehicle v = new Vehicle(time);
                                road.enqueueVehicle(way, lane, v);
                                arrived[numArrivals++] = String.format("Car%s entered %s, going %s in %s lane.",
                                        v, road, direction, laneString);
                            }
                        }
                    }
                }
            }
            passed = simulation.timeStep();
            System.out.println("    "+simulation.getCurrentLightValue()+" for "+roads[simulation.getLightIndex()]);
            System.out.println("    Timer = "+simulation.getCountdownTimer());
            System.out.println("\n    ARRIVING CARS:");
            if (time<simulationTime) {
                empty=true;
                for (String arrivals : arrived)
                    if (arrivals != null) {
                        System.out.printf("        %s\n", arrivals);
                        empty=false;
                        waitingCars++;
                    }
                if (empty)
                    System.out.println("        No cars arrived.");
            }
            else
                System.out.println("        Cars no longer arriving.");

            System.out.println("\n    PASSING CARS:");
            empty=true;
            for (Vehicle v : passed) {
                if (v!=null) {
                    System.out.printf("        Car%s passes through. Wait time of %d.%n", v, time - v.getTimeArrived());
                    empty=false;
                    waitingCars--;
                    passedCars++;
                    totalWait+=time-v.getTimeArrived();
                    maxWait = Math.max(maxWait, time-v.getTimeArrived());
                }
            }
            if (empty)
                System.out.println("        No cars passed.");
            simulation.display();
            System.out.println("\n\n    STATISTICS:");
            System.out.printf("        Cars currently waiting:  %d cars%n",waitingCars);
            System.out.printf("        Total cars passed:       %d cars%n",passedCars);
            System.out.printf("        Total wait time:         %d turns%n",totalWait);
            System.out.printf("        Average wait time:       %.2f turns%n%n",
              (totalWait==0?totalWait:(double)totalWait/passedCars));
            boolean roadsEmpty=true;
            for (TwoWayRoad road : roads)
                for (int way=0; way<road.NUM_WAYS; way++)
                    for (int lane=0; lane<road.NUM_LANES; lane++)
                        if (!road.isEmpty(way,lane))
                            roadsEmpty=false;
            if (roadsEmpty&&time>=simulationTime)
                break;
            time++;
        }
        System.out.println(("\n"+"#".repeat(80)).repeat(3));
        System.out.println("\nSIMULATION SUMMARY:");
        System.out.printf("    Total time:           %d steps%n",time);
        System.out.printf("    Total vehicles:       %d cars%n",passedCars);
        System.out.printf("    Longest wait time:    %d turns%n",maxWait);
        System.out.printf("    Total wait time:      %d turns%n",totalWait);
        System.out.printf("    Average wait time:    %.2f turns%n",
          (totalWait==0?totalWait:(double)totalWait/passedCars));
        System.out.println("\nEnd simulation.\n");
    }
}
