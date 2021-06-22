package mc322.game.entitiesCharacters;

import java.util.ArrayList;
import java.util.Scanner;

import mc322.engine.LinearAlgebra;
import mc322.engine.Pair;
import mc322.game.GameBrain;
import mc322.game.ImpossibleOriginOrDestiny;
import mc322.game.DoorSelected;
import mc322.game.Entity;
import mc322.game.Room;

public abstract class Character extends Entity{

      public Character(int i,int j,double elevation)
      {
            this.i = i;
            this.j = j;
            this.elevation = elevation;
            this.legSize = 0.7;
      }
      protected double legSize;
      protected int health;
      protected String name;

      //public abstract void change_state(String state);
      public abstract void attack(int i,int j);
      public abstract void move(int i, int j, Room room);
      public abstract void move(char dir, Room room);
      public abstract void die();
      public abstract void hurt(int damage);


      public String toString()
      {
            return this.name;
      }

      protected abstract boolean verifyMovement(int i, int j, Room room);

      protected String requestMap(Room room, int iDest, int jDest,boolean ignoreHeroes){
            char map[][] = room.builCharMap(this.i,this.j,iDest,jDest,ignoreHeroes);
            String solution = GameBrain.solveMaze(map,this.i,this.j,iDest,jDest);
            return solution;
      }

      public void change_state(String state){
            this.state = state;
            if(state == "moving"){
                  this.nFrames = this.nFramesMoving;
                  this.velocityAnim = this.velocityAnimMoving;
            }
            if(state == "idle"){
                  this.nFrames = this.nFramesIdle;
                  this.velocityAnim = this.velocityAnimIdle;
            }
      }

      public void follow(int i, int j, Room room, boolean ignoreHeroes){
            if(i == this.i && j == this.j) return;

            try{
                  String solution = requestMap(room, i, j, ignoreHeroes);
                  if(solution != null) this.move(solution.charAt(0), room);
            }
            catch(ImpossibleOriginOrDestiny e){
                  System.out.println("This place is inaccessable");
                  return;
            }
            catch(DoorSelected e){
                  System.out.println("Tentei entrar na porta");
                  if(i==0) follow(i+1, j, room, ignoreHeroes);
                  if(j==0) follow(i, j+1, room, ignoreHeroes);
                  if(i==14)follow(i-1, j, room, ignoreHeroes);
                  if(j==14)follow(i, j-1, room, ignoreHeroes);

                  if(LinearAlgebra.getModulo(i-this.i)+LinearAlgebra.getModulo(j-this.j) == 1){
                        if(i==14)this.move('W',room);
                        if(j==0) this.move('A',room);
                        if(i==0) this.move('S',room);
                        if(j==14)this.move('D',room);
                  }
                  return;
            }
            return;
      }

      public void follow(Character charac, Room room, boolean ignoreHeroes){
            this.follow(charac.getPos().getFirst(),charac.getPos().getSecond(),room,ignoreHeroes);
      }

}
