package thedrake;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class BoardTroops {
	private final PlayingSide playingSide;
	private final Map<BoardPos, TroopTile> troopMap;
	private final TilePos leaderPosition;
	private final int guards;
	
	public BoardTroops(PlayingSide playingSide) { 
		// Místo pro váš kód
	}
	
	public BoardTroops(
			PlayingSide playingSide,
			Map<BoardPos, TroopTile> troopMap,
			TilePos leaderPosition, 
			int guards) {
		// Místo pro váš kód
	}

	public Optional<TroopTile> at(TilePos pos) {
		// Místo pro váš kód
	}
	
	public PlayingSide playingSide() {
		// Místo pro váš kód
	}
	
	public TilePos leaderPosition() {
		// Místo pro váš kód
	}

	public int guards() {
		// Místo pro váš kód
	}
	
	public boolean isLeaderPlaced() {
		// Místo pro váš kód
	}
	
	public boolean isPlacingGuards() {
		// Místo pro váš kód
	}	
	
	public Set<BoardPos> troopPositions() {
		// Místo pro váš kód
	}

	public BoardTroops placeTroop(Troop troop, BoardPos target) {
		// Místo pro váš kód	
	}
	
	public BoardTroops troopStep(BoardPos origin, BoardPos target) {
		// Místo pro váš kód
	}
	
	public BoardTroops troopFlip(BoardPos origin) {
		if(!isLeaderPlaced()) {
			throw new IllegalStateException(
					"Cannot move troops before the leader is placed.");			
		}
		
		if(isPlacingGuards()) {
			throw new IllegalStateException(
					"Cannot move troops before guards are placed.");			
		}
		
		if(!at(origin).isPresent())
			throw new IllegalArgumentException();
		
		Map<BoardPos, TroopTile> newTroops = new HashMap<>(troopMap);
		TroopTile tile = newTroops.remove(origin);
		newTroops.put(origin, tile.flipped());

		return new BoardTroops(playingSide(), newTroops, leaderPosition, guards);
	}
	
	public BoardTroops removeTroop(BoardPos target) {
		// Místo pro váš kód
	}	
}
