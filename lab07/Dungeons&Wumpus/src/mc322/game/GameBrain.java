package mc322.game;

import java.util.ArrayList;

import mc322.engine.GameContainer;
import mc322.engine.LinearAlgebra;
import mc322.engine.Pair;
import mc322.game.entitiesCharacters.Heroes;
import mc322.game.entitiesCharacters.Luna;
import mc322.game.entitiesCharacters.Milo;
import mc322.game.entitiesCharacters.Raju;
import mc322.game.entitiesCharacters.Ze;
import java.util.Random;

public abstract class GameBrain{

      public static Pair<Integer, Integer> getOrigin(){
            
            //Purple:
            //Pair <Integer, Integer> origin = Pair.of(11, 1);
            
            //Green:
            //Pair <Integer, Integer> origin = Pair.of(8, 12);

            //Yellow:
            Pair <Integer, Integer> origin = Pair.of(6, 12);

            //Blue:
            //Pair <Integer, Integer> origin = Pair.of(10, 13);

            //Red:
            //Pair <Integer, Integer> origin = Pair.of(13, 12);

            //Black
            //Pair <Integer, Integer> origin = Pair.of(3, 14);


            return origin;
      }

      public static char[][] solveMaze(char map[][]){
            char auxMap[][] = new char[map.length][map.length];
            for(int i = 0;i<map.length;i++) for(int j = 0;j<map.length;j++) auxMap[i][j] = map[i][j];

            ArrayList<Pair<Integer,Integer>> news = new ArrayList<Pair<Integer,Integer>>();
            ArrayList<Pair<Integer,Integer>> newNews = new ArrayList<Pair<Integer,Integer>>();
            int dirI[] = {1,0,0,-1};
            int dirJ[] = {0,-1,1,0};


            for(int i = 0;i<map.length;i++){
                  for(int j = 0;j<map.length;j++){
                        if(map[i][j]=='E' || map[i][j]=='e') newNews.add(Pair.of(i,j));
                  }
            }

            boolean running = true;
            int counting = 0;

            while(running && counting <map.length*map.length/2){
                  for(int i = 0;i<newNews.size();i++) news.add(newNews.get(i));
                  newNews.clear();

                  if(news.size()==0){
                        running = false;
                        break;
                  }

                  for(int i = 0;i<news.size();i++){
                        for(int k =0;k<4;k++){
                              int next_i = news.get(i).getFirst()+dirI[k];
                              int next_j = news.get(i).getSecond()+dirJ[k];

                              if(!LinearAlgebra.between(next_i,0,map.length-1)||
                                          !LinearAlgebra.between(next_j,0,map.length-1)) 
                                    continue;

                              char v = map[next_i][next_j];

                              if(".BUMNEbe".indexOf(v) != -1){ 
                                    char c = auxMap[news.get(i).getFirst()][news.get(i).getSecond()];
                                    if(c == 'B' || c == 'b') { running = false; break; }

                                    if("BU".indexOf(v) != -1 && "UMNE".indexOf(c) == -1) continue;
                                    if(v == 'N' && (k==0 || k == 3)) continue;
                                    if(v == 'M' && (k==1 || k == 2 || k ==0 )) continue;
                                    if(".b".indexOf(v) != -1 && ".MNe".indexOf(c) ==  -1) continue;

                                    String str_dir = "A><V";
                                    map[next_i][next_j] = str_dir.charAt(k);
                                    newNews.add(Pair.of(next_i, next_j));
                              }
                        }
                        if(!running) break;
                  }

                  news.clear();
                  counting ++;
            } 		
            return map;
      }


      static void walk(Dungeon dungeon) {
            if(!dungeon.getFollow()){
                  GameRenderer.change_animation_state("idle", dungeon);
                  dungeon.getCurrentRoom().getPlayer().change_state("moving");
                  return;
            }

            Room cRoom = dungeon.getCurrentRoom();
            Random rand = new Random();

            if(cRoom.getMilo() != cRoom.getPlayer()){
                  if(rand.nextInt(9)<7) cRoom.getMilo().follow(cRoom.getLuna(),cRoom);
                  else cRoom.getMilo().follow(cRoom.getRaju(),cRoom);
            }

            if(cRoom.getLuna() != cRoom.getPlayer()){
                  if(rand.nextInt(13)<9) cRoom.getLuna().follow(cRoom.getPlayer(),cRoom);
                  else cRoom.getLuna().follow(cRoom.getZe(),cRoom);
            }

            if(cRoom.getZe() != cRoom.getPlayer()) {
                  if(rand.nextInt(15)<8) cRoom.getZe().follow(cRoom.getRaju(),cRoom);
                  else
                        cRoom.getZe().follow(cRoom.getLuna(),cRoom);
            }

            if(cRoom.getRaju() != cRoom.getPlayer()){
                  if(rand.nextInt(8)<5) cRoom.getRaju().follow(cRoom.getMilo(),cRoom);
                  else cRoom.getRaju().follow(cRoom.getLuna(),cRoom);
            }
      }

}
