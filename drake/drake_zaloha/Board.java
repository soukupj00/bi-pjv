package thedrake;

public class Board {
	private BoardTile [ ] [ ] tile;
	private int dimension;

	// Konstruktor. Vytvoří čtvercovou hrací desku zadaného rozměru, kde všechny dlaždice jsou prázdné, tedy BoardTile.EMPTY
	public Board(int dimension) {
		tile = new BoardTile[dimension][dimension];
		for (int i = 0; i<dimension; i++){
			for (int j = 0; j<dimension; j++){
				tile[i][j] = BoardTile.EMPTY;
			}
		}
		this.dimension = dimension;
	}

	// Rozměr hrací desky
	public int dimension() {
		return dimension;
	}

	// Vrací dlaždici na zvolené pozici.
	public BoardTile at(TilePos pos) {
		return tile[pos.i()][pos.j()];
	}

	// Vytváří novou hrací desku s novými dlaždicemi. Všechny ostatní dlaždice zůstávají stejné
	public Board withTiles(TileAt ...ats) {
		Board newBoard = new Board(dimension);
		for (int i = 0; i < newBoard.dimension; i++){
			newBoard.tile[i] = tile[i].clone();
		}
		for (TileAt tile: ats) {
			newBoard.tile[tile.pos.i()][tile.pos.j()] = tile.tile;
		}
		return newBoard;
	}

	// Vytvoří instanci PositionFactory pro výrobu pozic na tomto hracím plánu
	public PositionFactory positionFactory() {
		return new PositionFactory(dimension);
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

