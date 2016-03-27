package ie.gmit.entity;

import java.util.ArrayList;
import java.util.List;

import ie.gmit.food.Food;
import ie.gmit.tile.TilePiece;
import ie.gmit.tile.TileType;
import ie.gmit.weapon.Weapon;

public class Player extends TilePiece
{
	private int health;
	private int hunger = 100;
	private int currentFoodPosition = 0;
	private int currentWeaponPosition = 0;
	private List<Food> InventoryFood = new ArrayList<Food>();
	private List<Weapon> InventoryWeapons = new ArrayList<Weapon>();
	
	public Player(int health,int x, int z) 
	{
		super(TileType.PLAYER, x, z);
		this.health = health;
	}
	
	public void setHealth(int health)
	{
		this.health = health;
	}
	
	public int getHealth()
	{
		return this.health;
	}
	
	public int getHunger()
	{
		return this.hunger;
	}
	
	public void setHunger(int hunger)
	{
		this.hunger = hunger;
	}
	
	public boolean canEatFood()
	{
		try
		{
			if(InventoryFood.size() > 0)
				if(InventoryFood.get(currentFoodPosition) != null)
					return true;
			
		}
		catch(Exception e){e.toString(); return false;}
		
		return false;
	}
	
	public Food getCurrentFood()
	{
		if(canEatFood())
		{
			return this.InventoryFood.get(currentFoodPosition);
		}
		
		return null;
	}
	
	public Food eatCurrentFood()
	{
		if(canEatFood())
		{
			health += InventoryFood.get(currentFoodPosition).giveHealth();
			InventoryFood.remove(currentFoodPosition);
		}
		
		return null;
	}
	
	public void cycleFood()
	{
		if(currentFoodPosition + 1 > InventoryFood.size())
		{
			currentFoodPosition = 0;
		}
		else
		{
			currentFoodPosition++;
		}
	}
	
	public void pickupFood(TilePiece f)
	{
		Food food = (Food)f;
		
		this.InventoryFood.add(food);
	}
	
	public void pickupWeapon(TilePiece w)
	{
		Weapon weapon = (Weapon)w;
		
		this.InventoryWeapons.add(weapon);
	}
	
	public void cycleWeapons()
	{
		if(currentWeaponPosition + 1 > InventoryWeapons.size())
		{
			currentWeaponPosition = 0;
		}
		else
		{
			currentWeaponPosition++;
		}
	}
	
	public String getHungryStatus()
	{
		return "Current Hungry Status";
	}
}
