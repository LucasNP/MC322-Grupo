package mc322.game;

import mc322.engine.gfx.Image;
import mc322.engine.gfx.ImageTile;

import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;

import mc322.engine.AbstractGame;
import mc322.engine.GameContainer;
import mc322.engine.Renderer;
import mc322.engine.Input;
import java.awt.event.KeyEvent;

public class GameManager extends AbstractGame{
      private ImageTile image;
      private String DIR;
      
      private double temp = 0;
      private char[][] board;

      public GameManager(){
            DIR = "/home/lucas/GameDev/Dungeons&Wumpus/characters/Milo/1/idle.png";
            image = new ImageTile(DIR, 64, 64);

            board = new char[13][13];
            for(int i = 0; i < board[0].length; i++){
                  for(int j = 0; j < board.length; j++) board[i][j] = 'c';
            }
      }

      @Override
      public void update(GameContainer gc, double dt){
            if(gc.getInput().isKey(KeyEvent.VK_A)) System.out.println("A");

            int velocidade_anim = 10;
            temp = (temp + velocidade_anim*dt)%6;
      }


      @Override
      public void renderer(GameContainer gc, Renderer r){
            int xCurrent = gc.getInput().getMouseX()  ;
            int yCurrent =  gc.getInput().getMouseY() - image.getHeight()/2;

            r.drawBoard(0, 0, board);
            r.drawImageTile(image, xCurrent, yCurrent, (int)temp, 0);
      }

      public static void main(String args[]){
            GameContainer gc = new GameContainer(new GameManager());
            gc.start();

      }

}
