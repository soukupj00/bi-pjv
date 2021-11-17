package thedrake.ui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import thedrake.game.BoardPos;
import thedrake.game.Move;
import thedrake.game.Tile;

public class TileView extends Pane {

    private BoardPos boardPos;
    private Border selectionBorder = new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3)));
    private final ImageView moveImage;
    private Move move = null;
    private Tile tile;
    private TileBackgrounds backgrounds = new TileBackgrounds();
    private TileViewContext tileViewContext;

    public TileView(BoardPos pos, Tile tile, TileViewContext tileViewContext) {
        this.boardPos = pos;
        this.tile = tile;
        this.tileViewContext = tileViewContext;

        setPrefSize(100,100);

        setOnMouseClicked(e -> onClick());

        moveImage = new ImageView(getClass().getResource("/assets/move.png").toString());
        moveImage.setVisible(false);
        getChildren().add(moveImage);

        update();
    }

    public BoardPos getBoardPos() {
        return boardPos;
    }

    private void onClick() {
        if (move != null) {
            tileViewContext.executeMove(move);
        }

        if (tile.hasTroop()) {
            select();
        }
    }

    private void select () {
        setBorder(selectionBorder);
        tileViewContext.tileViewSelected(this);
    }

    public void unselect() {
        setBorder(null);
    }

    public void setMove(Move move) {
        this.move = move;
        moveImage.setVisible(true);
    }

    public void clearMove() {
        this.move = null;
        moveImage.setVisible(false);

    }

    public void setTile(Tile tileAt) {
        this.tile = tileAt;
        update();
    }

    public void update() {
        setBackground(backgrounds.get(tile));
    }
}
