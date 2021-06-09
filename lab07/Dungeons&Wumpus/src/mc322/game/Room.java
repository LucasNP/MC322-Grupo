package mc322.game;

import java.util.Random;

import mc322.engine.BasicObject;
import mc322.engine.Pair;
import mc322.engine.Renderer;

public class Room implements BasicObject {
	  private final int size = 13;
	  private Entity entities[][] = new Entity[size][size];
	  private Pair<Entity, Entity> tiles[][] = new Pair<Entity, Entity>[size][size];
	  private String numberRoom;

      public Room(MapBuilder mapBuilder, Pair pos) //called at MapBuilder
      {
            Random rnd = new Random();
            this.numberRoom = "" + (rnd.nextInt(9)+1);
            numberRoom = "3";
            tiles = mapBuilder.buildTiles(pos,""+numberRoom);
            entities = mapBuilder.buildEntities(pos);
      }
      
      private void renderTerrain(Renderer r)
      {
    	  String floor = "tile";
    	  int elevationFloor = 0;
          int lin = 14;
          int col = 14;

        for(int i = 0; i <=lin; i++){
              for(int j = 0; j <= col; j++){
            	  GameRenderer.drawImage(i,j,elevationFloor,floor,r);
              }
        }

      }
	public void update(double dt) {
        for(int i =0;i<size;i++)
        {
              for(int j=0;j<size;j++)
              {
              if(tiles!=null)
                    if(tiles[i][j] != null)
                    	tiles[i][j].getFirst().update(dt);
              if(entities!=null)
                  if(entities[i][j] != null)
                	  entities[i][j].update(dt);
              }
        }
		
	}


	public void renderer(Renderer r) {
		this.renderTerrain(r);
        for(int i =0;i<size;i++)
        {
              for(int j=0;j<size;j++)
              {
              if(tiles!=null)
                    if(tiles[i][j] != null)
                    	tiles[i][j].getFirst().renderer(r);
              if(entities!=null)
                  if(entities[i][j] != null)
                	  entities[i][j].renderer(r);
      		
              }
        }
		
	}
      
}
