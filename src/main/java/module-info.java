module be.ugent.battleship {
    requires javafx.controls;
    requires javafx.fxml;


    opens be.ugent.battleship to javafx.fxml;
    exports be.ugent.battleship;
}