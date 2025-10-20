package be.ugent.battleship.model;

import java.io.File;

public class DummyBattleshipSoloGame implements IBattleshipSoloGame {

    private int numberOfMoves = 0;

    public DummyBattleshipSoloGame(File file) {
    }

    @Override
    public int getRowCount() {
        return 5;
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getCellContent(Position pos, boolean forOwner) {
        if (pos.x == pos.y) {
            return "X";
        } else return ".";
    }

    @Override
    public String getCellContentImage(Position pos) {
        if (pos.x == pos.y) {
            return "fire.png";
        } else return "sea.png";
    }

    @Override
    public void shoot(Position pos) {
        // nothing happens; dummy is not really functional
    }

    @Override
    public boolean isGameOver() {
        return false; // dummy never stops
    }

    @Override
    public int getMoveCount() {
        return 0; // dummy is not really functional
    }

    @Override
    public String shipSunk(Position pos) {
        return null; // ships are unsinkable in dummy game!
    }

}
