package thedrake.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import thedrake.game.*;

import java.util.List;

public class BoardView extends GridPane implements TileViewContext{

    private GameState gameState;
    private TileView selectedTileView;
    private ValidMoves validMoves;
    private StackView selectedStackView;
    private CapturedView capturedViewBlue;
    private CapturedView capturedViewOrange;

    public BoardView(GameState gameState) {
        this.gameState = gameState;
        this.validMoves = new ValidMoves(gameState);

        //gameState.board().dimension(); na obecnou dimenzi

        PositionFactory positionFactory = gameState.board().positionFactory();

        for (int y = 0; y<4; y++) {
            for (int x = 0; x<4; x++) {
                BoardPos boardPos = positionFactory.pos(x, 3 - y);
                add(new TileView(boardPos, gameState.tileAt(boardPos), this), x, y);
            }
        }

        setHgap(5);
        setVgap(5);
        setPadding(new Insets(15));
        setAlignment(Pos.CENTER);

        this.selectedStackView = new StackView(this);
        this.capturedViewBlue = new CapturedView(this, PlayingSide.BLUE);
        this.capturedViewOrange = new CapturedView(this, PlayingSide.ORANGE);
        this.selectedStackView.setMaxSize(100, 100);
    }

    private TileView tileView(BoardPos pos) {
        int index = (3 - pos.j()) * 4 + pos.i();
        return (TileView) getChildren().get(index);
    }

    @Override
    public void tileViewSelected(TileView tileView) {
        if (selectedTileView != null && selectedTileView != tileView) {
            selectedTileView.unselect();
            selectedTileView = null;
            selectedStackView.unselect();
        }

        selectedTileView = tileView;

        clearMoves();
        showMoves(validMoves.boardMoves(tileView.getBoardPos()));
    }

    @Override
    public void stackViewSelected(StackView stackView) {
        if (selectedTileView != null) {
            selectedTileView.unselect();
        }

        selectedStackView = stackView;
        clearMoves();

        List<Move> validStackMoves = validMoves.movesFromStack();
        showMoves(validStackMoves);
    }

    private void clearMoves() {
        for (Node node : getChildren()) {
            TileView tileView = (TileView) node;
            tileView.clearMove();
        }
    }

    private void showMoves(List<Move> moves) {
        for (Move move : moves) {
            tileView(move.target()).setMove(move);
        }
    }

    @Override
    public void executeMove(Move move) {
        if (selectedTileView != null) {
            selectedTileView.unselect();
            selectedTileView = null;
        }

        clearMoves();
        gameState = move.execute(gameState);
        validMoves = new ValidMoves(gameState);

        updateTiles();
        GameResult.changeStateTo(gameState.result());
    }

    private void updateTiles() {
        for (Node node : getChildren()) {
            TileView tileView = (TileView) node;
            tileView.setTile(gameState.tileAt(tileView.getBoardPos()));
            tileView.update();
            selectedStackView.update();
        }
        if (gameState.armyOnTurn().side() == PlayingSide.BLUE) {
            capturedViewOrange.update();
        } else {
            capturedViewBlue.update();
        }
    }

    public GameState getGameState() {
        return gameState;
    }

    public StackView getSelectedStackView() {
        return selectedStackView;
    }

    public CapturedView getCapturedViewBlue() {
        return capturedViewBlue;
    }

    public CapturedView getCapturedViewOrange() {
        return capturedViewOrange;
    }
}
