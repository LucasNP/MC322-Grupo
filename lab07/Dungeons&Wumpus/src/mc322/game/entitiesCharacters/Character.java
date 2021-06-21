package mc322.game.entitiesCharacters;

import java.util.ArrayList;
import java.util.Scanner;

import mc322.engine.Pair;
import mc322.game.GameBrain;
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

      protected char[][] requestMap(Room room, int iDest, int jDest)
      {
            char map[][] = room.builCharMap(this.i,this.j,iDest,jDest);
            map = GameBrain.solveMaze(map,this.i,this.j,iDest,jDest);
            return map;
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

      public void follow(int i, int j, Room room)
      {
            if(i == this.i && j == this.j)
                  return;


            char map[][] = requestMap(room,i,j);


            switch(map[this.i][this.j])
            {
                  case 'V':
                        this.move('W',room);
                        break;
                  case 'A':
                        this.move('S',room);
                        break;
                  case '<':
                        this.move('A',room);
                        break;
                  case '>':
                        this.move('D',room);
                        break;
                  default:
                        break;
            }
      }

      public void follow(Character charac, Room room){
            this.follow(charac.getPos().getFirst(),charac.getPos().getSecond(),room);
      }

}
