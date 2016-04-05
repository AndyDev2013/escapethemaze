package ie.gmit.traverser;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import ie.gmit.ai.Maze;
import ie.gmit.entity.MazeEntity;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class AStarTraversator implements Traversator
{
	private MazeEntity goal;
	
	public AStarTraversator(MazeEntity goal)
	{		
		this.goal = goal;
		
	}// constructor
	
	public void traverse(Maze maze, MazeEntity node) 
	{    			
		PriorityQueue<MazeEntity> open = new PriorityQueue<MazeEntity>(20, (MazeEntity current, MazeEntity next)-> (current.getPathCost() + current.getHeuristic(goal)) - (next.getPathCost() + next.getHeuristic(goal)));
		List<MazeEntity> closed = new ArrayList<MazeEntity>();
    	   	
		open.offer(node);
		node.setPathCost(0);
		
		while(!open.isEmpty())
		{
			node = open.poll();		
			closed.add(node);
			node.setVisited(true);	
			
			if (node.isGoalNode()){
				break;
			}
						
			MazeEntity[] children = node.children(maze);
			
			for (int i = 0; i < children.length; i++) 
			{
				MazeEntity child = children[i];
				int score = node.getPathCost() + 1 + child.getHeuristic(goal);
				int existing = child.getPathCost() + child.getHeuristic(goal);
				
				if ((open.contains(child) || closed.contains(child)) && existing < score)
				{
					continue;
				}
				else
				{
					open.remove(child);
					closed.remove(child);
					child.setParent(node);
					child.setPathCost(node.getPathCost() + 1);
					open.add(child);
				}
			}									
		}

		int a = 0;
		
		while(a < 10 && a < closed.size())
		{		
			closed.get(a).setTilepiece(new TilePiece(TileType.HINT, closed.get(a).getX(), closed.get(a).getX()));
			closed.get(a).setWall(false);
			a++;
		}
		
	}//traverse
	
}//class
