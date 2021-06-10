package mc322.game;

import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import mc322.engine.BasicObject;
import mc322.engine.Pair;
import mc322.engine.Renderer;
import mc322.engine.LinearAlgebra;

public class Room implements BasicObject {
      private final int size = 15;
      private Entity entities[][] = new Entity[size][size];

      private String numberRoom;
      private ArrayList<ArrayList<Pair<Entity, Entity>>> tiles = new ArrayList<>(size);

      //TODO: Porque cada room precisa saber pos ?
      public Room(MapBuilder mapBuilder, Pair<Integer, Integer> pos){
            Random rnd = new Random();
            this.numberRoom = "" + (rnd.nextInt(9)+1);
            numberRoom = "9";
            tiles = mapBuilder.buildTiles(size, pos, numberRoom);
            entities = mapBuilder.buildEntities(pos);
      }
      
      private void renderTerrain(Renderer r){
    	  
            String floor = "tile";
            int elevationFloor = -1;
            
            for(int i0 = size-1; i0 >0; i0--){
                  for(int j0= 0; j0 <size-1; j0++){
                	  //System.out.println("desenhando o chao em x: "+j0+" y: "+i0);
                        GameRenderer.drawTile(i0, j0, elevationFloor, floor, r,0,0);
                  }
            }
            GameRenderer.drawTile(size/2, size-1, elevationFloor, floor, r,0,0);
            GameRenderer.drawTile(0, size/2, elevationFloor, floor, r,0,0);
            
      }

      public void update(double dt) {
            for(int i0 =0;i0<size;i0++){
                  for(int j0=0;j0<size;j0++){
                        if(tiles!=null)
                              if(tiles.get(i0).get(j0) != null)
                                    tiles.get(i0).get(j0).getFirst().update(dt);
                        if(entities!=null)
                              if(entities[i0][j0] != null)
                                    entities[i0][j0].update(dt);
                  }
            }
      }

      public void renderer(Renderer r) {
            this.renderTerrain(r);

            for(int i0 = size-1; i0 >=0; i0--){
                  for(int j0=0;j0<size;j0++){
                              if(tiles.get(i0).get(j0) != null)
                              {
                            	  tiles.get(i0).get(j0).getFirst().renderer(r);
                            	  if(tiles.get(i0).get(j0).getSecond()!=null)
                            		  tiles.get(i0).get(j0).getSecond().renderer(r);
                              }
                                    
                        if(entities!=null)
                              if(entities[i0][j0] != null)
                                    entities[i0][j0].renderer(r);
                  }
            }
      }

}
