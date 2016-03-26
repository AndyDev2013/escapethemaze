package ie.gmit.entity;

import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class Player extends TilePiece{

	public Player(int x, int z) {
		super(TileType.PLAYER, x, z);
	}
	
	public String getHungryStatus()
	{
		return "Current Hungry Status";
	}
	
}
