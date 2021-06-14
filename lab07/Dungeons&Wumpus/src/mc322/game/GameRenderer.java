package mc322.game;

import mc322.engine.Renderer;
import mc322.engine.gfx.ImageTile;

public class GameRenderer {

	public static void drawTile(int i,int j,int elevation, String name, Renderer r, int updateX, int updateY){
            ImageTile image = GameMapTokens.getImageTile(name, "Purple");
            r.drawIsometricImage(i+elevation, j-elevation, image, updateX, updateY);
	}

      public static void drawItem(int i,int j,int elevation, String name, Renderer r, int updateX, int updateY){
            ImageTile image = GameMapTokens.getImageItem(name, "Purple");
            r.drawIsometricImage(i+elevation, j-elevation, image, updateX, updateY);
	}
}
