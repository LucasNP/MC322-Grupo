package mc322.game;

import mc322.engine.gfx.Image;
import mc322.engine.gfx.ImageTile;
import mc322.engine.sfx.AudioManager;

import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.awt.event.KeyEvent;

import mc322.engine.AbstractGame;
import mc322.engine.GameContainer;
import mc322.engine.Renderer;
import mc322.engine.Input;

import mc322.game.entitiesTiles.*;

public class GameManager implements AbstractGame{
      private Dungeon dungeon;
      private Menu menu;
      private AudioManager audio;
      private String STATE = "exploration"; 
      private Bag bag;
      private double timing_keys_move;
      private double timming_background_light;
      private boolean pause;

      public GameManager(){
            dungeon = new Dungeon();
            audio = new AudioManager();
            menu = new Menu(this);
            this.pause =false;
            this.timing_keys_move = 0;
            this.timming_background_light = 0;
            audio.playMusic(GameMapTokens.getPathSound("BestAmbientMusic"),true);
            bag = new Bag();
      }
      
      public void togglePause()
      {
    	  this.pause = !this.pause;
      }
      
  	public void pause() {
		this.pause = true;
		
	}
  	
  	public void unpause() {
		this.pause = false;
		
	}

      @Override
      public void update(GameContainer gc, double dt){
    	  
            if(timming_background_light > 3) timming_background_light = 0;
            if(!this.pause)
            {
	            if(timing_keys_move > 0.12){
	                  timing_keys_move = 0;
	                  KeysManager.keys_movement(gc,dungeon);
	            }
	
	            KeysManager.keys_action(gc,dungeon,bag);
	            KeysManager.mouse_action(gc,dungeon);
	            dungeon.update(dt);
	
	            
	            timing_keys_move += dt;
	            
            }
            menu.update(dt);
            timming_background_light += dt;
            KeysManager.keys_game_flow(gc,this,menu);
      }

      public void renderer(GameContainer gc, Renderer r){
            GameRenderer.drawBackground(r, timming_background_light);
            dungeon.renderer(r);
            menu.renderer(r);
      }

      public static void main(String args[]){
            System.setProperty("sun.java2d.opengl", "true"); 
            GameContainer gc = new GameContainer(new GameManager());
            gc.start();
      }



}
