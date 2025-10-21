package be.ugent.battleship.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class BattleshipSoloGame implements IBattleshipSoloGame {
    private Board spelBord;
    private int aantalRijen;
    private int aantalKolommen;
    private int numberOfMoves;

    public BattleshipSoloGame(File file) throws FileNotFoundException {
            Scanner sc = new Scanner(file);


            aantalKolommen = sc.nextInt();
            aantalRijen = sc.nextInt();
            sc.nextLine();
            spelBord = new Board(aantalKolommen, aantalRijen);

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ");
                int bootLengte = Integer.parseInt(parts[0]);
                int startX = Integer.parseInt(parts[1]);
                int startY = Integer.parseInt(parts[2]);
                String richting = parts[3];
                String soortSchip = parts[4];

                spelBord.plaatsSchepen(bootLengte, startX, startY, richting, soortSchip);

                System.out.printf("Schip: %s (%d), positie (%d,%d), %s%n", soortSchip, bootLengte, startX, startY, richting);
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
        Cell cel = spelBord.getCell(pos.x, pos.y);

        if (cel.getTypeCel().equals("ship")) {
            return forOwner ? "ship" : "shot";
        } else {
            return "sea";
        }
    }

    @Override
    public String getCellContentImage(Position pos) {
        String content = getCellContent(pos, false); // of false afhankelijk van wie kijkt

        switch (content) {
            case "ship": return "ship.png";
            case "shot": return "fire.png";
            case "sea": return "fountain.png";
            default: return "unknown.png";
        }

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
        Cell cel = spelBord.getCell(pos.x, pos.y);
        return cel.getSoortSchip();
    }
}
