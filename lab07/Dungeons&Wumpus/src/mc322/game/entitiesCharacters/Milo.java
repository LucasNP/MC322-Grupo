package mc322.game.entitiesCharacters;

import mc322.engine.LinearAlgebra;
import mc322.engine.Renderer;
import mc322.game.GameRenderer;
import mc322.game.Room;

public class Milo extends Heroes{
	
	public Milo(int i, int j,double elevation)
	{
		super(i,j,elevation);
		this.name = "Milo";
            this.state = "idle";

            this.updateDir = 3;
            this.updateFrame = 0;
            
            this.initAnimation = true;
            this.velocityAnim = this.velocityAnimIdle = 8;
            this.velocityAnimMoving = 3;

            this.nFrames = this.nFramesIdle = 6;
            this.nFramesMoving = 4;
            hpMax = 100;
            hp = hpMax;
	}


	@Override
	public void attack(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

      public void change_state(String state){
            this.state = state;
            if(state == "moving") this.nFrames = 3;
            if(state == "idle") this.nFrames = 5;
      }

	public void update(double dt) {
            this.updateFrame += this.velocityAnim*dt;
            
	}

	@Override
	public void renderer(Renderer r) {
        if(this.selected == 1) super.renderer(r);
		GameRenderer.drawCharacter(i,j,elevation,name,r, (int)updateFrame%nFrames, this.updateDir,this.state);
		GameRenderer.drawLife(0,20,1,this.hpMax,this.hp,r);
	}

	@Override
	public void toggleAnimation() {
		// TODO Auto-generated method stub
		
	}

}
