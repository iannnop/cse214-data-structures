import java.util.Scanner;

/**
 *  The <code>Presentation Manager</code> class implements a menu driven application that creates an instance of
 *  <code>SlideList</code> and provides an interface for the user to manipulate the list by adding, removing, and
 *  editing slides.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class PresentationManager {
    /**
     * String variable storing the user's selected menu option
     */
    private static String menu = " ";

    /**
     * Method to prompt the user to create a new <code>Slide</code>
     *
     * @return
     *  Returns the newly created <code>Slide</code>
     */
    public static Slide createSlide() {
        Scanner sc = new Scanner(System.in);
        Slide newSlide = new Slide();
        char choice;
        System.out.print("Enter the slide title: ");
        newSlide.setTitle(sc.nextLine());
        System.out.print("Enter the slide duration: ");
        double duration = sc.nextDouble();
        while (duration <= 0) {
            System.out.println("Duration must be positive.\n");
            System.out.print("Enter the slide duration: ");
            duration = sc.nextDouble();
        }
        newSlide.setDuration(duration);
        int i = 1;
        while (true) {
            sc.nextLine();
            System.out.printf("Bullet %d: ",i);
            newSlide.setBullet(sc.nextLine(), i++);
            if (i>5) {
                System.out.println("No more bullets allowed. Slide is full.\n");
                break;
            }
            System.out.print("Add another bullet point? (Y/N) ");
            choice = Character.toUpperCase(sc.next().charAt(0));
            while (choice != 'Y' && choice != 'N') {
                System.out.println("Invalid option");
                System.out.print("Add another bullet point? (Y/N) ");
                choice = Character.toUpperCase(sc.next().charAt(0));
            }
            if (choice == 'N')
                break;
        }
        return newSlide;
    }

    /**
     * The main method that runs a menu driven application which first creates an empty <code>SlideList</code> and
     * then prompts the user for a menu command selecting the operation.
     * <p>Following is a list of menu options and their required information:<p>
     * F) Move cursor forward
     * B) Move cursor backward
     * D) Display cursor slide
     * E) Edit cursor slide
     * P) Print presentation summary
     * A) Append new slide to tail
     * I) Insert new slide before cursor
     * R) Remove slide at cursor
     * H) Reset cursor to head
     * Q) Quit
     *
     * @param args Array of command-line arguments that are passed to the main function
     */
    public static void main(String[] args) {
        SlideList presentation = new SlideList();
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to PresentationManager!");
        while (Character.toUpperCase(menu.charAt(0)) != 'Q') {
            System.out.println("\n\nPlease select an option:");
            System.out.println("F)  Move cursor forward");
            System.out.println("B)  Move cursor backward");
            System.out.println("D)  Display cursor slide");
            System.out.println("E)  Edit cursor slide");
            System.out.println("P)  Print presentation summary");
            System.out.println("A)  Append new slide to tail");
            System.out.println("I)  Insert new slide before cursor");
            System.out.println("R)  Remove slide at cursor");
            System.out.println("H)  Reset cursor to head");
            System.out.println("Q)  Quit\n");
            System.out.print("Select a menu option: ");
            menu = sc.next();
            if (menu.length() > 1)
                menu = " ";
            switch (Character.toUpperCase(menu.charAt(0))) {
                case 'F':
                    try {
                        presentation.cursorForward();
                        System.out.printf("The cursor has moved forward to slide \"%s\".",
                          presentation.getCursorSlide().getTitle());
                    } catch (EndOfListException e) {
                        System.out.print("Cursor cannot move forward any further.");
                    }
                    break;
                case 'B':
                    try {
                        presentation.cursorBackward();
                        System.out.printf("The cursor has moved backward to slide \"%s\".",
                          presentation.getCursorSlide().getTitle());
                    } catch (EndOfListException e) {
                        System.out.print("Cursor cannot move backward any further.");
                    }
                    break;
                case 'D':
                    if (presentation.getCursorSlide() == null) {
                        System.out.print("No slide to display.");
                        break;
                    }
                    System.out.print(presentation.getCursorSlide());
                    break;
                case 'E':
                    if (presentation.getCursorSlide()==null) {
                        System.out.print("No slide to edit.");
                        break;
                    }
                    System.out.print("Edit title, duration, or bullets? (T/D/B) ");
                    char selection = Character.toUpperCase(sc.next().charAt(0));
                    while (selection != 'T' && selection != 'D' && selection != 'B') {
                        System.out.println("Invalid option");
                        System.out.print("Edit title, duration, or bullets? (T/D/B) ");
                        selection = Character.toUpperCase(sc.next().charAt(0));
                    }
                    if (selection == 'T') {
                        System.out.print("Delete or Edit? (D/E) ");
                        selection = Character.toUpperCase(sc.next().charAt(0));
                        while (selection != 'D' && selection != 'E') {
                            System.out.println("Invalid option");
                            System.out.print("Delete or Edit? (D/E) ");
                            selection = Character.toUpperCase(sc.next().charAt(0));
                        }
                        if (selection == 'D') {
                            presentation.getCursorSlide().setTitle("");
                            System.out.print("Slide title has been deleted");
                        }
                        else {
                            System.out.print("Enter the new title: ");
                            sc.nextLine();
                            presentation.getCursorSlide().setTitle(sc.nextLine());
                        }
                    }
                    else if (selection == 'D') {
                        System.out.print("Delete or Edit? (D/E) ");
                        selection = Character.toUpperCase(sc.next().charAt(0));
                        while (selection != 'D' && selection != 'E') {
                            System.out.println("Invalid option");
                            System.out.print("Delete or Edit? (D/E) ");
                            selection = Character.toUpperCase(sc.next().charAt(0));
                        }
                        if (selection == 'D') {
                            presentation.getCursorSlide().deleteDuration();
                            System.out.print("Slide duration has been deleted");
                        }
                        else {
                            System.out.print("Enter the new duration: ");
                            try {
                                presentation.getCursorSlide().setDuration(sc.nextDouble());
                            } catch (IllegalArgumentException e) {
                                System.out.print("Duration must be positive.");
                            }
                        }
                    }
                    else {
                        System.out.print("Bullet index: ");
                        int index = sc.nextInt();
                        while (index < 1 || index > presentation.getCursorSlide().getNumBullets()+1 || index > 5) {
                            System.out.println("Index outside of range");
                            System.out.print("Bullet index: ");
                            index = sc.nextInt();
                        }
                        System.out.print("Delete or Edit? (D/E) ");
                        selection = Character.toUpperCase(sc.next().charAt(0));
                        while (selection != 'D' && selection != 'E') {
                            System.out.println("Invalid option");
                            System.out.print("Delete or Edit? (D/E) ");
                            selection = Character.toUpperCase(sc.next().charAt(0));
                        }
                        if (selection == 'D') {
                            if (index == presentation.getCursorSlide().getNumBullets()+1) {
                                System.out.print("Cannot delete empty bullet.");
                                break;
                                }
                            presentation.getCursorSlide().setBullet(null, index);
                            System.out.printf("Bullet %d has been deleted", index);
                        }
                        else {
                            System.out.printf("Bullet %d: ",index);
                            sc.nextLine();
                            String bullet = sc.nextLine();
                            presentation.getCursorSlide().setBullet(bullet,index);
                        }
                    }
                    break;
                case 'P':
                    System.out.print(presentation);
                    break;
                case 'A':
                    presentation.appendToTail(createSlide());
                    System.out.printf("Slide \"%s\" has been added to the end of the presentation.",
                      presentation.getCursorSlide().getTitle());
                    break;
                case 'I':
                    presentation.insertBeforeCursor(createSlide());
                    System.out.printf("Slide \"%s\" has been inserted before the cursor.",
                      presentation.getCursorSlide().getTitle());
                    break;
                case 'R':
                    if (presentation.getCursorSlide() == null) {
                        System.out.print("No slide to remove.");
                        break;
                    }
                    try {
                        System.out.printf("Slide \"%s\" has been removed.",presentation.removeCursor().getTitle());
                    } catch (EndOfListException e) {
                        System.out.print("Cannot perform operation on an empty list.");
                    }
                    break;
                case 'H':
                    if (presentation.getCursorSlide() == null) {
                        System.out.print("No slide for the cursor to select.");
                        break;
                    }
                    presentation.resetCursorToHead();
                    System.out.print("Cursor has been reset to the beginning of the list.");
                    break;
                case 'Q':
                    System.out.print("Program terminating normally...");
                    break;
                default:
                    System.out.print("Invalid option");
                    break;
            }
        }
    }
}
