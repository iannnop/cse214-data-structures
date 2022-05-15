import java.util.Scanner;
import java.util.zip.DataFormatException;

/**
 *  The <code>Zork</code> class represents a Zork game that allows the user to construct and
 *  traverse a <code>StoryTree</code>.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class Zork {
    /**
     * Main method runs a menu driven application that allows the user to play or edit a <code>StoryTree</code> from
     * a file or a newly constructed tree.
     * @param args
     *  Array of command-line arguments that are passed to the main function
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String menu = " ";
        StoryTree tree;
        System.out.println("Hello, and Welcome to Zork!\n");
        System.out.print("Please enter the file name: ");
        String filename = sc.nextLine();
        try {
            tree = StoryTree.readTree(filename);
            System.out.println("Loading game from file...\n");
            System.out.println("File loaded!\n");
        } catch (DataFormatException e) {
            System.out.println("File not accepted.\n");
            System.out.println("Creating new tree...\n");
            tree = new StoryTree("option","message");
            System.out.println("Tree created!\n");
        }
        while (!menu.equalsIgnoreCase("Q")&&!menu.equalsIgnoreCase("P")) {
            System.out.print("Would you like to edit (E), play (P), or quit (Q)? ");
            menu = sc.nextLine();
            System.out.println();
            switch (Character.toUpperCase(menu.charAt(0))) {
                case 'P' -> playGame(tree);
                case 'E' -> editGame(tree);
                case 'Q' -> { }
                default -> System.out.println("Invalid input");
            }
        }
        System.out.println("Game being saved to "+filename+"...");
        StoryTree.saveTree(filename, tree);
        System.out.println("Save successful!");
        System.out.println("\nProgram terminating normally...");
    }

    /**
     * Provides a user interface allowing a player to play the game represented by <code>tree</code>.
     * This method allows the user to traverse the tree by continually displaying messages and allowing the user to
     * select options until a tree is reached.
     * @param tree
     *  the <code>StoryTree</code> to be traversed
     */
    public static void playGame(StoryTree tree) {
        Scanner sc = new Scanner(System.in);
        String choice;
        tree.getGameState();
        System.out.println(tree.getCursorOption()+"\n");
        while (tree.getGameState().equals(GameState.GAME_NOT_OVER)) {
            System.out.println(tree.getCursorMessage());
            for (String[] option : tree.getOptions()) {
                if (option[0]!=null&&option[1]!=null)
                    System.out.println(option[0].substring(option[0].length()-1)+") "+option[1]);
            }
            System.out.print("Please make a choice: ");
            choice = sc.nextLine();
            if (choice.equalsIgnoreCase("C"))
                System.out.printf("Probability of a win at this point: %3.1f%%%n",tree.winProbability()*100.0);
            else {
                try {
                    tree.selectChild(choice);
                } catch (NodeNotPresentException e) {
                    System.out.println("Invalid selection");
                }
            }
            System.out.println();
            tree.getGameState();
        }
        System.out.println(tree.getCursorMessage());
        System.out.println("\nGAME OVER");
        System.out.println("Thanks for playing!\n");
    }

    /**
     * Provides a user interface allowing a user to edit the story followed by the game represented by <code>tree</code>
     * @param tree
     *  the <code>StoryTree</code> to be edited
     */
    public static void editGame(StoryTree tree) {
        Scanner sc = new Scanner(System.in);
        String menu = " ";
        while (!menu.equalsIgnoreCase("Q")) {
            System.out.println("\nZork editor: ");
            System.out.println("\tV: View the cursor's position, option, and message");
            System.out.println("\tS: Select a child of this cursor (options are 1, 2, and 3)");
            System.out.println("\tO: Set the option of the cursor");
            System.out.println("\tM: Set the message of the cursor");
            System.out.println("\tA: Add a child StoryNode to the cursor");
            System.out.println("\tD: Delete one of the cursor's children and all its descendants");
            System.out.println("\tR: Move the cursor to the root of the tree");
            System.out.println("\tP: Return the cursor to the parent node");
            System.out.println("\tQ: Quit editing and return to main menu");
            System.out.print("\nPlease select an option: ");
            menu = sc.nextLine();
            if (menu.length()>1)
                menu = " ";
            System.out.println();
            switch (Character.toUpperCase(menu.charAt(0))) {
                case 'V' -> {
                    System.out.println("Position: "+tree.getCursorPosition());
                    System.out.println("Option: "+tree.getCursorOption());
                    System.out.println("Message: "+tree.getCursorMessage());
                }
                case 'S' -> {
                    if (tree.getNumChildren()==0) {
                        System.out.println("Cannot descend any further");
                        break;
                    }
                    System.out.print("Please select a child: ");
                    String position = sc.nextLine();
                    try {
                        tree.selectChild(position);
                    } catch (NodeNotPresentException e) {
                        System.out.printf("Error. No child %s for the current node%n",position);
                    }
                }
                case 'O' -> {
                    System.out.print("Please enter a new option: ");
                    tree.setCursorOption(sc.nextLine());
                }
                case 'M' -> {
                    System.out.print("Please enter a new message: ");
                    tree.setCursorMessage(sc.nextLine());
                }
                case 'A' -> {
                    System.out.print("Enter an option: ");
                    String option = sc.nextLine();
                    System.out.print("Enter a message: ");
                    String message = sc.nextLine();
                    try {
                        tree.addChild(option,message);
                    } catch (TreeFullException e) {
                        System.out.println("\nAll three child spots are full. Child not added.");
                    }
                }
                case 'D' -> {
                    System.out.print("Please select a child: ");
                    String position = sc.nextLine();
                    try {
                        tree.removeChild(position);
                        System.out.println("\nSubtree deleted.");
                    } catch (NodeNotPresentException e) {
                        System.out.printf("Error. No child %s for the current node%n",position);
                    }
                }
                case 'R' -> tree.resetCursor();
                case 'P' -> tree.returnToParent();
                case 'Q' -> { return; }
                default -> System.out.println("Invalid input");
            }
        }
    }
}
