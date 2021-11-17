package thedrake.game;

import java.io.PrintWriter;

public enum GameResult implements JSONSerializable{
	VICTORY, DRAW, IN_PLAY, MAIN_MENU;

	public static boolean stateChanged = false;
	public static GameResult state = null;

    public static void changeStateTo(GameResult newState) {
        if (state == newState) {
            return;
        }
        state = newState;
        changeStateChangedTo(true);
    }

    public static void changeStateChangedTo(boolean stateChanged) {
        GameResult.stateChanged = stateChanged;
    }

    public static GameResult getState() {
        return state;
    }

    public static boolean getStateChanged() {
        return stateChanged;
    }

    @Override
    public void toJSON(PrintWriter writer) {
	    writer.printf("\"%s\"", this.toString());
	}
}
