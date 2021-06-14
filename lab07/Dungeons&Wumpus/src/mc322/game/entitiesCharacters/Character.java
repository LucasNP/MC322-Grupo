package mc322.game.entitiesCharacters;

import mc322.game.Entity;
import mc322.game.FullPlaceException;
import mc322.game.Room;

public abstract class Character extends Entity{

	public Character(int i,int j,int elevation, Room room)
	{
		this.i = i;
		this.j = j;
		this.elevation = elevation;
		this.room = room;
	}
	
	protected int health;
	
	protected Room room;
	
	protected String name;
	
	public abstract void atack(int i,int j);

	public abstract void move(int i, int j);
	
	public abstract void die();
	
	public abstract void hurt(int damage);
	
	public String toString()
	{
		return this.name;
	}
	
	protected abstract boolean verifyMovement(int i, int j);
}
