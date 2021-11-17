package thedrake;

import java.util.List;

import static thedrake.TroopFace.AVERS;
import static thedrake.TroopFace.REVERS;

public class TroopTile implements Tile {
    Troop troop;
    PlayingSide side;
    TroopFace face;

    public TroopTile(Troop troop, PlayingSide side, TroopFace face) {
        this.troop = troop;
        this.side = side;
        this.face = face;
    }

    public Troop troop() {
        return troop;
    }

    public PlayingSide side() {
        return side;
    }

    public TroopFace face() {
        return face;
    }

    public boolean canStepOn(){
        return false;
    }

    public boolean hasTroop(){
        return true;
    }

    @Override
    public List<Move> movesFrom(BoardPos pos, GameState state) {
        return null;
    }

    public TroopTile flipped(){

        return new TroopTile(troop, side, face == AVERS ? REVERS : AVERS);
    }
}
