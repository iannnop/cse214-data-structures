import java.util.Scanner;

/**
 *  The <code>ShipLoader</code> class implements a menu driven application that creates an instance of
 *  <code>CargoShip</code> class, and also provides an interface for a user to manipulate the ship by creating, adding,
 *  and moving <code>Cargo</code> objects. In addition to <code>CargoShip</code>, the <code>ShipLoader</code> also
 *  utilizes another <code>CargoStack</code> called <code>dock</code>, which is the loading/unloading stack for
 *  <code>Cargo</code> being moved to/from the ship.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class ShipLoader {
    /**
     * Prints the current state of the ship and the dock
     *
     * @param ship
     *  The <code>CargoShip</code> to be printed
     * @param dock
     *  The <code>CargoStack</code> representing the dock that should be printed
     */
    public static void printState(CargoShip ship, CargoStack dock) {
        CargoShip shipClone = (CargoShip) ship.clone();
        CargoStack dockClone = (CargoStack) dock.clone();
        Cargo[][] shipCargo = new Cargo[ship.getMaxSize()][ship.getNumStacks()];
        System.out.println();
        for (int i=1; i<ship.getNumStacks()+1; i++) {
            for (int j=ship.getStack(i).size()-1; j>-1; j--) {
                try {
                    shipCargo[j][i-1] = shipClone.popCargo(i);
                } catch (EmptyStackException e) {
                    break;
                }
            }
        }
        while (dockClone.size()>shipCargo.length)
            System.out.println(" ".repeat(6*ship.getNumStacks()+18)+dockClone.pop());

        for (int i=ship.getMaxSize()-1; i>-1;i--) {
            for (int j=0; j<ship.getNumStacks(); j++) {
                if (shipCargo[i][j] == null)
                    System.out.print(" ".repeat(6));
                else
                    System.out.print(" ".repeat(5)+shipCargo[i][j]);
            }
            if (dockClone.size()==i+1)
                System.out.println(" ".repeat(18)+dockClone.pop());
            else
                System.out.println();
        }
        System.out.print("\\=|"+"=====|".repeat(ship.getNumStacks())+"=/   Dock: | ===== |\n \\ ");
        for (int i=1;i<ship.getNumStacks()+1;i++)
            System.out.printf("  %d   ",i);
        System.out.println("/          | ===== |\n  \\"+
          "-----".repeat(ship.getNumStacks())+"-".repeat(ship.getNumStacks()-1)+"/           | ===== |");
        System.out.printf("Total Weight = %.0f / %.0f",ship.getWeight(),ship.getMaxWeight());
    }

    /**
     * The main method runs a menu driven application which allows the user to create an instance of the
     * <code>CargoShip</code> class and then prompts the user for a menu command selecting the operation.
     * <p>Following is a list of menu options and their required information:<p>
     * C) Create new cargo [name] [weight] [strength]
     * L) Load cargo from dock [stackIndex]
     * U) Unload cargo from ship [stackIndex]
     * M) Move cargo between stacks [srcStackIndex] [dstStackIndex]
     * K) Clear dock
     * P) Print ship stacks
     * S) Search for cargo [name]
     * R) Remove cargo [name]
     * Q) Quit
     *
     * @param args Array of command-line arguments that are passed to the main function
     */
    public static void main(String[] args) {
        String menu = " ";
        String name, strength;
        Cargo cargo;
        int stack, dstStack, height;
        double weight;
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to ShipLoader!\n");
        System.out.println("Cargo Ship Parameters\n"+"-".repeat(22));
        System.out.print("Number of stacks: ");
        stack = sc.nextInt();
        System.out.print("Maximum height of stacks: ");
        height = sc.nextInt();
        System.out.print("Maximum total cargo weight: ");
        weight = sc.nextDouble();
        CargoShip ship = new CargoShip(stack,height,weight);
        CargoStack dock = new CargoStack();
        System.out.println("\n\nCargo ship created.\nPulling ship in to dock...\nCargo ship ready to be loaded.");
        printState(ship,dock);
        while (Character.toUpperCase(menu.charAt(0)) != 'Q') {
            System.out.println("\n\nPlease select an option:");
            System.out.println("C)  Create new cargo");
            System.out.println("L)  Load cargo from dock");
            System.out.println("U)  Unload cargo from ship");
            System.out.println("M)  Move cargo between stacks");
            System.out.println("K)  Clear dock");
            System.out.println("P)  Print ship stacks");
            System.out.println("S)  Search for cargo");
            System.out.println("R)  Remove cargo");
            System.out.println("Q)  Quit\n");
            System.out.print("Select a menu option: ");
            menu = sc.next();
            if (menu.length() > 1)
                menu = " ";
            sc.nextLine();
            switch (Character.toUpperCase(menu.charAt(0))) {
                case 'C':
                    System.out.print("Enter the name of the cargo: ");
                    name = sc.nextLine();
                    System.out.print("Enter the weight of the cargo: ");
                    weight = sc.nextDouble();
                    System.out.print("Enter the container strength (F/M/S): ");
                    strength = sc.next();
                    while (!strength.equalsIgnoreCase("F") && !strength.equalsIgnoreCase("M") &&
                      !strength.equalsIgnoreCase("S")) {
                        System.out.print("Invalid input.\nEnter the container strength (F/M/S): ");
                        strength = sc.next();
                    }
                    cargo = new Cargo(name,weight,strength.equalsIgnoreCase("F")?CargoStrength.FRAGILE:
                      strength.equalsIgnoreCase("M")?CargoStrength.MODERATE:CargoStrength.STURDY);
                    try {
                        dock.push(cargo);
                    } catch (CargoStrengthException e) {
                        System.out.print("Operation failed! " +
                                "Cargo at top of the stack cannot support selected cargo strength");
                        break;
                    }
                    System.out.printf("%nCargo '%s' has been pushed into the dock.%n",cargo.getName());
                    printState(ship,dock);
                    break;
                case 'L':
                    System.out.print("Select the load destination stack index: ");
                    stack = sc.nextInt();
                    try {
                        cargo = dock.peek();
                        ship.pushCargo(cargo,stack);
                        dock.pop();
                        System.out.printf("%nCargo '%s' moved from dock to stack %d%n",cargo.getName(),stack);
                    } catch (CargoStrengthException e) {
                        System.out.print("Operation failed! " +
                                "Cargo at top of the stack cannot support selected cargo strength");
                        break;
                    } catch (FullStackException e) {
                        System.out.print("Operation failed! " +
                                "Load destination stack has reached the maximum height");
                        break;
                    } catch (ShipOverweightException e) {
                        System.out.print("Operation failed! " +
                                "Loading cargo would make the ship exceed the maximum weight");
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.print("Operation failed! " +
                                "No cargo containers on the dock to load or " +
                                "stack index not within the appropriate bounds");
                        break;
                    }
                    printState(ship,dock);
                    break;
                case 'U':
                    System.out.print("Select the unload source index: ");
                    stack = sc.nextInt();
                    try {
                        cargo = ship.getStack(stack).peek();
                        dock.push(cargo);
                        ship.popCargo(stack);
                        System.out.printf("%nCargo '%s' moved from dock to stack %d%n",cargo.getName(),stack);
                    } catch (CargoStrengthException e) {
                        System.out.print("Operation failed! " +
                                "Cargo at top of the stack cannot support selected cargo strength");
                        break;
                    } catch (IllegalArgumentException e) {
                        System.out.print("Operation failed! " +
                                "Stack index not within the appropriate bounds");
                        break;
                    } catch (EmptyStackException e) {
                        System.out.print("Operation failed! " +
                                "No cargo containers on the dock to load or " +
                                "stack index not within the appropriate bounds");
                        break;
                    }
                    printState(ship,dock);
                    break;
                case 'M':
                    System.out.print("Source stack index: ");
                    stack = sc.nextInt();
                    System.out.print("Destination stack index: ");
                    dstStack = sc.nextInt();
                    try {
                        cargo = ship.popCargo(stack);
                        ship.pushCargo(cargo,dstStack);
                        System.out.printf("%nCargo '%s' moved from dock to stack %d",cargo.getName(),stack);
                    } catch (CargoStrengthException e) {
                        System.out.print("Operation failed! " +
                                "Cargo at top of the stack cannot support weight");
                    } catch (FullStackException e) {
                        System.out.print("Operation failed! " +
                                "Load destination stack has reached the maximum height");
                    } catch (ShipOverweightException e) {
                        System.out.print("Operation failed! " +
                                "Loading cargo would make the ship exceed the maximum weight");
                    } catch (EmptyStackException e) {
                        System.out.print("Operation failed! " +
                                "No cargo containers on the source stack");
                    }
                    printState(ship,dock);
                    break;
                case 'K':
                    while (!dock.isEmpty())
                        dock.pop();
                    System.out.println("Dock cleared");
                    printState(ship,dock);
                    break;
                case 'P':
                    printState(ship,dock);
                    break;
                case 'S':
                    System.out.print("Enter the name of the cargo: ");
                    name = sc.nextLine();
                    ship.findAndPrint(name);
                    break;
                case 'Q':
                    System.out.print("Program terminating normally...");
                    break;
                default:
                    System.out.print("Invalid input.");
                    break;
            }
        }
    }
}
