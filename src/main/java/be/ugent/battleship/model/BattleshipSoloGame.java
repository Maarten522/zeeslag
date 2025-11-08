package be.ugent.battleship.model;

//import be.ugent.battleship.model.Cell;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;
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
            if (line.isEmpty())
                continue;

            String[] parts = line.split(" ");
            int bootLengte = Integer.parseInt(parts[0]);
            int startX = Integer.parseInt(parts[1]);
            int startY = Integer.parseInt(parts[2]);
            String richting = parts[3];
            String soortSchip = parts[4];

            spelBord.plaatsSchepen(bootLengte, startX, startY, richting, soortSchip);

            System.out.printf("Schip: %s (%d), positie (%d,%d), %s%n", soortSchip, bootLengte, startX, startY,
                    richting);
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
            case "ship":
                return "ship.png";
            case "shot":
                return "fire.png";
            case "sea":
                return "fountain.png";
            default:
                return "unknown.png";
        }

    }

    @Override
    public void shoot(Position pos) {
        Cell cel = spelBord.getCell(pos.x, pos.y);

        if (!cel.isGeraakt()) {
            cel.setGeraakt(true);
            numberOfMoves++;
        }
    }

    @Override
    public boolean isGameOver() {
        for (int y = 0; y < aantalRijen; y++) {
            for (int x = 0; x < aantalKolommen; x++) {
                Cell cel = spelBord.getCell(x, y);
                if (cel.getTypeCel().equals("ship") && !cel.isGeraakt()) {
                    return false;
                }
            }
        }
        return true; //boolean true wordt bij volledig gezonken boot (equals "ship" en is geraakt
    }

    @Override
    public int getMoveCount() {
        return numberOfMoves;
    }

    @Override
    public String shipSunk(Position pos) {
        Cell cel = spelBord.getCell(pos.x, pos.y);
        if (!cel.getTypeCel().equals("ship")) {
            return null;// als het geen schip is stopt methode meteen
        }

        String soortSchip = cel.getSoortSchip().toLowerCase();// slaat soort schip op als cel gelijk is aan schip
        int geraakt = 0;
        for (int y = 0; y < aantalRijen; y++) {
            for (int x = 0; x < aantalKolommen; x++) {
                Cell c = spelBord.getCell(x, y);
                Position startBoot = cel.getStartPositie(); // unieke startpositie per schip om te zorgen dat boten met hetzelfde type ook geschoten kunnen worden

                if (c.getTypeCel().equals("ship") && c.getSoortSchip().toLowerCase().equals(soortSchip) && c.getStartPositie().equals(startBoot) && c.isGeraakt()) {
                    geraakt++;
                }
            }
        }


        if (geraakt == cel.getBootlengte()) {
            return soortSchip;
        }
        return null;

    }
}
