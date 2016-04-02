package ie.gmit.monster;

import ie.gmit.ai.Maze;

public interface Decideable {
	public void moveSentient();
	public void updateSentient(int oldX,int oldZ,int x,int z, Maze maze);
}