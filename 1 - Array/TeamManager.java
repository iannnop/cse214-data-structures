import java.util.Scanner;

/**
 *  The <code>TeamManager</code> class implements a menu driven application
 *  that manipulates 5 <code>Team</code> objects by performing operations on it.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */
public class TeamManager {
    /**
     * a readOnly integer storing the maximum number of teams
     */
    public static final int MAX_TEAMS = 5;

    /**
     * an array storing the maximum number of teams
     */
    private static Team[] teams = new Team[MAX_TEAMS];

    /**
     * The currently selected team
     */
    private static int currentTeam = 1;

    /**
     * a string variable storing the selected option
     */
    private static String option = "O";

    /**
     * The main method that runs a menu driven application that fills the array with empty team objects and prompts the
     * user for a menu command selecting the operation.
     *
     * Following is a list of menu options and their required information:
     *
     * A) Add Player [Name] [Hits] [Errors] [Position]
     * G) Get Player stats [Position]
     * L) Get leader in stat [Stat]
     * R) Remove a player [Position]
     * P) Print all players [Team]
     * S) Size of team [Team]
     * T) Select a team [Index]
     * C) Clone team [From] [To]
     * E) Team equals [Team1] [Team2]
     * U) Update stat [Name] [Stat] [StatAmount]
     * Q) Quit
     *
     * @param args
     *  Array of command-line arguments that are passed to the main function
     */
    public static void main(String[] args) {
        Player p; // Variable that stores player objects.
        String playerName, stat; // Variable that stores a player's name, Variable storing a statistic input.
        int team1, team2, position; // Integers storing first team input, second team input, and player position
        teams[currentTeam-1] = new Team();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to TeamManager!");
        while (Character.toUpperCase(option.charAt(0)) != 'Q') {
            System.out.printf("%n%nTeam %d is currently selected.%n%n", currentTeam);
            System.out.println("Please select an option:");
            System.out.println("A)  Add Player");
            System.out.println("G)  Get Player stats");
            System.out.println("L)  Get leader in stat");
            System.out.println("R)  Remove a player");
            System.out.println("P)  Print all players");
            System.out.println("S)  Size of team");
            System.out.println("T)  Select a team");
            System.out.println("C)  Clone team");
            System.out.println("E)  Team equals");
            System.out.println("U)  Update stat");
            System.out.println("Q)  Quit\n");
            System.out.print("Select a menu option: ");
            option = sc.next();
            sc.nextLine();
            if (option.length() > 1)
                option = "O";
            switch (Character.toUpperCase(option.charAt(0))) {
                case 'A':
                    p = new Player();
                    System.out.print("Enter the player's name: ");
                    p.setName(sc.nextLine());
                    System.out.print("Enter the number of hits: ");
                    p.setNumHits(sc.nextInt());
                    System.out.print("Enter the number of errors: ");
                    p.setNumErrors(sc.nextInt());
                    System.out.print("Enter the position: ");
                    try {
                        teams[currentTeam - 1].addPlayer(p, sc.nextInt());
                        System.out.print("Player added: " + p);
                    } catch (FullTeamException e) {
                        System.out.println("\nTeam is full. Player cannot be added.");
                    } catch (IllegalArgumentException e) {
                        System.out.print("\nPosition is not within the valid range.");
                    }
                    break;
                case 'G':
                    System.out.print("Enter the position: ");
                    System.out.print("\n" + teams[currentTeam - 1].getPlayer(sc.nextInt()));
                    break;
                case 'L':
                    System.out.print("Enter the stat: ");
                    stat = sc.next();
                    try {
                        System.out.printf("Leader in %s - " + teams[currentTeam - 1].getLeader(stat), stat.toLowerCase());
                    } catch (IllegalArgumentException e) {
                        System.out.print("Statistic was neither \"hits\" nor \"errors\"");
                    }
                    break;
                case 'R':
                    System.out.print("Enter the position: ");
                    position = sc.nextInt();
                    try {
                        p = teams[currentTeam - 1].getPlayer(position);
                        teams[currentTeam - 1].removePlayer(position);
                        System.out.printf("Player removed at position %d%n%n", position);
                        System.out.println(p.getName() + " has been removed from the team.");
                    } catch (IllegalArgumentException e) {
                        System.out.print("\nPosition is not within the valid range.");
                    }
                    break;
                case 'P':
                    System.out.print("Select team index: ");
                    team1 = sc.nextInt() - 1;
                    if (teams[team1] == null) {
                        System.out.println("\nTeam does not exist.");
                        break;
                    }
                    teams[team1].printAllPlayers();
                    break;
                case 'S':
                    int playerCount = teams[currentTeam - 1].size();
                    System.out.printf("There %s in the current Team.",
                            (playerCount > 1 ? String.format("are %d players", playerCount) :
                                    playerCount == 1 ? "is one player" : "are no players"));
                    break;
                case 'T':
                    System.out.print("Enter team index to select: ");
                    currentTeam = sc.nextInt();
                    if (teams[currentTeam - 1] == null)
                        teams[currentTeam - 1] = new Team();
                    break;
                case 'C':
                    System.out.print("Select team to clone from: ");
                    team1 = sc.nextInt();
                    System.out.print("Select team to clone to: ");
                    team2 = sc.nextInt();
                    teams[team2 - 1] = (Team) teams[team1 - 1].clone();
                    System.out.printf("Team %d has been copied to team %d", team1, team2);
                    break;
                case 'E':
                    System.out.print("Select first team index: ");
                    team1 = sc.nextInt();
                    System.out.print("Select second team index: ");
                    team2 = sc.nextInt();
                    System.out.print("These teams are " + (teams[team1 - 1].equals(teams[team2 - 1]) ? "equal" : "not equal"));
                    break;
                case 'U':
                    System.out.print("Enter the player to update: ");
                    playerName = sc.nextLine();
                    p = null;
                    for (int i = 0; i < teams[currentTeam - 1].size(); i++)
                        if (teams[currentTeam - 1].players[i].getName().equalsIgnoreCase(playerName))
                            p = teams[currentTeam - 1].getPlayer(i + 1);
                    if (p == null) {
                        System.out.print("Player not found.");
                        break;
                    }
                    System.out.print("Enter the stat to update: ");
                    stat = sc.next();
                    if (!(stat.equalsIgnoreCase("hits") || stat.equalsIgnoreCase("errors"))) {
                        System.out.print("\nStatistic was neither \"hits\" nor \"errors\"");
                        break;
                    }
                    System.out.printf("Enter the new number of %s: ", stat.toLowerCase());
                    if (stat.equalsIgnoreCase("hits")) {
                        p.setNumHits(sc.nextInt());
                    } else {
                        p.setNumErrors(sc.nextInt());
                    }
                    System.out.printf("%nUpdated %s %s", p.getName(), stat.toLowerCase());
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
