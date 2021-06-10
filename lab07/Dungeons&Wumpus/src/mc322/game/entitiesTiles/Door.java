package mc322.game.entitiesTiles;

import mc322.engine.Renderer;
import mc322.game.Entity;
import mc322.game.GameRenderer;

public class Door extends Entity{

	private int dir;
	
	public Door(int i, int j, String dir, int elevation)
	{
		this.i=i;
		this.j= j;
		this.elevation = elevation+1;
		this.name = "door";
		if(dir == "north" || dir =="south")
			this.dir = 1;
		else
			this.dir = 0;
	}
	
	public void renderer(Renderer r) {
		GameRenderer.drawTile(i , j , elevation , name , r , 0 , this.dir);
		
	}

	public void update(double dt) {
		
	}

}
