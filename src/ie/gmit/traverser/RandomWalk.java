package ie.gmit.traverser;

import ie.gmit.ai.Maze;
import ie.gmit.entity.MazeEntity;

public class RandomWalk implements Traversator{
	public void traverse(Maze maze, MazeEntity node) {
    	int visitCount = 0;
    	   	
		int steps = (int) Math.pow(maze.getMaze().length, 2) * 2;
		System.out.println("Number of steps allowed: " + steps);
	
		boolean complete = false;
		while(visitCount <= steps && node != null){		
			node.setVisited(true);	
			visitCount++;
			
			if (node.isGoalNode()){
		        complete = true;
				break;
			}
			
			try { //Simulate processing each expanded node
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			//Pick a random adjacent node
			MazeEntity[] children = node.children(maze);
        	node = children[(int)(children.length * Math.random())];		
		}
		
		if (!complete) System.out.println("*** Out of steps....");
	}
}