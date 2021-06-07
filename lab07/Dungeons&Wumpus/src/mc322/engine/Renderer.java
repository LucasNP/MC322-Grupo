package mc322.engine;

import java.awt.image.DataBufferInt;
import java.lang.Math;

import mc322.engine.LinearAlgebra;

import mc322.engine.gfx.Image;
import mc322.engine.gfx.ImageTile;

import java.util.HashMap;
import java.util.Map;

public class Renderer{
      
      private int pW, pH;
      private int[] p;
      private LinearAlgebra linearAlgebra;

      public Renderer(GameContainer gc){
            this.pW = gc.getWidth();
            this.pH = gc.getHeight();
            this.p = ((DataBufferInt) gc.getWindow().getImage().getRaster().getDataBuffer()).getData();
            this.linearAlgebra = new LinearAlgebra();
      }

      public void clear(){
            for(int i = 0; i < p.length; i++){
                  p[i] = 0xff000000;
            }
      }

      public void setPixel(int x, int y, int value){
            if( (x < 0 || x >= pW || y < 0 || y >= pH) || value == 0xffff00ff) return;
            p[x + y*pW] = value;
      }

      public void drawImage(Image image, int offX, int offY){
            int newX = 0,
                newY = 0,
                newWidth  = image.getWidth(),
                newHeight = image.getHeight();

            if(offX < -newWidth  || offX >= pW) return;
            if(offY < -newHeight || offY >= pH) return; 

            if(offX < 0) newX -= offY;
            if(offY < 0) newY -= offY;
            if(newWidth + offX > pW)  newWidth  -= newWidth  + offX - pW;
            if(newHeight + offY > pH) newHeight -= newHeight + offY - pH;

            for(int y = newY; y < newHeight; y++){
                  for(int x = newX; x < newWidth; x++){
                        setPixel(x + offX, y + offY, image.getP()[x + y*image.getWidth()] );     
                  }
            }           
      }

      //TODO: Modularizar essa função em classes
      public void drawIsometricImage(int i, int j, char c, int tileX, int tileY){
            //TODO: Classe GameTileDefinitions 
            Map<Character, String> mapTiles = new HashMap<>();
            mapTiles.put('a', "C:\\Users\\nicol\\eclipse-workspace\\MC322-Grupo\\lab07\\Dungeons&Wumpus\\src\\mc322\\assets\\tiles\\Purple\\tile1.png");
            mapTiles.put('b', "C:\\Users\\nicol\\eclipse-workspace\\MC322-Grupo\\lab07\\Dungeons&Wumpus\\src\\\\mc322\\assets\\tiles\\Purple\\pilar.png");

            mapTiles.put('g', "C:\\Users\\nicol\\eclipse-workspace\\MC322-Grupo\\lab07\\Dungeons&Wumpus\\src\\mc322\\assets\\tiles\\Purple\\tile_left_wall.png");

            //TODO: Draw
            ImageTile image;
            String DIR;
            DIR = mapTiles.get(c);
            image = new ImageTile(DIR, 64, 64);
            
            int tx = pW/2 - 7*image.getTileWidth()/2;
            int ty = pH/2 - image.getTileHeight()/2;

            int sizeX = image.getTileWidth()/4;
            int sizeY = image.getTileHeight()/4;
            
            Pair <Integer, Integer> b = Pair.of(i*sizeX, j*sizeY);
            b = linearAlgebra.toIsometrica(b);
            drawImageTile(image, b.getFirst() + tx, b.getSecond() + ty, tileX, tileY);
      }

      public void drawImageTile(ImageTile image, int offX, int offY, int tileX, int tileY){
            int newX = 0,
                newY = 0,
                newWidth  = image.getTileWidth(),
                newHeight = image.getTileHeight();

            if(offX < -newWidth  || offX >= pW) return;
            if(offY < -newHeight || offY >= pH) return; 

            if(offX < 0) newX -= offY;
            if(offY < 0) newY -= offY;
            if(newWidth + offX > pW)  newWidth  -= newWidth  + offX - pW;
            if(newHeight + offY > pH) newHeight -= newHeight + offY - pH;

            int fixX = tileX*newWidth;
            int fixY = tileY*newHeight;

            for(int y = newY; y < newHeight; y++){
                  for(int x = newX; x < newWidth; x++){
                        setPixel(x + offX, y + offY, image.getP()[(x+fixX)+ (y+fixY)*image.getWidth()] );     
                  }
            }  
      }

      //TODO: Modularizar essa função em classes
      public void drawBoard(int offX, int offY, char board[][]){

            int lin = board[0].length;
            int col = board.length;

            for(int i = lin - 1 ; i >= 0; i--){
                  for(int j = 0; j < col; j++){
                        drawIsometricImage(i, j, board[i][j], 0, 0);
                  }
            }

            drawIsometricImage(lin, -1, 'b', 0, 0);
            //drawIsometricImage(lin+1, -2, 'b');

            for(int i = lin-1; i > 0; i--){
                  drawIsometricImage(i, -1, 'g', 0, 0);
                  //drawIsometricImage(i+1, -2, 'g');
            }


            drawIsometricImage(0, -1, 'b', 1, 0);
            drawIsometricImage(col, col-1, 'b', 2, 0);
            //drawIsometricImage(1, -2, 'b');

      }
}
