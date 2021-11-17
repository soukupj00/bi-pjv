package thedrake.ui;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import thedrake.game.GameState;

public class GameView extends BorderPane {
    private BoardView boardView;
    private VBox infoBox;
    private Label playerOnTurn;
    private Label numberOfCapturedTroops;

    public GameView(GameState gameState) {
        this.boardView = new BoardView(gameState);
        this.setCenter(boardView);

        VBox stackBox = new VBox();
        stackBox.getChildren().add(new Label("Stack: "));
        stackBox.getChildren().add(boardView.getSelectedStackView());
        stackBox.setAlignment(Pos.CENTER);

        this.infoBox = new VBox();
        this.playerOnTurn = new Label();
        this.numberOfCapturedTroops = new Label();

        infoBox.getChildren().add(this.playerOnTurn);
        infoBox.getChildren().add(this.numberOfCapturedTroops);
        infoBox.setAlignment(Pos.CENTER);

        setLeft(boardView.getCapturedViewBlue());
        setRight(boardView.getCapturedViewOrange());

        this.setTop(infoBox);
        this.setBottom(stackBox);
        this.setPrefSize(1200,700);
    }

    public BoardView getBoardView() {
        return boardView;
    }

    public Label getPlayerOnTurn() {
        return playerOnTurn;
    }

    public Label getNumberOfCapturedTroops() {
        return numberOfCapturedTroops;
    }
}
