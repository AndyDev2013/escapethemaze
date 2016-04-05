package ie.gmit.traverser;

import ie.gmit.ai.Maze;
import ie.gmit.entity.MazeEntity;

public interface Traversator {
	public void traverse(Maze maze, MazeEntity start);
}
