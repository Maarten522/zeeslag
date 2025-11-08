package be.ugent.battleship.model;

public class Board {
    private Cell[][] cellen;
    int r, c, aantalRijen, aantalKolommen;

    public Board(int aantalRijen, int aantalKolommen) {
        this.aantalRijen = aantalRijen;
        this.aantalKolommen = aantalKolommen;
        cellen = new Cell[aantalRijen][aantalKolommen];

        for (r = 0; r < aantalRijen; r++) {
            for (c = 0; c < aantalKolommen; c++) {
                cellen[r][c] = new Cell();
            }
        }
    }

    public Cell getCell(int x, int y) {
        return cellen[x][y];
    }

    public void plaatsSchepen(int bootLengte, int x, int y, String richting, String soortSchip) {
        boolean horizontaal = richting.equalsIgnoreCase("horizontal");
        boolean verticaal = richting.equalsIgnoreCase("vertical");

        for (int i = 0; i < bootLengte; i++) {
            int startX = x;
            int startY = y;

            if (horizontaal) {
                startX = x + i;
            } else if (verticaal) {
                startY = y + i;
            }

            Cell cel = cellen[startX][startY]; // rij = startY, kolom = startX
            cel.setType("ship");
            cel.setSoortSchip(soortSchip);
            cel.setBootLengte(bootLengte);

            System.out.printf("Cel geplaatst op (%d,%d), soort %s%n", startX, startY, soortSchip);
        }

    }

}
