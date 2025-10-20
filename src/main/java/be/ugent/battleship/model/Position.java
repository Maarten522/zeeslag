package be.ugent.battleship.model;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y){
        this.x = x;
        this.y = y;
    }

    // for those who are not sure where X and Y are located
    public int getRow(){
        return y;
    }

    // for those who are not sure where X and Y are located
    public int getCol(){
        return x;
    }
}
