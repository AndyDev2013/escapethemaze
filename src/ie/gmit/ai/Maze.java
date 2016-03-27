package ie.gmit.ai;

import ie.gmit.entity.MazeEntity;
import ie.gmit.food.Apple;
import ie.gmit.food.ChickenLeg;
import ie.gmit.food.Food;
import ie.gmit.maze.GeneratorAlgorithm;
import ie.gmit.maze.MazeGeneratorFactory;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class Maze {
	
	private MazeEntity[][] maze;
	
	public Maze(GeneratorAlgorithm algorithm,int rows, int cols){
		
		maze = new MazeEntity[rows][cols];
		
		initMaze();
		
		buildMaze(algorithm);

		placeFood(20);
	}
	
	public void initMaze()
	{
		for (int row = 0; row < GlobalsVars.MAZE_DIMENSION; row++)
		{
			for (int col = 0; col < GlobalsVars.MAZE_DIMENSION; col++)
			{
				maze[row][col] = new MazeEntity(row, col, new TilePiece(TileType.WALL, row, col));
				maze[row][col].setWall(true);
			}
		}
	}
	
	private void buildMaze(GeneratorAlgorithm algorithm)
	{
		maze = MazeGeneratorFactory.getInstance().getMazeGenerator(algorithm).generateMaze(maze);
	}	
	
	private void placeFood(int foodCount)
	{
		int row = 0;
		int col = 0;		
		int count = 0;
		int tooLong = 0;
		final int LIMIT = 200;		
		
		while(count < foodCount && tooLong < LIMIT)
		{		
			while(getMazeEntity(row, col).isWall())
			{
				row = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);
				col = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);	
				
				++tooLong;
			}
			
			int x = GlobalsVars.RandomNumber(2);	
			
			Food food;
			
			if(x == 0)
				food = new Apple(row, col);
			else
				food = new ChickenLeg(row, col);				
			
			getMazeEntity(row, col).setTilepiece(food);		
			getMazeEntity(row, col).setWall(false);
			
			row = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);
			col = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);
			
			++count;
		}
	}
	
	public MazeEntity getMazeEntity(int x,int z)
	{
		try
		{
			return this.maze[x][z];
		}
		catch(Exception e)
		{
			e.toString();
			return null;
		}
	}
	
	public MazeEntity[][] getMaze()
	{
		return this.maze;
	}
	
	public String printMaze()
	{
		StringBuffer sb = new StringBuffer();
		
		for (int row = 0; row < GlobalsVars.MAZE_DIMENSION; row++)
		{
			for (int col = 0; col < GlobalsVars.MAZE_DIMENSION; col++)
			{
				String ch = ".";
				
				if(maze[row][col].isWall())
					ch = "|";
				
				if(maze[row][col].containsType(TileType.PLAYER))
					ch = "@";
				
				sb.append(ch);
				if (col < maze[row].length - 1) sb.append(",");
			}
			
			sb.append("\n");
		}
		
		return sb.toString();
	}
}
