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


      public void drawBoard(int offX, int offY, char board[][]){
            Image image;
            String DIR;
            Map<Character, String> mapTiles = new HashMap<>();
            mapTiles.put('c', "/home/lucas/GameDev/Dungeons&Wumpus/tiles/Purple/tile1.png");
            
            for(int i = board[0].length - 1 ; i >= 0; i--){
                  for(int j = 0; j < board.length; j++){
                        DIR = mapTiles.get(board[i][j]);
                        image = new Image(DIR);

                        Pair <Integer, Integer> b = Pair.of(i*16, j*16);
                        b = linearAlgebra.toIsometrica(b);
                        drawImage(image, b.getFirst() + 100, b.getSecond() + 150);
                  }
            }
      }
}
