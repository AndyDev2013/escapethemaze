package ie.gmit.traverser;

import java.util.*;

import ie.gmit.ai.GlobalsVars;
import ie.gmit.ai.Maze;
import ie.gmit.entity.MazeEntity;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class BeamTraversator implements Traversator{
	private MazeEntity goal;
	private int beamWidth= 1; 
	
	public BeamTraversator(MazeEntity goal,  int beamWidth){
		this.goal = goal;
		this.beamWidth = beamWidth;
	}
	
	public void traverse(Maze maze, MazeEntity node) {
		
		maze.unvistMaze();
		
		LinkedList<MazeEntity> queue = new LinkedList<MazeEntity>();
		queue.addFirst(node);
    	
		while(!queue.isEmpty()){
			node = queue.poll();
			node.setVisited(true);	
			
			if (node.isGoalNode()){
				System.out.println("LOL");
				break;
			}
			
			MazeEntity[] children = node.children(maze);
			Collections.sort(Arrays.asList(children),(MazeEntity current, MazeEntity next) -> current.getHeuristic(goal) - next.getHeuristic(goal));
			
			int bound = 0;
			if (children.length < beamWidth){
				bound = children.length;
			}else{
				bound = beamWidth;
			}
			
			for (int i = 0; i < bound; i++) {
				if (children[i] != null && !children[i].isVisited()){
					children[i].setParent(node);
					queue.addFirst(children[i]);
				}
			}
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
