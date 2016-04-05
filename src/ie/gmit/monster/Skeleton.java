package ie.gmit.monster;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;

public class Skeleton extends TilePiece implements Fightable {

	private int health;
	private boolean isDead = false;
	private int attack = 7;
	private List<BufferedImage> allFrames;
	private BufferedImage imageFrame1;
	private BufferedImage imageFrame2;
	private BufferedImage imageFrame3;
	private int imagePosition = 0;
	
	public Skeleton(TileType tileType, int x, int z) {
		super(tileType, x, z);
		
		try 
		{
			imageFrame1 = ImageIO.read(new java.io.File("Images/enemy1_1.png"));
			imageFrame2 = ImageIO.read(new java.io.File("Images/enemy1_2.png"));
			imageFrame3 = ImageIO.read(new java.io.File("Images/enemy1_3.png"));
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

}
