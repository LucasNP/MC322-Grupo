package mc322.game.entitiesTiles;

import mc322.engine.Renderer;
import mc322.game.Entity;
import mc322.game.GameRenderer;

public class Wall extends Entity{

	public Wall (int i, int j, boolean internal, String direction, int elevation){
            this.name = "tile_wall";
		this.i=i;
		this.j=j;
            this.elevation = elevation;

            this.initAnimation = false;
            this.velocityAnim = 8;

            if(direction == "west") this.updateDir = 0;
            else if(direction == "north") this.updateDir = 1;
            else this.name = "blank";
		
            if(internal) this.name = "tile_side_wall";
            this.updateFrame = 0;
	}
	
	public void update(double dt){

	}

	public void renderer(Renderer r) {
		GameRenderer.drawTile(i,j,elevation,name,r, 0, updateDir);
	}

      public void toggleAnimation(){
            this.initAnimation = !(this.initAnimation);
      }

}

