package ie.gmit.ai;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import ie.gmit.tile.TileType;

public class GameView extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;	
		
	//	

	private Maze maze;	
	private Timer timer;
	private Font font;
	
	private double smallImageSizeWidth = GlobalsVars.SMALL_IMAGE_SIZE_WIDTH;
	private double smallImageSizeHeight = GlobalsVars.SMALL_IMAGE_SIZE_HEIGHT;
	
	//
		
	public GameView(Maze maze) throws Exception
	{
		this.maze = maze;
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		timer = new Timer(300, this);
		timer.start();

        font = new Font("Times New Roman", Font.PLAIN, 25); 
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
		BufferedImage currentImage; 
        g2.setFont(font);      
		
        g2.drawRect(0, 0, GlobalsVars.DEFAULT_VIEW_SIZE, GlobalsVars.DEFAULT_VIEW_SIZE);
        
        int rowMult = 0;
        int colMult = 0;
        
        //System.out.println(GlobalsVars.playerPositionX + " : " + GlobalsVars.playerPositionZ);
        
        if(!GlobalsVars.zoomOut)
        {        
	        for(int row = GlobalsVars.playerPositionX - 2; row <= GlobalsVars.playerPositionX + 1; row++) 
	        {
	        	for (int col = GlobalsVars.playerPositionZ - 2; col <= GlobalsVars.playerPositionZ + 2; col++)
	        	{         		        		
	        		try
	        		{        	        		
	        			currentImage = maze.getMaze()[row][col].getTilePiece().getTileImage();    
	        			g2.drawImage(currentImage, colMult, rowMult, GlobalsVars.ImageSize, GlobalsVars.ImageSize, null);
	        		}
	        		catch(Exception e)
	        		{
	        			e.toString(); 
	        			
        				g2.setColor(Color.BLACK);
				           
        				Rectangle2D r2d = new Rectangle2D.Float((float)colMult, (float)rowMult, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
        			    g2.fill(r2d);
        				g2.draw(r2d);
	        		} 
	        		
	        		colMult += GlobalsVars.ImageSize;
	        	}
	        	
	        	rowMult += GlobalsVars.ImageSize;
	        	colMult = 0;
	        }
        }
        else
        {

        	double rowMultf = 0;
        	double colMultf = 0;
        	        	
        	for(int row = 0; row < GlobalsVars.MAZE_DIMENSION; row++) 
            {
            	for (int col = 0; col < GlobalsVars.MAZE_DIMENSION; col++)
            	{         		        		
            		try
            		{     	           			            			
            			if(maze.getMaze()[row][col].isWall())
            			{
            				g2.setColor(Color.BLACK);
            				           
            				Rectangle2D r2d = new Rectangle2D.Float((float)colMultf, (float)rowMultf, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
            			    g2.fill(r2d);
            				g2.draw(r2d);
            			}
            			else if(maze.getMaze()[row][col].containsType(TileType.PLAYER))
            			{            				
            				g2.setColor(Color.GREEN);
            				
            				Rectangle2D r2d = new Rectangle2D.Float((float)colMultf, (float)rowMultf, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
            			    g2.fill(r2d);
            				g2.draw(r2d);          				
            			}
            			else if(maze.getMaze()[row][col].containsType(TileType.FOOD))
            			{
            				g2.setColor(Color.YELLOW);
            				
            				Rectangle2D r2d = new Rectangle2D.Float((float)colMultf, (float)rowMultf, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
            			    g2.fill(r2d);
            				g2.draw(r2d);          				
            			}
            			else
            			{
            				g2.setColor(Color.LIGHT_GRAY);
            				Rectangle2D r2d = new Rectangle2D.Float((float)colMultf, (float)rowMultf, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
            			    g2.fill(r2d);
            				g2.draw(r2d);
            			}
            			
            			//g2.drawImage(currentImage, colMult, rowMult, smallImageSizeWidth, smallImageSizeHeight, null);
            		}
            		catch(Exception e)
            		{
            			e.toString(); 
        			
        				g2.setColor(Color.PINK);
        				
        				Rectangle2D r2d = new Rectangle2D.Float((float)colMultf, (float)rowMultf, (float)smallImageSizeWidth, (float)smallImageSizeHeight);
        			    g2.fill(r2d);
        				g2.draw(r2d);
            		} 
            		
            		colMultf += smallImageSizeWidth;
            	}
            	
            	rowMultf += smallImageSizeHeight;
            	colMultf = 0;
            }
        }
        
        /// Drawing GUI
        
        try
		{
			currentImage = ImageIO.read(new java.io.File("Images/texture.png"));
			
			g2.drawImage(currentImage, 0, GlobalsVars.ImageSize * GlobalsVars.drawRows, GlobalsVars.ImageSize * GlobalsVars.drawCols, GlobalsVars.ImageSize, null);
		
		}catch (IOException e){e.toString();} 
        
        g2.setColor(Color.white);
        
        // Draw Hungry Status
        
        g2.drawString("Turn Count: " + GlobalsVars.TurnCount, 10, (GlobalsVars.ImageSize * GlobalsVars.drawRows) + 30);
        
        // Draw Turn Count
        
        g2.drawString(GlobalsVars.player.getHungryStatus(), 10, (GlobalsVars.ImageSize * GlobalsVars.drawRows) + 70);
	}

	public void actionPerformed(ActionEvent e)
	{	
		/*
		if (enemy_state < 0 || enemy_state == 5)
		{
			enemy_state = 6;
		}
		else
		{
			enemy_state = 5;
		}
		*/
		
		this.repaint();
	}
	
}