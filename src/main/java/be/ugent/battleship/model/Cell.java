package be.ugent.battleship.model;

public class Cell {
    private String type;
    private String soortSchip;

    public Cell() {
        this.type = "sea";
        this.soortSchip = null;
    }

    public String getTypeCel() {
        return type;
    }

    public String getSoortSchip() {
        return soortSchip;
    }

    public void setSoortSchip(String soortSchip) {
        this.soortSchip = soortSchip;
    }

    public void setType(String type) {
        this.type = type;
    }
}
