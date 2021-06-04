package mc322;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public abstract class MiscFunc {
	
    public static Pair<Integer, Integer> toIsometrica(Pair<Integer, Integer> p){
        int nx = p.getFirst() + p.getSecond();
        int ny = (p.getSecond() - p.getFirst())/2;

        Pair <Integer, Integer> np = Pair.of(nx, ny);
        return np;
  }
  public static Pair<Integer, Integer> toCartesianas(Pair<Integer, Integer> p){
        int nx = p.getFirst()/2 - p.getSecond();
        int ny = p.getFirst()/2 + p.getFirst(); 

        Pair <Integer, Integer> np = Pair.of(nx, ny);
        return np;
  }
  
  public static void Desenha(int i, int j, Image image, Graphics g){
      int tX = Game.WIDTH/10;
      int tY = Game.HEIGHT/2;
	  int cellSide = 32;
	  int cellSize = 4*cellSide;
      Pair <Integer, Integer> b = Pair.of(i*cellSide, j*cellSide);
      b = MiscFunc.toIsometrica(b);
      int x =b.getFirst() + tX-cellSize/2;
      int y = b.getSecond() + tY-cellSize/2;
      g.drawImage(image, x, y, cellSize, cellSize, null);
}
	
	
}
