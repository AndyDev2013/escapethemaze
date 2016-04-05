package ie.gmit.monster;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import ie.gmit.ai.Maze;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class FireArcher extends TilePiece implements Fightable,Decideable {

	private int health;
	private boolean isDead = false;
	private int attack = 10;
	private List<BufferedImage> allFrames;
	private BufferedImage imageFrame1;
	private BufferedImage imageFrame2;
	private BufferedImage imageFrame3;
	private int imagePosition = 0;
	
	
	
	public FireArcher(TileType tileType, int x, int z) {
		super(tileType, x, z);
		
		try 
		{
			imageFrame1 = ImageIO.read(new java.io.File("Images/enemy2_1.png"));
			imageFrame2 = ImageIO.read(new java.io.File("Images/enemy2_2.png"));
			imageFrame3 = ImageIO.read(new java.io.File("Images/enemy2_3.png"));
		}
		catch (IOException e){e.toString();}	
		
		allFrames = new ArrayList<BufferedImage>();
		
		if(imageFrame1 != null && imageFrame2 != null && imageFrame3 != null)
		{
			allFrames.add(imageFrame1);
			allFrames.add(imageFrame2);
			allFrames.add(imageFrame3);
		}
	}

	public void deprecateHealth(int val) {
		this.health -= val;		
		
		if(health <= 0)
			makeDead(true);
	}

	public int getAttack() {
		return this.attack;
	}

	public void makeDead(boolean flag) {
		this.isDead = flag;		
	}
	
	public int getHealth(){
		return this.health;
	}

	public boolean isDead() {
		return this.isDead;
	}
	
	public BufferedImage getTileImage()
	{		imagePosition++;
		
		if(imagePosition == allFrames.size())
			imagePosition = 0;
		
		return allFrames.get(imagePosition);
	}

	public void moveSentient() {
		// TODO Auto-generated method stub
		
	}

	public void updateSentient(int oldX, int oldZ, int x, int z, Maze maze) {
		// TODO Auto-generated method stub
		
	}

}
