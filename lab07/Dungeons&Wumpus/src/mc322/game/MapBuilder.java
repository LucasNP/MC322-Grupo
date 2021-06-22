package mc322.game;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import mc322.engine.Pair;
import mc322.engine.CSVHandling;
import mc322.engine.LinearAlgebra;
import mc322.game.entitiesCharacters.Heroes;
import mc322.game.entitiesCharacters.Luna;
import mc322.game.entitiesCharacters.Milo;
import mc322.game.entitiesCharacters.Raju;
import mc322.game.entitiesCharacters.Ze;
import mc322.game.entitiesTiles.*;

public class MapBuilder{

      private Room[][] rooms;
      private final int mapHeight = 20;
      private final int mapWidth  = 20;

      private Pair <Integer, Integer> origin;
      private Heroes player;


      public Room[][] buildRooms(String dungeonPath, Dungeon dungeon) { 
            this.origin = GameBrain.getOrigin();
            this.rooms = new Room[mapHeight][mapWidth];


            CSVHandling scannerCSV = new CSVHandling();
            scannerCSV.setDataSource(dungeonPath);

            String scannedDungeon[][] = scannerCSV.requestCommands();

            for(int i = 0; i < mapHeight; i++){
                  for(int j = 0; j < mapHeight; j++){

                        Pair<Integer, Integer> pi = Pair.of(i, j);
                        char token = scannedDungeon[i][j].charAt(0);

                        String rooms_around = "";
                        if(token != '0'){
                              rooms_around += scannedDungeon[i][j+1].charAt(0);
                              rooms_around += scannedDungeon[i][j-1].charAt(0);
                              rooms_around += scannedDungeon[i+1][j].charAt(0);
                              rooms_around += scannedDungeon[i-1][j].charAt(0); 
                        }

                        switch(token){
                              case 'y':
                                    rooms[i][j] = new Room(this, pi, rooms_around,"Yellow", dungeon);
                                    break;
                              case 'k':
                                    rooms[i][j] = new Room(this, pi, rooms_around,"Black", dungeon);
                                    break;
                              case 'b':
                                    rooms[i][j] = new Room(this, pi, rooms_around,"Blue", dungeon);
                                    break;
                              case 'g':
                                    rooms[i][j] = new Room(this, pi, rooms_around,"Green", dungeon);
                                    break;
                              case 'r':
                                    rooms[i][j] = new Room(this, pi, rooms_around,"Red", dungeon);
                                    break;
                              case 'p':
                                    rooms[i][j] = new Room(this, pi, rooms_around,"Purple", dungeon);
                                    break;
                              case '0':
                              default:
                                    rooms[i][j] = null;
                                    break;
                        }
                  }
            }
            return rooms;
      }

      public ArrayList<ArrayList<Pair<Entity,Entity>>>buildTiles(int size,Pair<Integer,Integer>pos, 
                  String rooms_around,
                  String numberRoom,
                  Room room){

            CSVHandling scannerCSV = new CSVHandling();
            scannerCSV.setDataSource(GameMapTokens.getRoomPATH(numberRoom));

            String scannedRoom[][] = scannerCSV.requestCommands();
            ArrayList<ArrayList<Pair<Entity, Entity>>> tiles = new ArrayList<>();

            for(int i=0; i < size; i++) tiles.add(new ArrayList<Pair<Entity, Entity>>());

            for(int i = size-1; i >= 0; i--){
                  for(int j = 0 ; j < size;j++){
                        char token = scannedRoom[j][0].charAt(i);
                        String dir = chooseOrientation(i, j, size);

                        boolean blocked = is_blocked_filter(token, dir, rooms_around);
                        Pair <Entity, Entity>pe;
                        pe = EntityTilesLoader.getEntity(token,blocked,i,j,dir,room.getColor(),room);
                        tiles.get(i).add(pe);

                        if(pe!=null && pe.getFirst() instanceof Chest){
                              room.setChest((Chest) pe.getFirst());
                        }
                        else if(pe!=null && pe.getSecond() instanceof Chest){
                              room.setChest((Chest) pe.getSecond());
                        }

                  }
            }
            return tiles;
      }

      private String chooseOrientation(int i, int j, int size){
            if(i == size-1) return "north";
            if(i == 0) return "south";
            if(j == 0) return "west";
            if(j == size-1) return "east";
            return "internal";
      }

      private boolean is_blocked_filter(char token, String dir, String rooms_around){
            if(token != 'd') return false;
            if(dir == "north" && rooms_around.charAt(0) == '0') return true;
            if(dir == "south" && rooms_around.charAt(1) == '0') return true;
            if(dir == "east" && rooms_around.charAt(2)  == '0') return true;
            if(dir == "west" && rooms_around.charAt(3)  == '0') return true;

            return false;
      }

      public Entity[][] buildEntities(int size,Pair<Integer,Integer> pos,String numberRoom,Room room) {
            player = null;
            Entity entities[][] = new Entity[size][size];

            CSVHandling scannerCSV = new CSVHandling();
            scannerCSV.setDataSource(GameMapTokens.getRoomPATH(numberRoom));

            String scannedRoom[][] = scannerCSV.requestCommands();

            for(int i = 0; i < size;i++)
                  for(int j = 0 ; j < size;j++)
                        entities[i][j] = null;

            if(this.origin.getFirst() == pos.getFirst() && this.origin.getSecond() == pos.getSecond()){
                  int targetI = 2;
                  int targetJ = 7;
                  char token = scannedRoom[targetJ][0].charAt(targetI);

                  if(token == '.' || token == 's' ||token =='S'){
                        player = new Luna(targetI,targetJ,0);
                        entities[targetI][targetJ] = player;
                        room.setZe(new Ze(targetI-1,targetJ-1,0));
                        room.setLuna(player);
                        room.setRaju(new Raju(targetI-1,targetJ,0));
                        room.setMilo(new Milo(targetI-1,targetJ+1,0));

                  }
                  player.toggleSelect();
                  room.setPlayer(player);

            }
            return entities;
      }

      public Heroes getPlayer(){
            return player;
      }


}
