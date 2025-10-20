package be.ugent.battleship;

import be.ugent.battleship.model.*;
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;


public class ZeeslagSoloView extends Application {

    // GOOD FOR TESTING PURPOSES
    // To avoid spending time clicking a file while testing,
    // this constant can be set to true.
    // In that case, the program uses "shipsA.txt" as input
    // (where ships are located in an easy constellation).
    private static boolean TESTMODE = false;

    private IBattleshipSoloGame gameModel;
    private Button[][] gameBoard;
    private Label movesLabel;
    private Stage primaryStage;

    private int cols = 0;
    private int rows = 0;

    private static int BIGSIZE = 50;//50

    private static String pathToImages = System.getProperty("user.dir") + "/src/main/resources/be/ugent/battleship/images/";
    private static String pathToGames = System.getProperty("user.dir") + "/src/main/resources/be/ugent/battleship/games/";


    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showTitleScreen();
    }

    private void showTitleScreen() {
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(20));
        vbox.setAlignment(Pos.CENTER);
        vbox.getStyleClass().add("titleScreen");

        Label titleLabel = new Label("ZEESLAG");
        titleLabel.getStyleClass().add("label");

        Button fileButton = new Button("Load Game from Files");
        fileButton.getStyleClass().add("controlButton");

        if (TESTMODE) {
            fileButton.setOnAction(e -> {
                try {
                    initializeGameForTestPhase();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
        } else {
            fileButton.setOnAction(e -> {
                try {
                    initializeGameFromFile();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }

        vbox.getChildren().addAll(titleLabel, fileButton);

        Scene scene = new Scene(vbox, 400, 300);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Battleship");
        primaryStage.show();
        primaryStage.centerOnScreen();
    }


    private void initializeGameForTestPhase() throws FileNotFoundException {
        gameModel = new BattleshipSoloGame(new File(pathToGames+"shipsA.txt"));
        cols = gameModel.getColumnCount();
        rows = gameModel.getRowCount();

        gameBoard = new Button[rows][cols];
        startGame();
    }

    private void initializeGameFromFile() throws FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(pathToGames));
        fileChooser.setTitle("Select game to load");
        File selectedFile = fileChooser.showOpenDialog(primaryStage);

        if (selectedFile != null) {
            gameModel = new BattleshipSoloGame(selectedFile);

            cols = gameModel.getColumnCount();
            rows = gameModel.getRowCount();

            gameBoard = new Button[rows][cols];

            startGame();
        } else {
            // User cancelled, return to title screen
            showTitleScreen();
        }
    }


    private void startGame() {


        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(5);
        grid.setVgap(5);

        movesLabel = new Label("Moves: 0");
        movesLabel.getStyleClass().add("label");
        grid.add(movesLabel, 0, 0);

        grid.add(makeGameBoard(), 0, 1);

        Scene scene = new Scene(grid);
        primaryStage.setScene(scene);
        primaryStage.setTitle("ZEESLAG");
        primaryStage.show();
        primaryStage.centerOnScreen();

    }

    private void setGraphicOnButton(Button button, String imageFileName) {
        Image image = new Image(getClass().getResource("/be/ugent/battleship/images/" + imageFileName).toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(button.getPrefWidth());  // Adjust to button size
        imageView.setFitHeight(button.getPrefHeight()); // Adjust to button size
        imageView.setPreserveRatio(false); // Stretch image to fit button
        button.setGraphic(imageView);
    }

    private GridPane makeGameBoard() {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(2));
        grid.setHgap(0);
        grid.setVgap(0);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                Button button = new Button();
                button.setPrefSize(BIGSIZE, BIGSIZE);
                button.setMinSize(BIGSIZE, BIGSIZE); // moet er ook bij; anders herschaalt button naar grootte van afbeelding
                button.setMaxSize(BIGSIZE, BIGSIZE); // moet er ook bij; anders herschaalt button naar grootte van afbeelding
                setGraphicOnButton(button, "unknown.png");
                Button[][] finalGameBoard = gameBoard;
                final Position pos = new Position(col, row);

                button.setOnAction(e -> {
                    gameModel.shoot(pos);
                    String nameOfImage = gameModel.getCellContentImage(pos);
                    setGraphicOnButton(button, nameOfImage);

                    checkShipSunk(pos);
                    checkGameOver();
                });

                button.setDisable(false);
                gameBoard[row][col] = button;
                grid.add(button, col, row);
            }
        }
        return grid;
    }

    private void checkShipSunk(Position pos) {
        String naamGezonkenSchip = gameModel.shipSunk(pos);
        if (naamGezonkenSchip != null) {
            Alert alert = new Alert(Alert.AlertType.NONE, "Je liet een " + naamGezonkenSchip + " zinken!", ButtonType.OK);
            alert.showAndWait();
        }
    }

    private void checkGameOver() {
        if (gameModel.isGameOver()) {
            movesLabel.setText("Game Over! Total Moves: " + gameModel.getMoveCount());
            movesLabel.getStyleClass().add("gameOverLabel");
            disableAllButtons(gameBoard);
        }
    }

    private void disableAllButtons(Button[][] buttons) {
        for (Button[] rowButtons : buttons) {
            for (Button button : rowButtons) {
                button.setDisable(true);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

