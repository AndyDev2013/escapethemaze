package ie.gmit.monster;

import ie.gmit.ai.GlobalsVars;
import ie.gmit.ai.Maze;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;
import ie.gmit.traverser.AStarTraversator;
import ie.gmit.traverser.Traversator;

public class HelpCage extends TilePiece {

	private Traversator t;
	
	public HelpCage(TileType tileType, int x, int z)
	{
		super(tileType, x, z);
	        
		t = new AStarTraversator(GlobalsVars.GoalNode);
	}
	
	public void ShowPathToDoor(Maze maze)
	{		
		t.traverse(maze, maze.getMazeEntity(getX(),getZ()));
	}

}
