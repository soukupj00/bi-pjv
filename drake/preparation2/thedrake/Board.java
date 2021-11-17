package thedrake;

public class Board {

	// Konstruktor. Vytvoří čtvercovou hrací desku zadaného rozměru, kde všechny dlaždice jsou prázdné, tedy BoardTile.EMPTY
	public Board(int dimension) {
		// Místo pro váš kód
	}

	// Rozměr hrací desky
	public int dimension() {
		// Místo pro váš kód
	}

	// Vrací dlaždici na zvolené pozici.
	public BoardTile at(TilePos pos) {
		// Místo pro váš kód
	}

	// Vytváří novou hrací desku s novými dlaždicemi. Všechny ostatní dlaždice zůstávají stejné
	public Board withTiles(TileAt ...ats) {
		// Místo pro váš kód
	}

	// Vytvoří instanci PositionFactory pro výrobu pozic na tomto hracím plánu
	public PositionFactory positionFactory() {
		// Místo pro váš kód
	}
	
	public static class TileAt {
		public final BoardPos pos;
		public final BoardTile tile;
		
		public TileAt(BoardPos pos, BoardTile tile) {
			this.pos = pos;
			this.tile = tile;
		}
	}
}

