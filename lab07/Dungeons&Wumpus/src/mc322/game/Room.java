package mc322.game;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import mc322.engine.BasicObject;
import mc322.engine.Pair;
import mc322.engine.Renderer;
import mc322.game.entitiesCharacters.Character;
import mc322.game.entitiesTiles.SafeZone;
import mc322.engine.LinearAlgebra;

public class Room implements BasicObject {
      private final int size = 15;
      private Entity entities[][] = new Entity[size][size];

      private String numberRoom;
      private ArrayList<ArrayList<Pair<Entity, Entity>>> tiles = new ArrayList<>(size);

      public Room(MapBuilder mapBuilder, Pair<Integer, Integer> pos){
            Random rnd = new Random();
            this.numberRoom = "" + (rnd.nextInt(9)+1);
            //numberRoom = "9";
            tiles = mapBuilder.buildTiles(size, pos, numberRoom);
            entities = mapBuilder.buildEntities(size, pos, numberRoom);
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
                          if(entities[i][j] != null)
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
                              if(entities[i][j] != null)
                            	  entities[i][j].renderer(r);
                        }
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
	

}
