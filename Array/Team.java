/**
 *  The <code>Team</code> class implements an array of <code>Player</code> objects
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public class Team {
    /**
     * Maximum number of players on a team
     */
    public static final int MAX_PLAYERS = 40;

    /**
     * Current number of players on the team. Cannot exceed MAX_PLAYERS
     */
    int playerCount = 0;

    /**
     * An Array that stores Player objects
     */
    Player[] players;

    /**
     * Constructs an instance of the Team class with no Player objects in it
     *
     * Postconditions:
     *     This Team has been initialized to an empty array of Players
     */
    public Team() {
        players = new Player[MAX_PLAYERS];
    }

    /**
     * Generates a clone of this Team
     *
     * @return
     *  Returns a clone of this Team. Subsequent changes to the clone will not affect the original, or vice versa.
     */
    public Object clone() {
        Team copy = new Team();
        for (int i = 0; i<size(); i++) {
            try {
                copy.addPlayer((Player) players[i].clone(),i+1);
            } catch (FullTeamException e) {
                e.printStackTrace();
            }
        }
        return copy;
    }

    /**
     * Compare this Team to another object for equality
     *
     * @param obj
     *  An object to which this Team is compared
     *
     * @return
     *  A return value of true indicates that obj refers to a Team object with the same players in the same order
     *  as this team. The return value is false otherwise or if the obj is not a Team object.
     */
    public boolean equals(Object obj) {
        if (obj instanceof Team) {
            Team team = (Team) obj;
            if (size() != team.size())
                return false;

            for (int i = 0; i<size(); i++) {
                if (!players[i].equals(team.players[i]))
                    return false;
            }
            return true;
        }
        return false;
    }

    /**
     * Determines the number of Players that are currently on this Team
     *
     * @return
     *  The number of players on the Team.
     *
     * Preconditions:
     *     This Team object has been instantiated.
     */
    public int size() { return playerCount; }

    /**
     * Adds a Player to this Team at the indicated position in the lineup
     *
     * @param p
     *  The new Player object to be added to this Team
     *
     * @param position
     *  The position in the lineup where the Player will be inserted
     *
     * @throws FullTeamException
     *  Indicates that there is no more room inside of the Team to store the new Player object
     *
     * @throws IllegalArgumentException
     *  Indicates that position is not within the valid range
     *
     *  Preconditions:
     *      This Team object has been instantiated
     *      1 less than or equal to position less than or equal to size()+1
     *      The number of Player objects is less than MAX_PLAYERS
     *
     *  Postconditions:
     *      The new Player is now stored at the desired position in the Team.
     *      All Players >= position are moved back one position.
     */
    public void addPlayer(Player p, int position) throws FullTeamException, IllegalArgumentException {
        if (size() == MAX_PLAYERS)
            throw new FullTeamException("Team is full. Player cannot be added.");
        if (position > size()+1 || position < 1)
            throw new IllegalArgumentException("Position is not within the valid range.");
        playerCount++;
        for (int i = size()-1; i>position-1; i--)
            players[i] = players[i-1];
        players[position-1] = p;
    }

    /**
     * Removes a Player from this Team at the indicated position in the lineup.
     *
     * @param position
     *  The position in the lineup where the player is to be removed
     *
     * @throws IllegalArgumentException
     *  Indicates that position is not within the valid range
     *
     *  Preconditions:
     *      This Team object has been instantiated
     *      1 less than or equal to position less than or equal to size()
     *
     *  Postconditions:
     *      The Player at the desired position in the Team has been removed.
     *      All Players >= position are moved forward one position.
     */
    public void removePlayer(int position) throws IllegalArgumentException {
        if (position > size() || position < 1)
            throw new IllegalArgumentException("Position is not within the valid range.");
        if (position == size())
            players[size()-1] = null;
        else
            for (int i = position; i<size(); i++)
                players[i-1] = players[i];
        playerCount--;
    }

    /**
     * Returns a reference to a player in the lineup at the indicated position
     *
     * @param position
     *  The position in the lineup from which the Player is to be retrieved
     *
     * @return
     *  Returns the Player from the given index
     *
     * @throws IllegalArgumentException
     *  Indicates that position is not within the valid range
     *
     *  Preconditions:
     *      This Team object has been instantiated
     */
    public Player getPlayer(int position) throws IllegalArgumentException {
        if (position > size() || position < 1)
            throw new IllegalArgumentException("Position is not within the valid range.");
        return players[position-1];
    }

    /**
     * Returns the Player with the best value in the given statistic ("hits" or "errors).
     *
     * @param stat
     *  either "hits" or "errors"
     *
     * @return
     *  Returns the Player with the best stat. HIGHER hits are good, whereas LOWER errors are good.
     *
     * @throws IllegalArgumentException
     *  Indicates that the desired stat was neither "hits" nor "errors"
     *
     *  Preconditions:
     *      This Team object has been instantiated
     */
    public Player getLeader(String stat) throws IllegalArgumentException {
        Player best = players[0];
        if (stat.equalsIgnoreCase("hits")) {
            for (int i=1; i<size(); i++)
                if (players[i].getNumHits() > best.getNumHits())
                    best = players[i];
            return best;
        }
        else if (stat.equalsIgnoreCase("errors")) {
            for (int i=1; i<size(); i++)
                if (players[i].getNumHits() < best.getNumHits())
                    best = players[i];
            return best;
        }
        else {
            throw new IllegalArgumentException("Statistic was neither \"hits\" or \"errors\"");
        }
    }

    /**
     * Prints a neatly formatted table of each Player in this Team on its own line with its position number and stats
     *
     *  Preconditions:
     *      This Team object has been instantiated
     *
     *  Postconditions:
     *      A neatly formatted table of each Player in the Team has been displayed to the user
     */
    public void printAllPlayers() {
        System.out.print(this);
    }

    /**
     * Gets the String representation of this Team object, which is a neatly formatted table of each Player in the Team
     * on its own line with its position number and stats
     *
     * @return
     *  Returns the String representation of this Team object
     */
    public String toString() {
        String out = "";
        out += String.format("%n%-10s%-19s%-11s%s%n%s%n","Player#","Name","Hits","Errors","-".repeat(48));
        for (int i = 0; i<size(); i++) {
            out += String.format("%-10d%-19s%-11d%d%n",
              i+1,players[i].getName(),players[i].getNumHits(),players[i].getNumErrors());
        }
        return out;
    }
}
