package be.ugent.battleship.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BattleshipSoloGame implements IBattleshipSoloGame {
    private int aantalRijen;
    private int aantalKolommen;
    private int numberOfMoves = 0;
    private int x;
    private int y;
    private int bootLengte;
    private String richting;
    private String typeBoot;

    public BattleshipSoloGame(File file) throws FileNotFoundException {
            Scanner sc = new Scanner(file);

            aantalKolommen = sc.nextInt();
            aantalRijen = sc.nextInt();
            sc.nextLine();

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ");
                bootLengte = Integer.parseInt(parts[0]);
                x = Integer.parseInt(parts[1]);
                y = Integer.parseInt(parts[2]);
                richting = parts[3];
                typeBoot = parts[4];

                System.out.printf("Schip: %s (%d), positie (%d,%d), %s%n", typeBoot, bootLengte, x, y, richting);
            }

            sc.close();

    }

    @Override
    public int getRowCount() {
        return aantalRijen;
    }

    @Override
    public int getColumnCount() {
        return aantalKolommen;
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
        return numberOfMoves;
    }

    @Override
    public String shipSunk(Position pos) {
        return "";
    }
}
