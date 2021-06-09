package mc322.game.entitiesTiles;

import mc322.engine.Renderer;
import mc322.game.Entity;
import mc322.game.GameRenderer;

public class Chest extends Entity{
	
	public Chest (int i, int j, int elevation, String direction)
	{
		this.elevation = elevation;
		this.i=i;
		this.j=j;
		this.name = "chest";
		if(direction=="north-south")
			this.updateDir = 0;
		this.updateFrame = 0;
	}
	

	public void update(int dt) {
		this.updateFrame = dt%2;
	}

	public void renderer(Renderer r) {
		GameRenderer.drawImage(i,j,elevation,name,r,0,0);
	}



}
