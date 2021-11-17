package thedrake.game;

import java.io.PrintWriter;

public enum PlayingSide implements JSONSerializable{
    ORANGE, BLUE;

    @Override
    public void toJSON(PrintWriter writer) {
        writer.printf("\"%s\"", this.toString());
    }
}
