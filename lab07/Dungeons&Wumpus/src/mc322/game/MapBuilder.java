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

      private Room[][] rooms;
      private final int mapHeight = 10;
      private final int mapWidth  = 10;
      
      private String dungeonPath;
      private Pair <Integer, Integer> origin;
 

      public Room[][] buildRooms(String dungeonPath) { 
            this.dungeonPath = dungeonPath;
            this.origin = LinearAlgebra.getOrigin();
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
            scannerCSV.setDataSource(GameMapTokens.getRoomPATH(numberRoom));
            
            String scannedRoom[][] = scannerCSV.requestCommands();
            ArrayList<ArrayList<Pair<Entity, Entity>>> tiles = new ArrayList<>();

            for(int j=0; j < size; j++) tiles.add(new ArrayList<Pair<Entity, Entity>>());

            for(int i = 0; i<size;i++){
                  for(int j = 0 ; j < size;j++){
                		System.out.println(scannedRoom[j][0]);
                		
                        char token = scannedRoom[j][0].charAt(i);

                        Pair <Entity, Entity> pe = entityTilesLoader.getEntity(token, i, j, chooseOrientation(i,j,size));
                        tiles.get(i).add(pe);
                  }
            }
            
            return tiles;
      }

      private String chooseOrientation(int i, int j,int size){
            if(i== 0 ) return "south";
            if(i==size-1) return "north";
            if(j== 0 ) return "west";
            return "east";
      }
      
      
      
      public Entity[][] buildEntities(Pair<Integer,Integer>pos) {
            return null;
      }
      
}
