import java.util.Scanner;

/**
 *  The <code>NeoViewer</code> class allows the user to view datasets obtained from the NASA NeoW API
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */
public class NeoViewer {
    /**
     * The main method that runs a menu driven application which creates a <code>NeoDatabase</code> instance and
     * then prompts the user for a menu command selecting the operation
     *
     * @param args
     *  Array of command-line arguments that are passed to the main function
     */
    public static void main(String[] args) {
        NeoDatabase neoDB = new NeoDatabase();
        Scanner sc = new Scanner(System.in);
        String option = " ";
        String sortOption;
        int pageNumber;
        System.out.println("Welcome to NEO Viewer!");
        while (Character.toUpperCase(option.charAt(0)) != 'Q') {
            System.out.println("""
                                    
                    Option Menu:
                      A) Add a page to the database
                      S) Sort the database
                      P) Print the database as a table
                      Q) Quit
                    """);
            System.out.print("Select a menu option: ");
            option = sc.next();
            sc.nextLine();
            if (option.length() > 1)
                option = " ";
            switch (Character.toUpperCase(option.charAt(0))) {
                case 'A' -> {
                    System.out.print("Enter the page to load: ");
                    pageNumber = sc.nextInt();
                    while (pageNumber<0||pageNumber>715) {
                        System.out.print("Page number outside of acceptable range\nEnter the page to load: ");
                        pageNumber = sc.nextInt();
                    }
                    neoDB.addAll(neoDB.buildQueryURL(pageNumber));
                }
                case 'S' -> {
                    System.out.println("""
                            R) Sort by referenceID
                            D) Sort by diameter
                            A) Sort by approach date
                            M) Sort by miss distance
                            """);
                    System.out.print("Select a menu option: ");
                    sortOption = sc.nextLine();
                    if (sortOption.length() > 1)
                        sortOption = " ";
                    switch (Character.toUpperCase(sortOption.charAt(0))) {
                        case 'R' -> {
                            neoDB.sortBy(new ReferenceIDComparator());
                            System.out.println("Table sorted by referenceID");
                        }
                        case 'D' -> {
                            neoDB.sortBy(new DiameterComparator());
                            System.out.println("Table sorted by diameter");
                        }
                        case 'A' -> {
                            neoDB.sortBy(new ApproachDateComparator());
                            System.out.println("Table sorted by approach date");
                        }
                        case 'M' -> {
                            neoDB.sortBy(new MissDistanceComparator());
                            System.out.println("Table sorted by miss distance");
                        }
                        default -> System.out.println("Invalid option");
                    }
                }
                case 'P' -> neoDB.printTable();
                case 'Q' -> System.out.println("Program terminating normally...");
                default -> System.out.println("Invalid option");
            }
        }
    }
}
