import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 *  The <code>StorageManager</code> class allows the user to interact with the storage database by listing the
 *  storage boxes occupied, allowing the user to add or remove storage boxes, searching for a box by id, and listing
 *  all the boxes for a user.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */
public class StorageManager {
    /**
     * Hash table for <code>Storage</code> objects
     */
    private static StorageTable storageTable;

    /**
     * The main method that runs a menu driven application that implements the following menu options on a
     * <code>StorageTable</code>
     *
     * <p>P - Print all storage boxes</p>
     * <p>A - Insert into storage box</p>
     * <p>R - Remove contents from a storage box</p>
     * <p>C - Select all boxes owned by a particular client</p>
     * <p>F - Find a box by ID and display its owner and contents</p>
     * <p>Q - Quit and save workspace</p>
     * <p>X - Quit and delete workspace</p>
     *
     * @param args
     *  Array of command-line arguments that are passed to the main function
     */
    public static void main(String[] args) {
        storageTable = new StorageTable();
        try {
            FileInputStream file = new FileInputStream("storage.obj");
            ObjectInputStream inStream = new ObjectInputStream(file);
            storageTable = (StorageTable) inStream.readObject();
            inStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("""
                    'storage.obj' file could not be found.
                    Using empty StorageTable...
                    """);
        } catch (IOException e) {
            System.out.println("An IO error has occurred.");
        } catch (ClassNotFoundException e) {
            System.out.println("Class could not be found.");
        }
        String option = " ";
        int id;
        String client, content;
        Storage storage;
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello, and welcome to Rocky Stream Storage Manager");
        while (Character.toUpperCase(option.charAt(0)) != 'Q' && Character.toUpperCase(option.charAt(0)) != 'X') {
            System.out.println("""
                    
                    P - Print all storage boxes
                    A - Insert into storage box
                    R - Remove contents from a storage box
                    C - Select all boxes owned by a particular client
                    F - Find a box by ID and display its owner and contents
                    Q - Quit and save workspace
                    X - Quit and delete workspace
                    """);
            System.out.print("Please select an option: ");
            option = sc.next();
            sc.nextLine();
            if (option.length() > 1)
                option = "O";
            switch (Character.toUpperCase(option.charAt(0))) {
                case 'P' -> System.out.println("\n"+storageTable);
                case 'A' -> {
                    System.out.print("Please enter ID: ");
                    id = sc.nextInt();
                    while (id < 0||storageTable.containsKey(id)) {
                        if (id < 0)
                            System.out.println("ID cannot be less than 0");
                        else if (storageTable.containsKey(id))
                            System.out.println("ID already exists in table");
                        System.out.print("Please enter ID: ");
                        id = sc.nextInt();
                    }
                    sc.nextLine();
                    System.out.print("Please enter client: ");
                    client = sc.nextLine();
                    System.out.print("Please enter contents: ");
                    content = sc.nextLine();
                    storage = new Storage(id,client,content);
                    storageTable.putStorage(id, storage);
                    System.out.printf("%nStorage %d set!%n",id);
                }
                case 'R' -> {
                    System.out.print("Please enter ID: ");
                    id = sc.nextInt();
                    sc.nextLine();
                    if (storageTable.containsKey(id)) {
                        storageTable.remove(id);
                        System.out.printf("Box %d is now removed.%n", id);
                    }
                    else
                        System.out.println("Storage box could not be found.");
                }
                case 'C' -> {
                    System.out.print("Please enter the name of the client: ");
                    client = sc.nextLine();
                    boolean found = false;
                    String out = String.format("%-10s%-38s%s%n%s","Box#","Contents","Owner","-".repeat(64));
                    ArrayList<Integer> keys = new ArrayList<>(storageTable.keySet());
                    Collections.sort(keys);
                    for (int key : keys) {
                        storage = storageTable.getStorage(key);
                        if (storage.getClient().equals(client)) {
                            out+=String.format("%n%-10d%-38s%s",storage.getId(),storage.getContent(),storage.getClient());
                            found=true;
                        }
                    }
                    if (found)
                        System.out.println("\n"+out);
                    else
                        System.out.printf("Could not find any storage box owned by %s%n",client);
                }
                case 'F' -> {
                    System.out.print("Please enter ID: ");
                    id = sc.nextInt();
                    storage = storageTable.getStorage(id);
                    if (storage==null) {
                        System.out.println("Storage box could not be found.");
                    }
                    else
                        System.out.println(storage);
                }
                case 'Q' -> {
                    System.out.println("Storage Manager is quitting, current storage is saved for next session");
                    try {
                        FileOutputStream file = new FileOutputStream("storage.obj");
                        ObjectOutputStream outStream = new ObjectOutputStream(file);
                        outStream.writeObject(storageTable);
                        outStream.close();
                    } catch (FileNotFoundException e) {
                        System.out.println("File could not be found");
                    } catch (IOException e) {
                        System.out.println("An IO error has occurred.");
                    }
                }
                case 'X' -> {
                    System.out.println("Storage Manager is quitting, all data is being erased.");
                    File file = new File("storage.obj");
                    file.deleteOnExit();
                }
                default -> System.out.println("Invalid option");
            }
        }
    }
}
