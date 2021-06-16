package mc322.game;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import mc322.engine.BasicObject;
import mc322.engine.Pair;
import mc322.engine.Renderer;
import mc322.game.entitiesCharacters.Character;
import mc322.game.entitiesCharacters.Heros;
import mc322.game.entitiesTiles.Chest;
import mc322.game.entitiesTiles.Door;
import mc322.game.entitiesTiles.Ladder;
import mc322.game.entitiesTiles.Platform;
import mc322.game.entitiesTiles.SafeZone;
import mc322.engine.LinearAlgebra;

public class Room implements BasicObject {
      private final int size = 15;
      private Entity entities[][] = new Entity[size][size];

      private String numberRoom;
      private ArrayList<ArrayList<Pair<Entity, Entity>>> tiles = new ArrayList<>(size);
      private Heros player;
      private Chest chest;
      private Heros milo;
      private String color;
      private Dungeon dungeon;
      private int i;
      private int j;
      
      public Room(MapBuilder mapBuilder, Pair<Integer, Integer> pos,String color, Dungeon dungeon){
            Random rnd = new Random();
            this.numberRoom = "" + (rnd.nextInt(9)+1);
            //numberRoom = "5";
            tiles = mapBuilder.buildTiles(size, pos, numberRoom,this);
            entities = mapBuilder.buildEntities(size, pos, numberRoom,this);
            player = milo;
            this.color = color;
            this.dungeon = dungeon;
            this.i = pos.getFirst();
            this.j = pos.getSecond();
      }

      private void renderTerrain(Renderer r){
            String floor = "tile";
            int elevationFloor = -1;

            for(int i = size-1; i > 0; i--){
                  for(int j = 0; j < size-1; j++){
                        GameRenderer.drawTile(i, j, elevationFloor, floor, r, 0, 0);
                  }
            }

            GameRenderer.drawTile(size/2, size-1, elevationFloor, floor, r,0,0);
            GameRenderer.drawTile(0, size/2, elevationFloor, floor, r,0,0);
      }

      public void update(double dt) {
    	  for(int i = size-1; i >= 0; i--){
              for(int j=0;j<size;j++){
                    if(tiles.get(i).get(j) != null){
                          tiles.get(i).get(j).getFirst().update(dt);
                          if(tiles.get(i).get(j).getSecond() != null)
                                tiles.get(i).get(j).getSecond().update(dt);
                                                 	  
                    }
                    if(entities[i][j] != null)
	                  {
	                	  entities[i][j].update(dt);
	                  } 
              }
        }
      }

      public void renderer(Renderer r) {
            this.renderTerrain(r);
            for(int i = size-1; i >= 0; i--){
                  for(int j=0;j<size;j++){
                	  
                        if(tiles.get(i).get(j) != null){
                              tiles.get(i).get(j).getFirst().renderer(r);
                              if(tiles.get(i).get(j).getSecond() != null)
                                    tiles.get(i).get(j).getSecond().renderer(r);
                              
                        }
                        if(entities[i][j] != null)
                      	  entities[i][j].renderer(r);
                  }
            }
      }

	public void addCharacter(int i, int j,Character character) throws FullPlaceException {
		if(this.entities[i][j] != null)
		{
			throw new FullPlaceException();
		}
		else
		{
			this.entities[i][j] = character;
		}
		
	}
	
	public void setMilo(Heros milo)
	{
		this.milo = milo;
	}
	
	public void setPlayer(Heros player)
	{
		this.player = milo;
	}
	
	public Heros getPlayer()
	{
		return player;
	}
	
	public void setChest(Chest chest)
	{
		this.chest = chest;
	}
	
	
	public boolean isAccessible(int i, int j,double elevation, double legSize,int dir)
	{
		
		if(this.entities[i][j] == null)
		{
			if(tiles.get(i).get(j) == null || tiles.get(i).get(j).getFirst() instanceof SafeZone)
			{
				if(elevation < legSize)
					return true;
				return false;
			}
			if(tiles.get(i).get(j).getFirst() instanceof Ladder)
			{
				if(tiles.get(i).get(j).getFirst().getDirection()-dir %2==0)
				return true;
			}
			if(tiles.get(i).get(j).getFirst() instanceof Platform && tiles.get(i).get(j).getSecond() == null && elevation > (1-legSize))// if it is platform
				return true;
			if(tiles.get(i).get(j).getFirst() instanceof Door)
			{
				if(i==0)
				{
					if(dungeon.getRoom(this.i,this.j-1) !=null) //south
						return true;
					else
						return false;
				}
				else if(i==size-1)
				{
					if(dungeon.getRoom(this.i,this.j+1) !=null)//north
						return true;
					else
						return false;
				}
				else if(j==0)
				{
					if(dungeon.getRoom(this.i - 1,this.j) !=null)//west
						return true;
					else
						return false;
				}
				else if(j==size-1)
				{
					if(dungeon.getRoom(this.i + 1,this.j) !=null) //east
						return true;
					else
						return false;
				}	
				else
				{
					System.out.println("error, the door is not well placed");
				}
			}
				
		}
		return false;
	}
	public void move(int i0,int j0,int i,int j)
	{
		this.entities[i][j]=this.entities[i0][j0];
		if(tiles.get(i).get(j) == null || tiles.get(i).get(j).getFirst() instanceof SafeZone)
		{
			this.entities[i][j].setElevation(0);
		}
		else if(tiles.get(i).get(j).getFirst() instanceof Ladder)
			this.entities[i][j].setElevation(0.5);
		else if(tiles.get(i).get(j).getFirst() instanceof Door)
		{
			
			int newI=i; // nova posicao i do personagem na sala final
			int newJ=j;// nova posicao j do personagem na nova sala
			int newRoomI=this.i; //nova posicao da sala
			int newRoomJ=this.j; //nova posicao da sala
			if(i==0)
			{
				//south
				newI=LinearAlgebra.getModulo(size-(i+2));
				newJ=j;
				newRoomI = this.i;
				newRoomJ = this.j-1; 
			}
			else if(i==size-1)
			{
				//north
				
				newI=1;
				newJ=j;
				newRoomI = this.i;
				newRoomJ = this.j+1;
			}
			else if(j==0)
			{
				//west
				newI=i;
				newJ=LinearAlgebra.getModulo(size-(j+2));
				newRoomI = this.i-1;
				newRoomJ = this.j;
			}
			else if(j==size-1)
			{
				//east
				newI=i;
				newJ=1;
				newRoomI = this.i+1;
				newRoomJ = this.j;
				
				
			}	
			else
			{
				System.out.println("error, the door is not well placed");
			}
			this.dungeon.getRoom(newRoomI,newRoomJ).placeEntity(this.entities[i][j],newI,newJ); // colocar a entidade na proxima sala
			this.entities[i][j].setPos(newI,newJ); // avisar a entidade sua nova posicao
			this.entities[i][j]=null; // retirar a entidade da sala antiga
			this.dungeon.setPos(newRoomI,newRoomJ); // setar a sala atual
			this.dungeon.getRoom(newRoomI,newRoomJ).setMilo(milo);
			this.dungeon.getRoom(newRoomI,newRoomJ).setPlayer(player); //adicionar o player na nova sala
			this.player = null; // remover o player da sala
			this.milo = null; //remove milo
		}
		else
			this.entities[i][j].setElevation(1);
		this.entities[i0][j0]=null;
	}
	
	public void placeEntity(Entity entity, int i ,int j)
	{
		entities[i][j]=entity;
	}
	

}
