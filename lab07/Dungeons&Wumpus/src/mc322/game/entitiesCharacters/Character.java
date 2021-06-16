package mc322.game.entitiesCharacters;

import mc322.game.Entity;
import mc322.game.Room;

public abstract class Character extends Entity{

	public Character(int i,int j,double elevation)
	{
		this.i = i;
		this.j = j;
		this.elevation = elevation;
		this.legSize = 0.7;
	}
	protected double legSize;
	
	protected int health;
	
	protected String name;
	
	public abstract void atack(int i,int j);

	public abstract void move(int i, int j, Room room);
	
	public abstract void move(char dir, Room room);
	
	public abstract void die();
	
	public abstract void hurt(int damage);
	
	public String toString()
	{
		return this.name;
	}
	
	protected abstract boolean verifyMovement(int i, int j, Room room);
}