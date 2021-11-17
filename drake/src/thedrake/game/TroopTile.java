package thedrake.game;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static thedrake.game.TroopFace.AVERS;
import static thedrake.game.TroopFace.REVERS;

public class TroopTile implements Tile, JSONSerializable {
    final private Troop troop;
    final private PlayingSide side;
    final private TroopFace face;

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

    public TroopTile flipped(){

        return new TroopTile(troop, side, face == AVERS ? REVERS : AVERS);
    }

    @Override
    public List<Move> movesFrom(BoardPos pos, GameState state) {
        List<Move> moves = new ArrayList<>();
        for (TroopAction action : troop().actions(face)) {
            moves.addAll(action.movesFrom(pos, side, state));
        }
        return moves;
    }

    @Override
    public void toJSON(PrintWriter writer) {
        writer.printf("{\"troop\":");
        troop.toJSON(writer);
        writer.printf(",\"side\":");
        side.toJSON(writer);
        writer.printf(",\"face\":");
        face.toJSON(writer);
        writer.printf("}");
    }
}
