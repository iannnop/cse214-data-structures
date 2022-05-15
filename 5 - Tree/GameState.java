/**
 *  The <code>GameState Enum</code> lists the possible current states as the player progresses through the game.
 *
 * @author Jun-Yi Wu
 *  <p>email: jun-yi.wu@stonybrook.edu</p>
 *  <p>Stony Brook ID: 112277356</p>
 *  <p>CSE 214: Recitation 03</p>
 */

public enum GameState {
    /**
     * GameState indicating that the game is over and the player has won
     */
    GAME_OVER_WIN,
    /**
     * GameState indicating that the game is over and the player has lost
     */
    GAME_OVER_LOSE,
    /**
     * GameState indicating that the game is not over
     */
    GAME_NOT_OVER
}
