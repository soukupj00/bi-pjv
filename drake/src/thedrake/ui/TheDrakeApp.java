package thedrake.ui;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import thedrake.game.*;

/**
 * Created by Jan Soukup in a week of horrible stress and pain
 */
public class TheDrakeApp extends Application{
    GameState gameState;
    GameView gameView;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //creates standard game state
        gameState = createSampleGameState();

        //creates menu, where user can choose the next action
        AnchorPane mainMenu = FXMLLoader.load(getClass().getResource("/thedrake/ui/menuScreen.fxml"));
        Scene menu = new Scene(mainMenu);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/thedrake/ui/winningScreen.fxml"));

        //creates screen, which appears after the game has ended
        AnchorPane endView = loader.load();
        Scene endScene = new Scene(endView);

        //creates controller which allows application to set label text after the game ended
        VictorySceneController victorySceneController = loader.getController();

        stage.setScene(menu);
        stage.setTitle("The Drake");
        stage.show();

        new AnimationTimer () {
            @Override
            public void handle(long now) {
                if (GameResult.getStateChanged()) {
                    switch (GameResult.getState()) {
                        case MAIN_MENU:
                            stage.setScene(menu);
                            break;
                        case IN_PLAY:
                            //creates gameView for type IN_PLAY, where players interact with game board and their troops
                            gameView = new GameView(gameState);
                            Scene gameScene = new Scene(gameView);
                            gameScene.getStylesheets().add("/thedrake/ui/style.css");
                            stage.setScene(gameScene);
                            break;
                        case VICTORY:
                            PlayingSide side = gameView.getBoardView().getGameState().armyNotOnTurn().side();
                            victorySceneController.setVictoryText(side + " player has won the game in masterful fashion!");
                            stage.setScene(endScene);
                            break;
                        case DRAW:
                            victorySceneController.setVictoryText("Draw!");
                            stage.setScene(endScene);
                            break;
                    }
                    stage.show();
                    GameResult.changeStateChangedTo(false);
                }
            }
        }.start();
    }

    private static GameState createSampleGameState() {
        Board board = new Board(4);
        PositionFactory positionFactory = board.positionFactory();
        board = board.withTiles(new Board.TileAt(positionFactory.pos(1,2), BoardTile.MOUNTAIN));
        board = board.withTiles(new Board.TileAt(positionFactory.pos(2,1), BoardTile.MOUNTAIN));

        return new StandardDrakeSetup().startState(board);
    }
}
