package mc322.game;

import mc322.engine.gfx.ImageTile;

import mc322.engine.AbstractGame;
import mc322.engine.GameContainer;
import mc322.engine.Renderer;
import java.awt.event.KeyEvent;

public class GameManager extends AbstractGame{
      private ImageTile image;
      private String DIR;
      
      private double temp = 0;
      private char[][] board;

      private boolean update_board;

      public GameManager(){
            DIR = "C:\\Users\\nicol\\eclipse-workspace\\MC322-Grupo\\lab07\\Dungeons&Wumpus\\assets\\characters\\Milo\\1\\idle.png";
            try 
            {
            	image = new ImageTile(DIR, 64, 64);
            }
            catch(Exception e)
            {
            	System.out.println(e.toString());
            }
            

            //TODO: Substituir com Classe Construstora de tabuleiro
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

      // equivalente to tick
      public void update(GameContainer gc, double dt){ 
            if(gc.getInput().isKey(KeyEvent.VK_A)) System.out.println("A");

            int velocidade_anim = 10;
            temp = (temp + velocidade_anim*dt);
      }

      // equivalente to render
      public void renderer(GameContainer gc, Renderer r){ 
            int xCurrent = gc.getInput().getMouseX()  - image.getTileWidth()/2;
            int yCurrent =  gc.getInput().getMouseY() - image.getTileHeight()/2;

            r.drawBoard(0, 0, board, (int)temp%3);
            r.drawImageTile(image, xCurrent, yCurrent, (int)temp%6, 0);
      }

      public static void main(String args[]){
            System.setProperty("sun.java2d.opengl", "true"); // ?
            GameContainer gc = new GameContainer(new GameManager()); //gamecontainer?
            gc.start(); 

      }

}
