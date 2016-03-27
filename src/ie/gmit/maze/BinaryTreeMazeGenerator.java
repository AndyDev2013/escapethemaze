package ie.gmit.maze;

import ie.gmit.entity.MazeEntity;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class BinaryTreeMazeGenerator implements MazeGenerator
{		
	public MazeEntity[][] generateMaze(MazeEntity[][] maze)
	{
		for (int row = 0; row < maze.length; row++)
		{
			for (int col = 0; col < maze[row].length; col++)
			{
				int num = (int) (Math.random() * 10);
				
				if (col > 0 && (row == 0 || num >= 5))
				{
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
			}
		}
		
		return maze;
	}
}