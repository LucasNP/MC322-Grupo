package mc322.game.entitiesTiles;

import mc322.engine.Renderer;
import mc322.game.Entity;
import mc322.game.GameRenderer;

public class Ladder extends Entity{

	private int dir;
	public Ladder(int i, int j,String dir,int elevation)
	{
		this.i = i;
		this.j = j;
		this.elevation = elevation;
		this.name = "tile_ladder";
		if(dir == "north-south")
			this.dir = 1;
		else
			this.dir = 0;
	}
	
	public void update(double dt) {
		
	}

	public void renderer(Renderer r) {
		GameRenderer.drawTile(i , j , elevation , name , r , 0 , this.dir);
	}

}
