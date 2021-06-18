package mc322.game.entitiesCharacters;

import java.util.ArrayList;
import java.util.Scanner;

import mc322.engine.LinearAlgebra;
import mc322.engine.Pair;
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
	
	protected char[][] requestMap(Room room, int iDest, int jDest)
	{
		char map[][] = room.builCharMap(this.i,this.j,iDest,jDest);
		System.out.println("mapa fabricado: ");
		for(int a = 0 ; a<map.length;a++,System.out.println())
		{
			for(int b = 0 ; b<map.length;b++)
			{
				System.out.print(map[a][b]);
			}
		}
		map = LinearAlgebra.solveMaze(map);
//		for(int i = 0;i<map.length;i++,System.out.println())
//		{
//			for(int j = 0;j<map.length;j++)
//			{
//				System.out.print(map[i][j]);
//			}
//		}
		return map;
	}
	
	public void follow(int i, int j, Room room)
	{
		if(i == this.i && j == this.j)
			return;
		
		
		char map[][] = requestMap(room,i,j);
		System.out.println("mapa de "+this.name+": ");
		
		for(int a = 0 ; a<map.length;a++,System.out.println())
		{
			for(int b = 0 ; b<map.length;b++)
			{
				System.out.print(map[a][b]);
			}
		}
		System.out.println("decisao de "+this.name+": ");
		
		
		switch(map[this.i][this.j])
		{
		case 'V':
			this.move('W',room);
			System.out.println("W");
			break;
		case 'A':
			this.move('S',room);
			System.out.println("S");
			break;
		case '<':
			this.move('A',room);
			System.out.println("A");
			break;
		case '>':
			this.move('D',room);
			System.out.println("D");
			break;
		default:
			break;
		}
	}
	
	public void follow(Character charac, Room room)
	{
		this.follow(charac.getPos().getFirst(),charac.getPos().getSecond(),room);
	}
	
	
	
}
