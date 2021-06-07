package mc322.game;

import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.*;

import mc322.engine.Pair;

public class Tabuleiro extends JPanel{
      private static final long serialVersionUID = 1L;
      private boolean iso;
      private BufferedImage image;
      private String DIR;
      private int oX, oY, tX, tY, lin, col, cellSide;
      private int cellSize;

      Dimension screenSize;
      
      // Constructor
      public Tabuleiro() {
            DIR = "/home/lucas/GameDev/Dungeons&Wumpus/tiles/Purple/";
            screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            
            oX = 0;
            oY = 0;

            tX = screenSize.width/10;
            tY = screenSize.height/2;

            lin = 13;
            col = 13;
            cellSide = 32;
            cellSize = 4*cellSide;
            
            iso = true;
      }

      //Metodos
      public Pair<Integer, Integer> toIsometrica(Pair<Integer, Integer> p){
            int nx = p.getFirst() + p.getSecond();
            int ny = (p.getSecond() - p.getFirst())/2;

            Pair <Integer, Integer> np = Pair.of(nx, ny);
            return np;
      }
      public Pair<Integer, Integer> toCartesianas(Pair<Integer, Integer> p){
            int nx = p.getFirst()/2 - p.getSecond();
            int ny = p.getFirst()/2 + p.getFirst(); 

            Pair <Integer, Integer> np = Pair.of(nx, ny);
            return np;
      }

      public void Line(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2, Graphics g){
            g.drawLine(p1.getFirst() + tX, p1.getSecond() + tY, p2.getFirst() + tX, p2.getSecond() + tY);
      }
      public void Desenha(int i, int j, String path, Graphics g){
            try { image = ImageIO.read(new File(DIR+path)); } 
            catch (IOException ex) {}

            Pair <Integer, Integer> b = Pair.of(i*cellSide, j*cellSide);
            b = toIsometrica(b);
            g.drawImage(image, b.getFirst() + tX-cellSize/2, b.getSecond() + tY-cellSize/2, cellSize, cellSize, null);
      }

      // Main
      @Override
      protected void paintComponent(Graphics g){
            super.paintComponent(g);

            setBackground(new Color(28, 28, 38));

            System.out.println(screenSize.width);
            System.out.println(screenSize.height);

            tX = screenSize.width/11;
            tY = screenSize.height/2;
 
            for(int i = 0; i <= lin; i++){
                  Pair <Integer, Integer> p1 = Pair.of(oX, oY + i*cellSide);
                  Pair <Integer, Integer> p2 = Pair.of(oX + col*cellSide, oY + i*cellSide);
                  Pair <Integer, Integer> p3 = Pair.of(oX + i*cellSide, oY);
                  Pair <Integer, Integer> p4 = Pair.of(oX + i*cellSide, oY + lin*cellSide);

                  if(iso == true){
                        p1 = toIsometrica(p1);
                        p2 = toIsometrica(p2);
                        p3 = toIsometrica(p3);
                        p4 = toIsometrica(p4);
                  }
                  Line(p1, p2, g);
                  Line(p3, p4, g);
            }


            for(int i = lin; i >= 0; i--){
                  for(int j = 0; j <= col; j++){
                        Desenha(i, j, "tile1.png", g);
                  }
            }

            Desenha(lin+1, -1, "tile2.png", g);
            Desenha(lin+2, -2, "tiles2.png", g);

            for(int i = lin-1; i > 0; i--){
                  if(i == 7) {
                        Desenha(i+2, -2, "metal_door_left1.png", g);
                        continue;
                  }
                  Desenha(i+1, -1, "tile_left_wall.png", g);
                  Desenha(i+2, -2, "tiles_left_wall.png", g);
                  if(i % 2 == 1) Desenha(i+1, -1, "torch1.png", g);
            }

            Desenha(1, -1, "tile2.png", g);
            Desenha(2, -2, "tile2.png", g);

            for(int j = 1; j < col; j++){
                  if(j == 6) {
                        Desenha(col+2, j-2, "metal_door_right1.png", g);
                        continue;
                  }
                  Desenha(col+1, j-1, "tile_right_wall.png", g);
                  Desenha(col+2, j-2, "tile_right_wall.png", g);
                  if(j % 2 == 0) Desenha(col+1, j-1, "torch1.png", g);
            }

            Desenha(col+1, col-1, "tile2.png", g);
            Desenha(col+2, col-2, "tile2.png", g);

            Desenha(1, 6-1, "metal_door_down1.png", g);
            Desenha(6 + 2, col-1, "metal_door_down7.png", g);

      }
}
