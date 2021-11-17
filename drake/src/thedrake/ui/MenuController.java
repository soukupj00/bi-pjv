package thedrake.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import thedrake.game.GameResult;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    private Button twoPlayerButton;

    @FXML
    private Button singlePlayerButton;

    @FXML
    private Button onlinePlayerButton;

    @FXML
    private Button closeButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {}

    public void twoPlayerButtonAction(ActionEvent event) {
        //starts game
        GameResult.changeStateTo(GameResult.IN_PLAY);
    }

    public void singlePlayerButtonAction(ActionEvent event){}

    public void onlinePlayerButtonAction(ActionEvent event){}

    public void closeButtonAction(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
