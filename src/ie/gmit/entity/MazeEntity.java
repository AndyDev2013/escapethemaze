package ie.gmit.entity;

import java.util.ArrayList;
import java.util.List;

import ie.gmit.ai.GlobalsVars;
import ie.gmit.ai.Maze;
import ie.gmit.maze.Node;
import ie.gmit.maze.Node.Direction;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class MazeEntity {
	
	private TilePiece tilePiece;
	private int x,z;
	boolean isWall = false;
	boolean isGoalNode = false;
	boolean isVisted = false;
	private int distance;
	private Direction[] paths = null;

	private MazeEntity parent;	
	
	public MazeEntity(int x,int z, TilePiece tile)
	{
		this.x = x;
		this.z = z;
		this.tilePiece = tile;
	}
	
	public int getHeuristic(){
		double x1 = this.getX();
		double y1 = this.getZ();
		double x2 = GlobalsVars.GoalNode.getX();
		double y2 = GlobalsVars.GoalNode.getZ();
		return (int) Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
	}
	
	public boolean hasDirection(Direction direction)
	{	
		for (int i = 0; i < paths.length; i++) 
		{
			if (paths[i] == direction) return true;
		}
		return false;
	}
	
	public MazeEntity[] children(Maze maze){		
		List<MazeEntity> children = new ArrayList<MazeEntity>();
				
		if (x > 0 && maze.getMazeEntity(x - 1,z).hasDirection(Direction.South))
		{
			children.add(maze.getMazeEntity(x - 1,z)); //Add North
		}
		
		if (x < maze.getElementCount() - 1 && maze.getMazeEntity(x + 1,z).hasDirection(Direction.North))
		{
			children.add(maze.getMazeEntity(x + 1,z)); //Add South
		}
		
		if (z > 0 && maze.getMazeEntity(x,z - 1).hasDirection(Direction.East))
		{
			children.add(maze.getMazeEntity(x,z - 1)); //Add West
		}
		
		if (z <maze.getElementCount() - 1 && maze.getMazeEntity(x,z + 1).hasDirection(Direction.West)) 
		{
			children.add(maze.getMazeEntity(x,z + 1)); //Add East
		}
		
		return (MazeEntity[]) children.toArray(new MazeEntity[children.size()]);
	}

	public void addPath(Direction direction) 
	{
		int index = 0;
		
		if (paths == null)
		{
			paths = new Direction[index + 1];		
		}
		else
		{
			index = paths.length;
			Direction[] temp = new Direction[index + 1];
			for (int i = 0; i < paths.length; i++) temp[i] = paths[i];
			paths = temp;
		}
		
		paths[index] = direction;
	}
	
	public MazeEntity[] adjacentNodes(Maze maze)
	{
		List<MazeEntity> adjacents = new ArrayList<MazeEntity>();
		
		if (x > 0) 
		{
			adjacents.add(maze.getMazeEntity(x - 1,z)); //Add North
		}
		
		
		if (x < GlobalsVars.MAZE_DIMENSION - 1) 
		{
			adjacents.add(maze.getMazeEntity(x + 1,z)); //Add South
		}
		
		
		if (z > 0)
		{
			adjacents.add(maze.getMazeEntity(x,z - 1)); //Add West
		}
		
		
		if (z < GlobalsVars.MAZE_DIMENSION - 1) 
		{
			adjacents.add(maze.getMazeEntity(x,z + 1)); //Add East
		}		
		
		return (MazeEntity[]) adjacents.toArray(new MazeEntity[adjacents.size()]);
	}	
	
	public MazeEntity getParent() {
		return this.parent;
	}

	public void setParent(MazeEntity parent) {
		this.parent = parent;
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
	
	public boolean isGoalNode()
	{
		return this.isGoalNode;
	}
	
	public void setGoalNode(boolean flag)
	{
		GlobalsVars.GoalNode = this;
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
		
	public Direction[] getPaths() {
		return paths;
	}
	
	public void setWall(boolean wallType)
	{
		this.isWall = wallType;
	}
	
	public boolean isWall()
	{
		return this.isWall;
	}
	
	public int getPathCost() {
		return this.distance;
	}
}
