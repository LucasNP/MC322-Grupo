package mc322.game;

import mc322.engine.gfx.Image;
import mc322.engine.gfx.ImageTile;
import mc322.engine.sfx.AudioManager;

import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.event.KeyEvent;

import mc322.engine.AbstractGame;
import mc322.engine.Pair;
import mc322.engine.GameContainer;
import mc322.engine.Renderer;
import mc322.engine.Input;

import mc322.game.entitiesTiles.*;

public class GameManager implements AbstractGame{
      private Dungeon dungeon;

      private AudioManager audio;
      private String STATE = "exploration"; 

      private double timing_keys_move;
      private double timing_background_light;

      private Pair<Integer, Pair<Integer, Integer>> mouseClick;
      private Pair<Integer, Integer> mouseClickPoint;
      private boolean movingToPointer;
      private boolean pause;

      public GameManager(){
            dungeon = new Dungeon();
            audio = new AudioManager();

            this.pause =false;
            this.timing_keys_move = 0;
            this.timing_background_light = 0;
            
            this.movingToPointer = false;
            this.mouseClick = null;
      }
      
      public void togglePause(){
            this.pause = !this.pause;
      }


      @Override
      public void update(GameContainer gc, double dt){

            if(timing_background_light > 3) timing_background_light = 0;
            if(!this.pause){

                  if(timing_keys_move > 0.12) {
                        timing_keys_move = 0;

                  }

                  KeysManager.keys_action(gc,dungeon);
                  KeysManager.keys_movement(gc,dungeon, timing_keys_move);
                  
                  mouseClick = KeysManager.verifyMouseClick(gc,dungeon);


                  if(mouseClick != null){
                        if(mouseClick.getFirst() == 1){
                              movingToPointer = !movingToPointer;
                              mouseClickPoint = mouseClick.getSecond();
                        }
                        if(mouseClickPoint != null)
                              movingToPointer = KeysManager.mouse_action(gc,dungeon,timing_keys_move,movingToPointer,mouseClickPoint);
                  }

                  dungeon.update(dt);
	            timing_keys_move += dt;
            }
            timing_background_light += dt;
            KeysManager.keys_game_flow(gc,this);
      }

      public void renderer(GameContainer gc, Renderer r){
            GameRenderer.drawBackground(r, dungeon, timing_background_light);
            dungeon.renderer(r);
      }

      public static void main(String args[]){
            System.setProperty("sun.java2d.opengl", "true"); 
            GameContainer gc = new GameContainer(new GameManager());
            gc.start();
      }

}
