package ie.gmit.ai;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import ie.gmit.entity.Player;
import ie.gmit.food.Food;
import ie.gmit.maze.GeneratorAlgorithm;
import ie.gmit.tile.TileType;

public class GameRunner implements KeyListener
{
	private GameView view;
	private Maze maze;
	
	public GameRunner(GeneratorAlgorithm algorithm) throws Exception
	{
		maze = new Maze(algorithm,GlobalsVars.MAZE_DIMENSION, GlobalsVars.MAZE_DIMENSION);

    	view = new GameView(maze);
    	
    	placePlayer();
    	
    	//System.out.println(maze.printMaze());
    	
    	setupApplication();
    	
		GlobalsVars.startTime = System.currentTimeMillis();	
	}
	
	private void setupApplication()
	{
    	Dimension d = new Dimension(GlobalsVars.DEFAULT_VIEW_SIZE, GlobalsVars.DEFAULT_VIEW_SIZE);
    	view.setPreferredSize(d);
    	view.setMinimumSize(d);
    	view.setMaximumSize(d);
    	
    	JFrame f = new JFrame(GlobalsVars.title);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.addKeyListener(this);
        f.getContentPane().setLayout(new FlowLayout());
        f.add(view);
        f.setSize(1000,1000);
        f.setLocation(100,100);
        f.pack();
        f.setResizable(false);
        f.setVisible(true);
	}
	
	private void placePlayer()
	{   	
		int row = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);
		int col = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);
		
		while(maze.getMazeEntity(row, col).isWall())
		{
			row = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);
			col = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);
		}
		
		GlobalsVars.player = new Player(GlobalsVars.PLAYER_BASE_HEALTH,row,col);
		
		GlobalsVars.firstPositionPlayer(row,col, maze);
	}

    public void keyPressed(KeyEvent e) 
    { 
    	if (e.getKeyCode() == KeyEvent.VK_Z)
	    {
	    	GlobalsVars.toggleZoom();

	    	view.repaint();
	    }
    	
        if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D && GlobalsVars.playerPositionZ < GlobalsVars.MAZE_DIMENSION - 1) 
        {    		
    		if(isExit(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ + 1))
    			GlobalsVars.WON_GAME = true;
    		
        	if (isValidMove(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ + 1))
        	{
        		if (isSomethingPickupable(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ + 1) == TileType.FOOD)
        		{
        			GlobalsVars.player.pickupFood(maze.getMaze()[GlobalsVars.playerPositionX][GlobalsVars.playerPositionZ + 1].getTilePiece());
        		}
        		else if(isSomethingPickupable(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ + 1) == TileType.WEAPON)
        		{
        			GlobalsVars.player.pickupWeapon(maze.getMaze()[GlobalsVars.playerPositionX][GlobalsVars.playerPositionZ + 1].getTilePiece());
        		}
        		
        		GlobalsVars.updatePlayer(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ, GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ + 1, maze);  		
        	        	        		
        		if(GlobalsVars.TurnCount % 20 == 0)
        		{
        			GlobalsVars.player.feelHunger();
        		}
        		
        		GlobalsVars.TurnCount++;
        		view.repaint();
        	}
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A && GlobalsVars.playerPositionZ > 0)
        {         		
    		if(isExit(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ - 1))
    			GlobalsVars.WON_GAME = true;
        	
        	if (isValidMove(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ - 1))
        	{
            	if(isSomethingPickupable(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ - 1) == TileType.FOOD)
            	{
            		GlobalsVars.player.pickupFood(maze.getMaze()[GlobalsVars.playerPositionX][GlobalsVars.playerPositionZ - 1].getTilePiece());
            	}
        		else if(isSomethingPickupable(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ - 1) == TileType.WEAPON)
        		{
        			GlobalsVars.player.pickupWeapon(maze.getMaze()[GlobalsVars.playerPositionX][GlobalsVars.playerPositionZ - 1].getTilePiece());
        		}				
        		
        		GlobalsVars.updatePlayer(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ, GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ - 1, maze);  		
	        	        		
        		if(GlobalsVars.TurnCount % 20 == 0)
        		{
        			GlobalsVars.player.feelHunger();
        		}
        		
        		GlobalsVars.TurnCount++;
        		view.repaint();
        	}
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W && GlobalsVars.playerPositionX > 0)
        {     
    		if(isExit(GlobalsVars.playerPositionX - 1, GlobalsVars.playerPositionZ))
    			GlobalsVars.WON_GAME = true;
        	
        	if (isValidMove(GlobalsVars.playerPositionX - 1, GlobalsVars.playerPositionZ)) 
        	{
            	if(isSomethingPickupable(GlobalsVars.playerPositionX - 1, GlobalsVars.playerPositionZ) == TileType.FOOD)
            	{
            		GlobalsVars.player.pickupFood(maze.getMaze()[GlobalsVars.playerPositionX - 1][GlobalsVars.playerPositionZ].getTilePiece());
            	}
        		else if(isSomethingPickupable(GlobalsVars.playerPositionX - 1, GlobalsVars.playerPositionZ) == TileType.WEAPON)
        		{
        			GlobalsVars.player.pickupWeapon(maze.getMaze()[GlobalsVars.playerPositionX - 1][GlobalsVars.playerPositionZ].getTilePiece());
        		}	
        		
        		GlobalsVars.updatePlayer(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ, GlobalsVars.playerPositionX - 1, GlobalsVars.playerPositionZ, maze);
        		        		
        		if(GlobalsVars.TurnCount % 20 == 0)
        		{
        			GlobalsVars.player.feelHunger();
        		}      		
       		
        		GlobalsVars.TurnCount++;
        		
        		view.repaint();	
        	}
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S && GlobalsVars.playerPositionX < GlobalsVars.MAZE_DIMENSION  - 1) 
        {        
    		if(isExit(GlobalsVars.playerPositionX + 1, GlobalsVars.playerPositionZ))
    			GlobalsVars.WON_GAME = true;
        	
        	if (isValidMove(GlobalsVars.playerPositionX + 1, GlobalsVars.playerPositionZ)) 
        	{
            	if(isSomethingPickupable(GlobalsVars.playerPositionX + 1, GlobalsVars.playerPositionZ) == TileType.FOOD) 
            	{
            		GlobalsVars.player.pickupFood(maze.getMaze()[GlobalsVars.playerPositionX + 1][GlobalsVars.playerPositionZ].getTilePiece());
            	}
        		else if(isSomethingPickupable(GlobalsVars.playerPositionX + 1, GlobalsVars.playerPositionZ) == TileType.WEAPON)
        		{
        			GlobalsVars.player.pickupWeapon(maze.getMaze()[GlobalsVars.playerPositionX + 1][GlobalsVars.playerPositionZ].getTilePiece());
        		}	
        		
        		GlobalsVars.updatePlayer(GlobalsVars.playerPositionX, GlobalsVars.playerPositionZ, GlobalsVars.playerPositionX + 1, GlobalsVars.playerPositionZ, maze);
        		        		
        		if(GlobalsVars.TurnCount % 20 == 0)
        		{
        			GlobalsVars.player.feelHunger();
        		}        		
        		
        		GlobalsVars.TurnCount++;

        		view.repaint();	
        	}
        }
        else if(e.getKeyCode() == KeyEvent.VK_H) 
        {
        	GlobalsVars.toggleHelp();
        	view.repaint();
        }
        else if(e.getKeyCode() == KeyEvent.VK_E)
        {
        	GlobalsVars.player.eatCurrentFood();
        	view.repaint();
        }
        else if(e.getKeyCode() == KeyEvent.VK_SLASH) 
        {
        	GlobalsVars.player.cycleFood();
        	view.repaint();
        }
        else if(e.getKeyCode() == KeyEvent.VK_PERIOD) 
        {
        	GlobalsVars.player.cycleWeapons();
        	view.repaint();
        }
        else
        {
        	return;
        }     
    	
    }
    
	private boolean isValidMove(int r, int c)
	{
		if (r <= GlobalsVars.MAZE_DIMENSION - 1 && c <= maze.getMaze()[r].length - 1 && !maze.getMaze()[r][c].isWall())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	private boolean isExit(int r,int c)
	{
		try
		{		
			if (r <= GlobalsVars.MAZE_DIMENSION - 1 && c <= maze.getMaze()[r].length - 1 && maze.getMaze()[r][c].isGoalNode())
			{
				return true;
			}
			else
				return false;
		}catch(Exception e ){e.toString();}
		
		return false;
	}
	
	private TileType isSomethingPickupable(int r, int c)
	{
		if (r <= GlobalsVars.MAZE_DIMENSION - 1 && c <= maze.getMaze()[r].length - 1 && maze.getMaze()[r][c].containsType(TileType.FOOD))
		{
			return TileType.FOOD;
		}
		else if (r <= GlobalsVars.MAZE_DIMENSION - 1 && c <= maze.getMaze()[r].length - 1 && maze.getMaze()[r][c].containsType(TileType.WEAPON))
		{
			return TileType.WEAPON;
		}
		else
		{
			return TileType.DEBUG;
		}
	}
    
    public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {}
}