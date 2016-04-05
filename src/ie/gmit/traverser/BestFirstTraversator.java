package ie.gmit.traverser;

import java.util.Collections;
import java.util.LinkedList;

import ie.gmit.ai.GlobalsVars;
import ie.gmit.ai.Maze;
import ie.gmit.entity.MazeEntity;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class BestFirstTraversator implements Traversator{

	private MazeEntity goal;
	
	public BestFirstTraversator(MazeEntity goal){
		this.goal = goal;
	}
	
	public void traverse(Maze maze, MazeEntity node) {

		maze.unvistMaze();
		
		LinkedList<MazeEntity> queue = new LinkedList<MazeEntity>();
		queue.addFirst(node);
    	
		while(!queue.isEmpty()){
			node = queue.poll();
			node.setVisited(true);	
			
			if (node.isGoalNode()){
				break;
			}
			
			MazeEntity[] children = node.children(maze);
			for (int i = 0; i < children.length; i++) {
				if (children[i] != null && !children[i].isVisited()){
					children[i].setParent(node);
					queue.addFirst(children[i]);
				}
			}
			
			Collections.sort(queue,(MazeEntity current, MazeEntity next) -> current.getHeuristic(goal) - next.getHeuristic(goal));		
		}
		
		for(int row = 0; row < GlobalsVars.MAZE_DIMENSION; row++) 
        {
        	for (int col = 0; col < GlobalsVars.MAZE_DIMENSION; col++)
        	{    
        		MazeEntity me = maze.getMazeEntity(row, col);
        		
        		if(maze.getMazeEntity(row, col).isVisited() && !maze.getMazeEntity(row, col).isGoalNode())
        		{        		
					me.setTilepiece(new TilePiece(TileType.HINT, me.getX(), me.getZ()));
					me.setWall(false);
        		}
        	}
		}
	}
}
