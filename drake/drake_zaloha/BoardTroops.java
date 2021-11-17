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
		this.playingSide = playingSide;
		troopMap = Collections.EMPTY_MAP;
		leaderPosition = TilePos.OFF_BOARD;
		guards = 0;
	}

	public BoardTroops(PlayingSide playingSide,
					   Map<BoardPos, TroopTile> troopMap,
					   TilePos leaderPosition,
					   int guards) {
		this.playingSide = playingSide;
		this.troopMap = troopMap;
		this.leaderPosition = leaderPosition;
		this.guards = guards;
	}

	public Optional<TroopTile> at(TilePos pos) {
		TroopTile tile = troopMap.get(pos);
		return Optional.ofNullable(tile);
	}

	public PlayingSide playingSide() {
		return playingSide;
	}
	
	public TilePos leaderPosition() {
		return leaderPosition;
	}

	public int guards() {
		return guards;
	}
	
	public boolean isLeaderPlaced() {
		return leaderPosition != TilePos.OFF_BOARD;
	}
	
	public boolean isPlacingGuards() {
		return isLeaderPlaced() && guards < 2;
	}	
	
	public Set<BoardPos> troopPositions() {
		return troopMap.keySet();
	}

	public BoardTroops placeTroop(Troop troop, BoardPos target) {
		if(at(target).isPresent()) {
			throw new IllegalStateException(
					"Cannot place troop where one is already.");
		}

		Map<BoardPos, TroopTile> newTroops = new HashMap<>(troopMap);
		TroopTile newTile = new TroopTile(troop, playingSide, TroopFace.AVERS);
		newTroops.put(target, newTile);

		if (!isLeaderPlaced()) //places leader
			return new BoardTroops(playingSide, newTroops, target, guards);
		//places guards
		if(isPlacingGuards())
			return new BoardTroops(playingSide, newTroops, leaderPosition, guards + 1);
		//does not place anything
		return new BoardTroops(playingSide, newTroops, leaderPosition, guards);
	}
	
	public BoardTroops troopStep(BoardPos origin, BoardPos target) {
		if(!isLeaderPlaced()) {
			throw new IllegalStateException(
					"Cannot move troops before the leader is placed.");
		}

		if(isPlacingGuards()) {
			throw new IllegalStateException(
					"Cannot move troops before guards are placed.");
		}

		if(!at(origin).isPresent())
			throw new IllegalArgumentException(
					"Cannot step with troop if it is not present on tile.");

		if(at(target).isPresent())
			throw new IllegalArgumentException(
					"Cannot move to tile that is already occupied by troop.");

		Map<BoardPos, TroopTile> newTroops = new HashMap<>(troopMap);
		TroopTile tile = newTroops.remove(origin);
		newTroops.put(target, tile.flipped());

		if (origin.equals( leaderPosition ))
			return new BoardTroops(playingSide, newTroops, target, guards);

		return new BoardTroops(playingSide, newTroops, leaderPosition, guards);
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
		if(!isLeaderPlaced()) {
			throw new IllegalStateException(
					"Cannot remove troops before the leader is placed.");
		}

		if(isPlacingGuards()) {
			throw new IllegalStateException(
					"Cannot remove troops before guards are placed.");
		}

		if(!at(target).isPresent()) {
			throw new IllegalArgumentException(
					"Cannot remove troops where nothing is placed.");
		}

		Map<BoardPos, TroopTile> newTroops = new HashMap<>(troopMap);
		newTroops.remove(target);
		//removes leader from board
		return new BoardTroops(playingSide(), newTroops,
				target.equals(leaderPosition) ? TilePos.OFF_BOARD : leaderPosition,
				guards);
	}	
}
