/**
 *  The <code>CargoShip</code> class represents a container ship which holds stacks of containers.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class CargoShip {
    /**
     * Array of <code>CargoStack</code> to represent a stack of <code>Cargo</code> on the ship
     */
    private CargoStack[] stacks;

    /**
     * Maximum height of the <code>CargoStack</code>
     */
    private int maxHeight;

    /**
     * Maximum weight capacity of the <code>CargoStack</code>
     */
    private double maxWeight;

    /**
     * Default constructor
     * @param numStacks
     *  The number of <code>CargoStacks</code> the ship can support
     * @param initMaxHeight
     *  The maximum height of any stack on the ship
     * @param initMaxWeight
     *  The maximum weight for all the cargo on the ship
     */
    public CargoShip(int numStacks, int initMaxHeight, double initMaxWeight) {
        stacks = new CargoStack[numStacks];
        maxHeight = initMaxHeight;
        maxWeight = initMaxWeight;
        for (int i = 0; i<numStacks; i++)
            stacks[i] = new CargoStack();
    }

    /**
     * Pushes a cargo container to the indicated stack on the cargo ship
     * <p>Preconditions:</p>
     * <p><code>cargo</code> is initialized and not <code>null</code></p>
     * <p><code>stack</code> is between 1 and <code>stacks.length</code>, inclusive</p>
     * <p>The size of the stack at the desired index is less than <code>maxHeight</code></p>
     * <p>The total weight of all <code>Cargo</code> on the ship and <code>cargo.getWeight()</code>
     * is less than <code>maxWeight</code></p>
     * <p>Postconditions:</p>
     * <p>The <code>cargo</code> has been successfully pushed to the given stack, or the appropriate exception
     * has been thrown, in which case the state of the cargo ship should remain unchanged.</p>
     * @param cargo
     *  The container to place on the stack
     * @param stack
     *  The index of the stack on the ship to push <code>cargo</code> onto
     * @throws IllegalArgumentException
     *  Thrown if <code>cargo</code> is <code>null</code> or <code>stack</code> is not in the appropriate bounds
     * @throws FullStackException
     *  Thrown if the stack being pushed to is at the max height
     * @throws ShipOverweightException
     *  Thrown if <code>cargo</code> would make the ship exceed <code>maxWeight</code>
     * @throws CargoStrengthException
     *  Thrown if <code>cargo</code> would be stacked on top of a weaker cargo (following from
     *  the <code>CargoStrength</code> rules)
     */
    public void pushCargo(Cargo cargo, int stack)
      throws IllegalArgumentException, FullStackException, ShipOverweightException, CargoStrengthException {
        if (cargo == null || stack < 1 || stack > stacks.length)
            throw new IllegalArgumentException("Illegal Argument.");
        else if (stacks[stack-1].size()==maxHeight)
            throw new FullStackException("Stack Overflow.");
        else if (getWeight()+cargo.getWeight()>maxWeight)
            throw new ShipOverweightException("Weight would exceed the maximum weight capacity.");
        else if (stacks[stack-1].peek()!=null && stacks[stack-1].peek().getStrength().compareTo(cargo.getStrength())<0)
            throw new CargoStrengthException("Stronger cargo cannot be stacked on top of weaker cargo.");

        stacks[stack-1].push(cargo);
    }

    /**
     * Pops a cargo from one of the specified stack
     * <p>Preconditions:</p>
     * <p>This <code>CargoShip</code> is initialized and not null</p>
     * <p><code>stack</code> is between 1 and <code>stacks.length</code>, inclusive</p>
     * <p>Postconditions:</p>
     * <p>The <code>cargo</code> has been successfully popped from the stack and returned, or the appropriate
     * exception has been thrown, in which case the state of the cargo ship should remain unchanged.</p>
     * @param stack
     *  The index of the stack on the ship to remove the <code>Cargo</code> from
     * @return
     *  Returns the removed <code>Cargo</code> object
     * @throws IllegalArgumentException
     *  Thrown if <code>stack</code> is not in the appropriate bounds
     * @throws EmptyStackException
     *  Thrown if the stack being popped from is empty
     */
    public Cargo popCargo(int stack) throws IllegalArgumentException, EmptyStackException {
        if (stack < 1 || stack > stacks.length)
            throw new IllegalArgumentException("Illegal Argument.");
        if (stacks[stack-1].isEmpty())
            throw new EmptyStackException("Stack Underflow.");

        return stacks[stack-1].pop();
    }

    /**
     * Counts the weight of all the cargo to get the current weight of the ship
     *
     * @return
     *  Returns the current weight of the ship
     */
    public double getWeight() {
        double weight = 0;
        for (CargoStack stack : stacks)
            weight += stack.getWeight();
        return weight;
    }

    /**
     * Getter method for a specific stack on the ship
     * @param stack
     *  The index of the stack on the ship
     * @return
     *  Returns the <code>CargoStack</code> with the given index on the ship
     * @throws IllegalArgumentException
     *  Thrown if <code>stack</code> is not within the appropriate bounds
     */
    public CargoStack getStack(int stack) throws IllegalArgumentException {
        if (stack < 1 || stack > stacks.length)
            throw new IllegalArgumentException("Illegal Argument.");
        return stacks[stack-1];
    }

    /**
     * Clone method for <code>CargoShip</code>
     *
     * @return
     *  Returns a newly created <code>CargoShip</code> filled with new <code>CargoStacks</code> which are filled with
     *  new <code>Cargo</code> following the original ship.
     */
    public Object clone() {
        CargoShip clone = new CargoShip(stacks.length,maxHeight,maxWeight);
        for (int i=0; i<stacks.length; i++)
            clone.stacks[i] = (CargoStack) stacks[i].clone();
        return clone;
    }

    /**
     * Getter method for the weight capacity of the ship
     *
     * @return
     *  Returns <code>maxWeight</code>
     */
    public double getMaxWeight() { return maxWeight; }

    /**
     * Getter method for the maximum number of stacks of the ship
     *
     * @return
     *  Returns <code>stacks.length</code>
     */
    public int getNumStacks() { return stacks.length; }

    /**
     * Iterates through the stacks and returns the height for the current highest stack on the ship
     *
     * @return
     *  Returns the highest stack on the ship
     */
    public int getMaxSize() {
        int maxSize=0;
        for (int i=0; i<stacks.length; i++) {
            if (getStack(i+1).size() > maxSize)
                maxSize = getStack(i+1).size();
        }
        return maxSize;
    }

    /**
     * Finds and prints a formatted table of all the cargo on the ship with a given name. If item could not be found,
     * displays a message that it could not be found.
     * <p>Preconditions:</p>
     * <p>This <code>CargoShip</code> is initialized and not null</p>
     * <p>Postconditions:</p>
     * <p>The details of the cargo with the given name have been found and printed, or the user has been notified
     * that no such cargo has been found.</p>
     * <p>The state of the cargo ship should remain unchanged.</p>
     * @param name
     *  The name of the cargo to find and print
     */
    public void findAndPrint(String name) {
        String output = " Stack   Depth   Weight   Strength\n=======+=======+========+==========\n";
        boolean found=false;
        CargoShip shipClone = (CargoShip) clone();
        Cargo[][] shipCargo = new Cargo[getMaxSize()][getNumStacks()];
        System.out.println();
        for (int i=1; i<getNumStacks()+1; i++) {
            for (int j=getStack(i).size()-1; j>-1; j--) {
                try {
                    shipCargo[j][i-1] = shipClone.popCargo(i);
                } catch (EmptyStackException e) {
                    break;
                }
            }
        }
        for (int i=0; i<shipCargo.length-1; i++) {
            for (int j=0; j<shipCargo[i].length-1; j++) {
                if (shipCargo[j][i]!=null && shipCargo[j][i].getName().equalsIgnoreCase(name)) {
                    found = true;
                    output+=String.format("   %d   |   %d   |  %.0f  |  %s%n",
                      j+1,i,shipCargo[j][i].getWeight(),shipCargo[j][i].getStrength());
                }
            }
        }
        if (found)
            System.out.print(output);
        else
            System.out.printf("Cargo '%s' could not be found on the ship",name);
    }
}
