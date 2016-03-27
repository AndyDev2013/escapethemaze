package ie.gmit.ai;

import ie.gmit.entity.MazeEntity;
import ie.gmit.maze.GeneratorAlgorithm;
import ie.gmit.maze.MazeGenerator;
import ie.gmit.maze.MazeGeneratorFactory;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class Maze {
	
	private MazeEntity[][] maze;
	
	public Maze(GeneratorAlgorithm algorithm,int rows, int cols){
		
		maze = new MazeEntity[rows][cols];
		
		initMaze();
		
		buildMaze(algorithm);

		/*
		int featureNumber = (int)((rows * cols) * 0.01);
		addFeature('W', 'X', featureNumber);
		addFeature('?', 'X', featureNumber);
		addFeature('B', 'X', featureNumber);
		addFeature('H', 'X', featureNumber);
		*/
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
	
	/*
	 *   private char[][] maze;
		public Maze(int rows, int cols){
			maze = new char[rows][cols];
			init();
			buildMaze();
			
			int featureNumber = (int)((rows * cols) * 0.01);
			addFeature('W', 'X', featureNumber);
			addFeature('?', 'X', featureNumber);
			addFeature('B', 'X', featureNumber);
			addFeature('H', 'X', featureNumber);
		}
		
		private void addFeature(char feature, char replace, int number){
			int counter = 0;
			while (counter < feature){
				int row = (int) (maze.length * Math.random());
				int col = (int) (maze[0].length * Math.random());
				
				if (maze[row][col] == replace){
					maze[row][col] = feature;
					counter++;
				}
			}
		}
		}*/
}
