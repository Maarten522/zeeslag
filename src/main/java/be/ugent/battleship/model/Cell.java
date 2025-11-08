package be.ugent.battleship.model;

public class Cell {
    private String type;
    private String soortSchip;
    private Boolean geraakt;
    private Integer bootlengte;

    public Cell() {
        this.type = "sea";
        this.soortSchip = null;
        this.geraakt = false;
    }

    public boolean isGeraakt() {
        return geraakt;
    }

    public void setGeraakt(boolean geraakt) {
        this.geraakt = geraakt;
    }

    public String getTypeCel() {
        return type;
    }

    public String getSoortSchip() {
        return soortSchip;
    }

    public int getBootlengte() {
        return bootlengte;
    }

    public void setBootLengte(int bootLengte) {
        this.bootlengte = bootLengte;
    }

    public void setSoortSchip(String soortSchip) {
        this.soortSchip = soortSchip;
    }

    public void setType(String type) {
        this.type = type;
    }
}
