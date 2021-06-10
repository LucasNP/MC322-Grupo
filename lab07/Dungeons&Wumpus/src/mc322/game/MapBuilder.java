package mc322.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import mc322.engine.Pair;
import mc322.engine.CSVHandling;
import mc322.engine.LinearAlgebra;
import mc322.game.entitiesTiles.*;

public class MapBuilder{
      private EntityTilesLoader entityTilesLoader;
      private GameMapTokens gameMapTokens;
      private LinearAlgebra linearAlgebra;

      private Room[][] rooms;
      private final int mapHeight = 10;
      private final int mapWidth  = 10;

      private String dungeonPath;
      private Pair <Integer, Integer> origin;
 

      public Room[][] buildRooms(String dungeonPath) { 
            this.dungeonPath = dungeonPath;
            this.origin = linearAlgebra.getOrigin();
            this.rooms = new Room[mapHeight][mapWidth]; 
            
            for(int i = 0; i < mapHeight; i++){
                  for(int j = 0; j < mapHeight; j++){
                        Pair<Integer, Integer> pi = Pair.of(i, j); 
                        rooms[i][j] = new Room(this, pi);
                  }
            }
            
            return rooms;
      }


      public ArrayList<ArrayList<Pair<Entity,Entity>>>buildTiles(int size,Pair<Integer,Integer>pos,String numberRoom){
            CSVHandling scannerCSV = new CSVHandling();
            scannerCSV.setDataSource(gameMapTokens.getRoomPATH(numberRoom));
            
            String scannedRoom[][] = scannerCSV.requestCommands();
            ArrayList<ArrayList<Pair<Entity, Entity>>> tiles = new ArrayList<>();

            for(int i=0; i < size; i++) tiles.add(new ArrayList<Pair<Entity, Entity>>());

            for(int i = size-1; i>=0;i--){
                  for(int j = 0 ; j < size;j++){
                        Entity entity1;
                        Entity entity2;

                        char token = scannedRoom[i][0].charAt(linearAlgebra.getModulo(size-j));
                        entity1 = entityTilesLoader.getEntity(token, i, j, decidirCanto(i,j));
                        entity2 = null;

                        Pair <Entity, Entity> pe = Pair.of(entity1, entity2);
                        tiles.get(i).add(pe);
                  }
            }
            
            return tiles;
      }

      private String decidirCanto(int i, int j){
            if(i== 0) return "north-south";
            return "east-west";
      }
      
      public Entity[][] buildEntities(Pair<Integer,Integer>pos) {
            return null;
      }
      
}
