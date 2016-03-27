package ie.gmit.entity;

import ie.gmit.maze.Node;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class MazeEntity {
	
	private TilePiece tilePiece;
	private int x,z;
	boolean isWall = false;
	boolean isGoalNode = false;
	boolean isVisted = false;
	private Node node;
	
	public MazeEntity(int x,int z, TilePiece tile)
	{
		this.x = x;
		this.z = z;
		this.tilePiece = tile;
	}
	
	public boolean containsType(TileType tileType)
	{
		if(tilePiece.getTileType() == tileType)
			return true;
		
		return false;
	}
	
	public void setTilepiece(TilePiece piece)
	{
		this.tilePiece = piece;
	}
	
	public void setGoalNode(boolean flag)
	{
		this.isGoalNode = flag;
	}
	
	public void visitedNode()
	{
		isVisted = true;
	}
	
	public boolean isVisited()
	{
		return this.isVisted;
	}
	
	public TilePiece getTilePiece()
	{
		return this.tilePiece;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getZ()
	{
		return this.z;
	}	
	
	public Node getNode()
	{
		return this.node;
	}
	
	public void setNode(Node node)
	{
		this.node = node;
	}
	
	public void setWall(boolean wallType)
	{
		this.isWall = wallType;
	}
	
	public boolean isWall()
	{
		return isWall;
	}
}
