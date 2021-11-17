package thedrake.ui;

import thedrake.game.GameState;
import thedrake.game.Move;

public interface TileViewContext {

    void tileViewSelected(TileView tileView);

    void stackViewSelected(StackView stackView);

    void executeMove(Move move);

    GameState getGameState();
}
