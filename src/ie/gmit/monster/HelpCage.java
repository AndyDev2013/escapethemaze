package ie.gmit.monster;

import ie.gmit.ai.GlobalsVars;
import ie.gmit.ai.Maze;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;
import ie.gmit.traverser.AStarTraversator;
import ie.gmit.traverser.BeamTraversator;
import ie.gmit.traverser.BestFirstTraversator;
import ie.gmit.traverser.RandomWalk;
import ie.gmit.traverser.Traversator;

public class HelpCage extends TilePiece {

	private Traversator t;
	
	public HelpCage(TileType tileType, int x, int z)
	{
		super(tileType, x, z);
	        
		//t = new BestFirstTraversator(GlobalsVars.GoalNode);
		//t = new AStarTraversator(GlobalsVars.GoalNode);
		t = new BeamTraversator(GlobalsVars.GoalNode, 2);
	}
	
	public void ShowPathToDoor(Maze maze)
	{		
		GlobalsVars.HINTS_TIME = 0;
		
		t.traverse(maze, maze.getMazeEntity(getX(),getZ()));
	}

}
