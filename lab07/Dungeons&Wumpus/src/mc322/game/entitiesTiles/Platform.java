package mc322.game.entitiesTiles;

import mc322.engine.Renderer;
import mc322.game.Entity;
import mc322.game.GameRenderer;


public class Platform extends Entity{

	public Platform(int i, int j){
		this.i = i;
		this.j = j;
		this.name = "tile";
	}

	public void update(double dt) {
		
	}

	public void renderer(Renderer r) {
		GameRenderer.drawTile(i,j,elevation,name,r, 0, 1);
	}

}
