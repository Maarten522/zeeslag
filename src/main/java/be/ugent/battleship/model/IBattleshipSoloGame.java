package be.ugent.battleship.model;

public interface IBattleshipSoloGame {

    /**
     * Returns the number of rows in the game board.
     */
    int getRowCount();

    /**
     * Returns the number of columns in the game board.
     */
    int getColumnCount();

    /**
     * This method is good for testing purposes, for example in a non-graphical main program.
     * The method returns what symbol has to be shown when showing this cell on the console.
     * As it is only for testing purposes, the programmer can choose his own symbols en meanings ("*", "S", "." etc.).
     * If you want to see all ships (even if not yet detected by the player), let forOwner be true.
     * If you want to see what the player will see during the game, let forOwner be false.
     * In the original game, with two players, the 'owner' of the ships sees all of them, of course.
     * The opponent only sees something when he already shot that position.
     */
    String getCellContent(Position pos, boolean forOwner);

    /**
     * Returns the name of the image that has to be displayed when showing this cell.
     * Needed by the Graphical User Interface (GUI).
     * There are three possibilities:
     *     position not yet shot
     *     position is already shot, there is a ship
     *     position is already shot, there is no ship
     */
    String getCellContentImage(Position pos);

    /**
     * Handles the logic when a position is shot by the player.
     * So the game should at least remember that this position is shot.
     */
    void shoot(Position pos);

    /**
     * Returns true if all ships are destroyed.
     */
    boolean isGameOver();

    /**
     * Returns the number of moves made by the player.
     */
    int getMoveCount();

    /**
     * Returns the name of the ship that is on that position,
     * but ONLY if it is destroyed ( = all parts of the ship are shot).
     * If there is no ship or the ship is not yet destroyed, 'null' is returned.
     */
    String shipSunk(Position pos);
}

