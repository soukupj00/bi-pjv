package thedrake.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import thedrake.game.GameResult;

import java.net.URL;
import java.util.ResourceBundle;

public class VictorySceneController implements Initializable {

    @FXML
    private Button closeButton;

    @FXML
    private Button returnToMenuButton;

    @FXML
    private Label victoryLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void returnToMenuButton(ActionEvent event) {
        GameResult.changeStateTo(GameResult.MAIN_MENU);
    }

    public void closeButtonPressed(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void setVictoryText(String text) {
        victoryLabel.setText(text);
    }
}
