package mc322.game;

import mc322.engine.gfx.Image;
import mc322.engine.gfx.ImageTile;

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
      private ImageTile image;
      private Dungeon dungeon;

      private double temp = 0;
      private char[][] board;
      private Pillar pillar;

      public GameManager(){
            dungeon = new Dungeon();
            image = GameMapTokens.getImageCharacter("Milo", "idle");

            board = new char[15][15];
            int lin = board[0].length;
            int col = board.length;
            for(int i = 0; i < lin; i++){
                  for(int j = 0; j < col; j++) {
                        board[i][j] = 'a';
                        if(i == lin-1 || j == 0) {
                              board[i][j] = 'c';
                              if(i == lin/2 || j == col/2) board[i][j] = 'd';
                        }
                        if(j == col-1 || i == 0) {
                              board[i][j] = '.';
                              if(i == lin/2 || j == col/2) board[i][j] = 'd';
                        }
                  }
            }
            board[0][0] = 'b';
            board[0][col-1] = 'b';
            board[col-1][0] = 'b';
            board[col-1][col-1] = 'b';
      }

      @Override
      public void update(GameContainer gc, double dt){
            if(gc.getInput().isKey(KeyEvent.VK_A)) System.out.println("A");

            int velocidade_anim = 10;
            temp += velocidade_anim*dt;
      }

      @Override
      public void renderer(GameContainer gc, Renderer r){
            int xCurrent = gc.getInput().getMouseX()  - image.getTileWidth()/2;
            int yCurrent =  gc.getInput().getMouseY() - image.getTileHeight()/2;

            //r.drawBoard(0, 0, board, (int)temp%3);
            dungeon.renderer(r);
            r.drawImageTile(image, xCurrent, yCurrent, (int)temp%6, 0);
      }

      public static void main(String args[]){
            System.setProperty("sun.java2d.opengl", "true"); 
            GameContainer gc = new GameContainer(new GameManager());
            gc.start();
      }

}
