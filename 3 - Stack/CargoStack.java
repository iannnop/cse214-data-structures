import java.util.ArrayList;

/**
 *  The <code>CargoStack</code> class stores <code>Cargo</code> objects within a stack ADT built with the
 *  <code>ArrayList</code> class in the <code>java.util</code> package.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class CargoStack {
    /**
     * Weight of the stack
     */
    private double weight = 0;

    /**
     * Stack of Cargo objects using ArrayList
     */
    private ArrayList<Cargo> stack = new ArrayList<>();

    /**
     * Getter method for <code>weight</code>
     * @return
     *  Returns the weight of the stack
     */
    public double getWeight() { return weight; }

    /**
     * Push method to add a new element at the top of the stack
     * @param cargo
     *  The <code>Cargo</code> object to be pushed onto the stack
     * @throws CargoStrengthException
     *  Thrown if <code>cargo</code> would be stacked on top of a weaker cargo (following from
     *  the <code>CargoStrength</code> rules)
     */
    public void push(Cargo cargo) throws CargoStrengthException {
        if (peek()!=null && peek().getStrength().compareTo(cargo.getStrength())<0)
            throw new CargoStrengthException("CargoStrengthException");
        weight+=cargo.getWeight();
        stack.add(cargo);
    }

    /**
     * Pop method to remove a <code>Cargo</code> object from the top of the stack
     *
     * @return
     *  Returns the removed <code>Cargo</code> object. If the stack is empty, returns null.
     */
    public Cargo pop() {
        if (stack.isEmpty())
            return null;
        Cargo cargo = stack.remove(size()-1);
        weight-=cargo.getWeight();
        return cargo;
    }

    /**
     * Peek method to obtain the <code>Cargo</code> object at the top of the stack without changing the contents
     * of the stack
     *
     * @return
     *  Returns the <code>Cargo</code> object at the top of the stack. If the stack is empty, returns null.
     */
    public Cargo peek() {
        if (stack.isEmpty())
            return null;
        return stack.get(size()-1);
    }

    /**
     * Method to obtain the number of <code>Cargo</code> objects that are currently in the stack
     * @return
     *  Returns the number of <code>Cargo</code> objects in the stack
     */
    public int size() { return stack.size(); }

    /**
     * Checks whether the stack is currently empty
     * @return
     *  Returns false if there is at least one <code>Cargo</code> object in the stack. Returns true otherwise.
     */
    public boolean isEmpty() { return stack.isEmpty(); }

    /**
     * Clone method for CargoStack
     *
     * @return
     *  Returns a newly cloned <code>CargoStack</code> object filled with cloned <code>Cargo</code> objects
     */
    public Object clone() {
        CargoStack clone = new CargoStack();
        Cargo[] copyCargo = new Cargo[size()];
        Cargo temp;
        for (int i=0; i<copyCargo.length; i++)
            copyCargo[i] = pop();
        for (int i=copyCargo.length-1; i>-1; i--) {
            temp = copyCargo[i];
            if (temp == null) break;
            try {
                push(temp);
                clone.push((Cargo)temp.clone());
            } catch (CargoStrengthException e) {
                System.out.print("CargoStrengthException");
                break;
            }
        }
        return clone;
    }
}
