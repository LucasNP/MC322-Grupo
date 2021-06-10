package mc322.game;

import mc322.engine.Renderer;
import mc322.engine.gfx.ImageTile;

public class GameRenderer {
	public static void drawTile(int i,int j,int elevation,String name,Renderer r,int updateX,int updateY){
		r.drawIsometricImage(i+elevation, j-elevation,name, updateX, updateY);
	}
}
