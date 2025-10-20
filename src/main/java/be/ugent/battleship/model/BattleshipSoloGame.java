package be.ugent.battleship.model;

import java.io.File;
import java.util.Scanner;

public class BattleshipSoloGame implements IBattleshipSoloGame {
    private int aantalRijen;
    private int aantalKolommen;
    private int numberOfMoves = 0;

    public BattleshipSoloGame(File file) {
        Scanner sc = new Scanner(new File());

    }

    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public String getCellContent(Position pos, boolean forOwner) {
        return "";
    }

    @Override
    public String getCellContentImage(Position pos) {
        return "";
    }

    @Override
    public void shoot(Position pos) {

    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    @Override
    public int getMoveCount() {
        return 0;
    }

    @Override
    public String shipSunk(Position pos) {
        return "";
    }
}
