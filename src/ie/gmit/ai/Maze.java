package ie.gmit.ai;

import ie.gmit.entity.MazeEntity;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class Maze {
	
	private MazeEntity[][] maze;
	
	//private int currentRow;
	//private int currentCol;
	
	public Maze(int rows, int cols){
		
		maze = new MazeEntity[rows][cols];
		
		initMaze();
		
		buildMaze();

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
	
	private void buildMaze()
	{
		for (int row = 0; row < maze.length; row++)
		{
			for (int col = 0; col < maze[row].length; col++)
			{
				int num = (int) (Math.random() * 10);
				
				if (col > 0 && (row == 0 || num >= 5))
				{
					//maze[row][col] = new MazeEntity(row, col, new TilePiece(TileType.FLOOR, row, col));
					//maze[row][col].setWall(false);
					
					try
					{
						if(maze[row][col - 1] != null)
						{
							maze[row][col - 1] = new MazeEntity(row, col, new TilePiece(TileType.FLOOR, row, col));
							maze[row][col - 1].setWall(false);							
						}
					}
					catch(Exception e){e.toString();}
				}
				else
				{
					//maze[row][col] = new MazeEntity(row, col, new TilePiece(TileType.FLOOR, row, col));
					//maze[row][col].setWall(false);
					
					try
					{
						if(maze[row - 1][col] != null)
						{
							maze[row - 1][col] = new MazeEntity(row, col, new TilePiece(TileType.FLOOR, row, col));
							maze[row - 1][col].setWall(false);							
						}
					}
					catch(Exception e){e.toString();}
				}	
				
				/*
				if (col > 0 && (row == 0 || num >= 5))
				{
					maze[row][col].addPath(Node.Direction.West);
				}
				else
				{
					maze[row][col].addPath(Node.Direction.North);	
				}	
				*/
			}
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
