package mc322.game.entitiesTiles;

import mc322.engine.Renderer;
import mc322.game.Entity;
import mc322.game.GameRenderer;


public class Wall extends Entity{

	private int dir;
	
	public Wall(int i, int j,boolean internal, String dir,int elevation)
	{
		this.elevation = elevation;
		this.i = i;
		this.j = j;
		if(internal)
		{
			this.name = "tile_side_wall";
			if(dir == "north-south")
				this.dir = 1;
			else
				this.dir = 0;
		}
		else
		{
			this.name = "tile_wall2";
			if(dir == "north")
				this.dir = 1;
			else if(dir == "west")
				this.dir = 0;
			else
			{
				this.name = "blank";
			}
		}
	}
	
	public void renderer(Renderer r) {
		
		GameRenderer.drawTile(i , j , elevation , name , r , 0 , this.dir);
		
	}

	public void update(double dt) {
		
	}

}
