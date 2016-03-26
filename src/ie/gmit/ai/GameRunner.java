package ie.gmit.ai;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import ie.gmit.entity.MazeEntity;
import ie.gmit.entity.Player;
import ie.gmit.tile.TileType;

public class GameRunner implements KeyListener
{
	private GameView view;
	private Maze maze;
	
	public GameRunner() throws Exception
	{
		maze = new Maze(GlobalsVars.MAZE_DIMENSION, GlobalsVars.MAZE_DIMENSION);
    	view = new GameView(maze);
    	
    	placePlayer();
    	
    	System.out.println(maze.printMaze());
    	
    	setupApplication();
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
		
		int timesHitWall = 0;
		
		while(maze.getMazeEntity(row, col).isWall())
		{
			row = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);
			col = GlobalsVars.RandomNumber(GlobalsVars.MAZE_DIMENSION);	
		
			++timesHitWall;
		}
		
		System.out.println("Times: " + timesHitWall+ " : " + row + " : " + col);
		
		GlobalsVars.player = new Player(row,col);
		
		GlobalsVars.firstPositionPlayer(row,col, maze);
	}

    public void keyPressed(KeyEvent e) 
    { 
    	if (e.getKeyCode() == KeyEvent.VK_Z)
	    {
	    	GlobalsVars.toggleZoom();

	    	view.repaint();
	    }
    	
    	/*
        if (e.getKeyCode() == KeyEvent.VK_RIGHT && GlobalsVars.playerPositionZ < MAZE_DIMENSION - 1) 
        {
        	if (isValidMove(currentRow, currentCol + 1)) currentCol++;   		
        	        	
    		view.repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_LEFT && GlobalsVars.playerPositionZ > 0)
        {
        	if (isValidMove(currentRow, currentCol - 1)) currentCol--;	
        	
        	view.repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_UP && GlobalsVars.playerPositionX > 0)
        {
        	if (isValidMove(currentRow - 1, currentCol)) currentRow--;
        	        	
    		view.repaint();
        }
        else if (e.getKeyCode() == KeyEvent.VK_DOWN && GlobalsVars.playerPositionX < MAZE_DIMENSION - 1) 
        {
        	if (isValidMove(currentRow + 1, currentCol)) currentRow++;   
        	        	     	  	
    		view.repaint();
        }
        else
        {
        	return;
        }               
        */    
    	
    }
    
    public void keyReleased(KeyEvent e) {} //Ignore
	public void keyTyped(KeyEvent e) {} //Ignore
}