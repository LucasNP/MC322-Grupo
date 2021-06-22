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
            Pair <Integer, Integer> pt;
            int dist;

            Node(Pair <Integer, Integer> p, int dist){
                  this.pt = p;
                  this.dist = dist;
            }
      }
      public static boolean isSafe(int i, int j, int n){
            return (i >= 0) && (j >= 0) && (i < n) && (j < n); 
      }
      public static String solveMaze(char mat[][],int iBeg, int jBeg, int iEnd, int jEnd){

            Pair <Integer, Integer> src  = Pair.of(jBeg, iBeg);
            Pair <Integer, Integer> dest = Pair.of(iEnd, jEnd);

            //System.out.println(src.getFirst() + " , " + src.getSecond());
            //System.out.println(dest.getFirst() + " , " + dest.getSecond());

            //for(int i = 0;i<mat.length;i++){
                  //for(int j = 0;j<mat.length;j++) System.out.print(mat[i][j]);
                  //System.out.println();
            //}

            // Impossible to reach
            if(mat[iBeg][jBeg]=='#' || mat[iEnd][jEnd]=='#') throw new ImpossibleOriginOrDestiny();
            //else if(mat[iEnd][jEnd]=='D') throw new DoorSelected();

            //directions
            int dirI[] = {1,0,0,-1};
            int dirJ[] = {0,-1,1,0};

            //BFS
            String solution = "";
            int n = mat.length;

            int distance[][] = new int[n][n];
            boolean visited[][] = new boolean[n][n];
            ArrayDeque<Node> q = new ArrayDeque<>();

            for(int d[] : distance) Arrays.fill(d, -1);
            distance[src.getFirst()][src.getSecond()] = 0;
            visited[src.getFirst()][src.getSecond()] = true;

            Node s = new Node(src, 0);
            q.addLast(s);

            boolean ok = false;

            while (!q.isEmpty()){
                  Node curr = q.removeFirst();
                  Pair <Integer, Integer> pt = curr.pt;
                  int i = pt.getFirst();
                  int j = pt.getSecond();

                  if (i == dest.getFirst() && j == dest.getSecond()){
                        int dist = curr.dist;

                        distance[i][j] = dist;
                        //System.out.println(dist);
                        String pathmoves = "";

                        while (i != src.getFirst() || j != src.getSecond()){
                              if (j > 0   && distance[i][j - 1] == dist - 1){ pathmoves += 'W'; j--; }
                              if (i < n-1 && distance[i + 1][j] == dist - 1){ pathmoves += 'A'; i++; }
                              if (j < n-1 && distance[i][j + 1] == dist - 1){ pathmoves += 'S'; j++; }
                              if (i > 0   && distance[i - 1][j] == dist - 1){ pathmoves += 'D'; i--; }
                              dist--;
                        }
                        for(int k = pathmoves.length() - 1; k >= 0; --k) solution += pathmoves.charAt(k);
                        ok = true;
                        break;
                  }

                  for(int k = 0; k < 4; k++){
                        int ni = i + dirI[k];
                        int nj = j + dirJ[k];

                        if(!isSafe(ni, nj, n)) continue;

                        if( mat[ni][nj] != '#' ){
                              if (isSafe(ni, nj, n) && !visited[ni][nj]){
                                          visited[ni][nj] = true;
                                          Node adjCell = new Node(Pair.of(ni,nj), curr.dist + 1);
                                          q.addLast(adjCell);
                                          distance[ni][nj] = curr.dist + 1;
                              }
                        }
                  }

            }

            if (ok) return solution;
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
