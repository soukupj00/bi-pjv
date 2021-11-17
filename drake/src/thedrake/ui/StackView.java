package thedrake.ui;

import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import thedrake.game.Tile;
import thedrake.game.TroopFace;
import thedrake.game.TroopTile;

/**
 * Creates single Pane in window, which changes every turn to let player know,
 * whose turn it is and which unit they can use
 */
public class StackView extends Pane {
    private Tile tile;
    private final TileBackgrounds tileBackgrounds = new TileBackgrounds();
    private final Border border = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3)));
    private TileViewContext tileViewContext;

    public StackView(TileViewContext tileViewContext) {
        this.setPrefSize(100, 100);

        setOnMouseClicked(e -> onClick());

        this.tileViewContext = tileViewContext;

        update();
    }

    public void update () {

        if (!tileViewContext.getGameState().armyOnTurn().stack().isEmpty()) {
            setTile(new TroopTile(tileViewContext.getGameState().armyOnTurn().stack().get(0), tileViewContext.getGameState().sideOnTurn(), TroopFace.AVERS));
        } else {
            setTile(null);
        }
        this.setBackground(tileBackgrounds.get(tile));
    }

    public void select () {
        this.setBorder(border);
        tileViewContext.stackViewSelected(this);
    }

    public void unselect() {
        this.setBorder(null);
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public void onClick() {
        select();
    }

}
