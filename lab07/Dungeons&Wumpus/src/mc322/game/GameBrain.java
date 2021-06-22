package mc322.game;

import java.util.ArrayList;
import java.util.*;  

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
            Pair <Integer, Integer> origin = Pair.of(11, 1);

            //Green:
            //Pair <Integer, Integer> origin = Pair.of(8, 12);

            //Yellow:
            //Pair <Integer, Integer> origin = Pair.of(6, 12);

            //Blue:
            //Pair <Integer, Integer> origin = Pair.of(10, 13);

            //Red:
            //Pair <Integer, Integer> origin = Pair.of(13, 12);

            //Black
            //Pair <Integer, Integer> origin = Pair.of(3, 14);
            return origin;
      }


      // Solve Maze with BFS
      static class Node{
            Pair<Integer, Integer> pt;
            int dist;

            Node(Pair<Integer, Integer> p, int dist){
                  this.pt = p;
                  this.dist = dist;
            }
      }
      public static boolean isSafe(int i, int j, int n){
            return (i >= 0) && (j >= 0) && (i < n) && (j < n); 
      }
      public static String solveMaze(char map[][],int iBeg, int jBeg, int iEnd, int jEnd){

            Pair <Integer, Integer> src  = Pair.of(jBeg-1, iBeg-1);
            Pair <Integer, Integer> dest = Pair.of(iEnd-1, jEnd-1);

            System.out.println(src.getFirst() + " , " + src.getSecond());
            System.out.println(dest.getFirst() + " , " + dest.getSecond());


            for(int i = 0;i<map.length;i++){
                  for(int j = 0;j<map.length;j++) System.out.print(map[i][j]);
                  System.out.println();
            }

            // Impossible to reach
            if(map[iBeg][jBeg]=='#' || map[iEnd][jEnd]=='#') throw new ImpossibleOriginOrDestiny();
            else if(map[iEnd][jEnd]=='D') throw new DoorSelected();

            //directions
            int dirI[] = {1,0,0,-1};
            int dirJ[] = {0,-1,1,0};

            //BFS
            String solution = "";
            int n = map.length - 2;
            boolean ok = false;
            int distance[][] = new int[n][n];


            boolean visited[][] = new boolean[n][n];
            for(int d[] : distance) Arrays.fill(d, -1);
            for(boolean v[] : visited)  Arrays.fill(v, false);


            distance[src.getFirst()][src.getSecond()] = 0;
            visited[src.getFirst()][src.getSecond()]  = true;

            ArrayDeque<Node> q = new ArrayDeque<>();
            q.addLast(new Node(src, 0));


            while (!q.isEmpty()){

                  Node current = q.removeFirst();
                  Pair <Integer, Integer> pt = current.pt;

                  if (pt.getFirst() == dest.getFirst() && pt.getSecond() == dest.getSecond()){
                        int i = pt.getFirst(); 
                        int j = pt.getSecond();

                        int dist = current.dist;
                        System.out.println("pao: " + dist);
                        distance[i][j] = dist;

                        while ( i != src.getFirst() || j != src.getSecond() ){
                              if (i>0   && distance[i-1][j] == dist - 1){ solution += 'D'; i--; }
                              if (i<n-1 && distance[i+1][j] == dist - 1){ solution += 'A'; i++; }
                              if (j>0   && distance[i][j-1] == dist - 1){ solution += 'W'; j--; }
                              if (j<n-1 && distance[i][j+1] == dist - 1){ solution += 'S'; j++; }
                              dist--;
                        }

                        ok = true;
                        break;
                  }

                  for(int k = 0; k < 4; k++){

                        int ni = pt.getFirst() + dirI[k];
                        int nj = pt.getSecond() + dirJ[k];


                        if (isSafe(ni, nj, n) && !visited[ni][nj]){
                              if(map[nj][ni] == '#'){
                                    System.out.println(ni + " " + nj);
                              }

                              visited[ni][nj] = true;
                              Node adjCell = new Node(Pair.of(ni, nj), current.dist + 1);
                              q.addLast(adjCell);
                              distance[ni][nj] = current.dist + 1;
                        }
                  }
                  //System.out.println();

            }

            System.out.println(solution);

            return null;
      }


      static void walk(Dungeon dungeon) {
            if(!dungeon.getFollow()){
                  GameRenderer.change_animation_state("idle", dungeon);
                  dungeon.getCurrentRoom().getPlayer().change_state("moving");
                  return;
            }

            Room cRoom = dungeon.getCurrentRoom();
            Random rand = new Random();

            //if(cRoom.getMilo() != cRoom.getPlayer()){
            //if(rand.nextInt(9)<7) cRoom.getMilo().follow(cRoom.getLuna(),cRoom,false);
            //else cRoom.getMilo().follow(cRoom.getRaju(),cRoom,false);
            //}

            //if(cRoom.getLuna() != cRoom.getPlayer()){
            //if(rand.nextInt(13)<9) cRoom.getLuna().follow(cRoom.getPlayer(),cRoom,false);
            //else cRoom.getLuna().follow(cRoom.getZe(),cRoom,false);
            //}

            //if(cRoom.getZe() != cRoom.getPlayer()) {
            //if(rand.nextInt(15)<8) cRoom.getZe().follow(cRoom.getRaju(),cRoom,false);
            //else
            //cRoom.getZe().follow(cRoom.getLuna(),cRoom,false);
            //}

            //if(cRoom.getRaju() != cRoom.getPlayer()){
            //if(rand.nextInt(8)<5) cRoom.getRaju().follow(cRoom.getMilo(),cRoom,false);
            //else cRoom.getRaju().follow(cRoom.getLuna(),cRoom,false);
            //}
      }

}
