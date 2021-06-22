package mc322.game.entitiesTiles;

import mc322.engine.Pair;
import mc322.engine.Renderer;
import mc322.game.Entity;
import mc322.game.GameRenderer;

public class Chest extends Entity{

	private boolean opened;
      public Chest (int i, int j, String direction, int elevation){
            this.name = "chest";
            this.i=i;
            this.j=j;
            this.elevation = elevation;
            this.opened = false;
            this.initAnimation = false;
            this.velocityAnim = 8;
            this.nFrames = 6;

            if(direction == "west-east") this.updateDir = 1;
            this.updateFrame = 0;
	}
	
      public void update(double dt){
            if(this.initAnimation){
                  this.updateFrame += this.velocityAnim*dt;
                  if( (int)this.updateFrame > 0 && (int)this.updateFrame % nFrames == 0){
                        this.initAnimation = false;
                        this.opened = true;
                  }
            }
            
	}

      public void renderer(Renderer r) {
    	  if(opened)
    		  GameRenderer.drawItem(i,j,elevation,name,r, 6, updateDir); 
    	  else
    		  GameRenderer.drawItem(i,j,elevation,name,r, (int)updateFrame%nFrames, updateDir);
	}
      
      public void toggleAnimation(){
            this.initAnimation = !(this.initAnimation);
      }
      
      public Pair<Integer,Integer> getPos()
      {
    	  return Pair.of(this.i,this.j);
      }

}
