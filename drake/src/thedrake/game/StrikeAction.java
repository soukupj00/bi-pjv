package thedrake.game;

import java.util.Collections;
import java.util.List;

public class StrikeAction extends TroopAction {

    public StrikeAction(Offset2D offset) {
        super(offset);
    }

    public StrikeAction(int offsetX, int offsetY) {
        super(offsetX, offsetY);
    }

    @Override
    public List<Move> movesFrom(BoardPos origin, PlayingSide side, GameState state) {
        TilePos target = origin.stepByPlayingSide(offset(), side);

        if (state.canCapture(origin, target))
            return Collections.singletonList(new CaptureOnly(origin, (BoardPos) target));

        return Collections.emptyList();
    }
}
