package ie.gmit.ai;

import java.util.Random;

import ie.gmit.entity.Player;

public class GlobalsVars {

	static Random rand = new Random();
	
	// Global Variables

	public static final int drawCols = 5;
	public static final int drawRows = 4;
	
	public static final String title = "G00237144 - Andrew Sweeney - AI Project 0.1";
	public static final int MAZE_DIMENSION = 60;
	public static final int DEFAULT_VIEW_SIZE = 800;
	public static final int INFO_PANEL_SIZE = DEFAULT_VIEW_SIZE - (GlobalsVars.ImageSize * GlobalsVars.drawRows);
	public static final int ImageSize = 160;
	public static boolean zoomOut = false;
	
	public static double SMALL_IMAGE_SIZE_WIDTH = (double)GlobalsVars.DEFAULT_VIEW_SIZE / (double)GlobalsVars.MAZE_DIMENSION;
	public static double SMALL_IMAGE_SIZE_HEIGHT = (double)(GlobalsVars.DEFAULT_VIEW_SIZE - (double)GlobalsVars.INFO_PANEL_SIZE) / (double)GlobalsVars.MAZE_DIMENSION;
	
	
	// Global Player Variables
	
	public static int playerPositionX;
	public static int playerPositionZ;	
	public static Player player;

	public static int TurnCount = 0;
	
	// Player Tracking
	
	public static void firstPositionPlayer(int x,int z, Maze maze)
	{	
		player.setX(x);
		player.setZ(z);
		playerPositionX = x;
		playerPositionZ = z;		
		maze.getMazeEntity(x, z).setTilepiece(player);
	}
	
	public static void updatePlayer(int oldX,int oldZ,int x,int z, Maze maze)
	{				
		maze.getMazeEntity(x, z).setTilepiece(player);
		player.setX(x);
		player.setZ(z);
		playerPositionX = x;
		playerPositionZ = z;
	}
	
	public static void toggleZoom()
	{
		zoomOut = !zoomOut;		
	}
	
	// Random Numbers
	
	public static int RandomNumber(int max)
	{
		return rand.nextInt(max);
	}	
	
	// Singleton Class Stuff
	
	private static GlobalsVars instance = null;
	
	protected GlobalsVars() {}
   
    public static GlobalsVars getInstance() 
    {
       if(instance == null) 
       {
          instance = new GlobalsVars();
       }
       
       return instance;
   }	
}
