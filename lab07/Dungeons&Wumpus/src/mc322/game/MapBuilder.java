package mc322.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import mc322.engine.Pair;
import mc322.engine.CSVHandling;
import mc322.engine.LinearAlgebra;

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
                        Pair<Integer, Integer> p = Pair.of(i, j); 
                        rooms[i][j] = new Room(this, p);
                  }
            }
            
            return rooms;
      }


      public ArrayList<ArrayList<Pair<Entity,Entity>>>buildTiles(int size,Pair<Integer,Integer>pos,String numberRoom){
            CSVHandling scannerCSV = new CSVHandling();
            scannerCSV.setDataSource(gameMapTokens.getRoomPATH(numberRoom));
            
            String scannedRoom[][] = scannerCSV.requestCommands();
            ArrayList<ArrayList<Pair<Entity, Entity>>> tiles;

            for(int i = size-1; i>=0;i--){
                  for(int j = 0 ; j < size;j++){
                        Entity entity1;
                        Entity entity2;

                        Pair<Entity, Entity> p = new Pair.of(entity1,entity2);

                        eMapa[i][j]=GameDictionary.getTerreno(sMapa[i][0].charAt(MiscFunc.modulo(14-j)) ,i,j,decidirCanto(i,j));
                  }
            }
            
            return eMapa;
      }

      private char decidirCanto(int i, int j){
            if(j == 0)  return 'n';
            if(j == 14) return 's';
            if(i== 0)   return 'o';
            return 'e';
      }
      
      public Entity[][] buildEntities(Pair<Integer,Integer>pos) {
            return null;
      }
      
}
