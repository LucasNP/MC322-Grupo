package mc322;

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
  
  public static void Desenha(int i, int j,int altura, Image image, Graphics g){
	  
	  int tempi =i;
	  int tempj =j;
	  i=modulo(14-tempj);
	  j=tempi;
	  i+=(altura);
	  j-=(altura);
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
  
  public static int modulo(int n)
  {
	  if(n<0)
		  return -n;
	  return n;
  }
	
	
}
