package thedrake.ui;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import thedrake.game.PlayingSide;
import thedrake.game.Troop;

public class CapturedView extends VBox{
    private TileViewContext tileViewContext;
    private int size = 0;

    public CapturedView(TileViewContext tileViewContext, PlayingSide side) {
        Label l = new Label();
        //sets Label for each stack
        if (side == PlayingSide.BLUE) {
            l.setText("Captured orange units:");
        } else {
            l.setText("Captured blue units:");
        }

        this.setPrefSize(200, 750);

        this.tileViewContext = tileViewContext;

        getChildren().add(l);

        update();
    }

    public void update () {
        //if some units are captured, display them in VBox on side of window
        //and size has changed since last update, ie another unit is captured
        if (!tileViewContext.getGameState().armyNotOnTurn().captured().isEmpty() &&
                size != tileViewContext.getGameState().armyNotOnTurn().captured().size()) {
            //finds unit, which was captured
            Troop unit = tileViewContext.getGameState().armyNotOnTurn().captured().get(size);
            Label label = new Label(unit.name());
            getChildren().add(label);

            //sets this.size to size of captured List, so it prevents same unit to be written multiple times
            size = tileViewContext.getGameState().armyNotOnTurn().captured().size();
        }
    }
}
