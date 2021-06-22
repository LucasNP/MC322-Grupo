package mc322.game.entitiesCharacters;

import mc322.engine.Renderer;
import mc322.game.GameRenderer;
import mc322.game.Room;

public class Luna extends Heroes{

	int velocidade_anim = 10;
	private double temp = 0;
	
	public Luna(int i, int j,double elevation)
	{
		super(i,j,elevation);
		this.name = "Luna";
            this.state = "idle";

            this.updateDir = 3;
            this.updateFrame = 0;
            
            this.initAnimation = true;
            this.velocityAnim = this.velocityAnimIdle = 8;
            this.velocityAnimMoving = 5;

            this.nFrames = this.nFramesIdle = 6;
            this.nFramesMoving = 4;
	}
	
	@Override
	protected void interact(int i, int j) {
		// TODO Auto-generated method stub
	}

	@Override
	public void attack(int i, int j) {
		// TODO Auto-generated method stub
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
	}

	@Override
	public void hurt(int damage) {
		// TODO Auto-generated method stub
		
	}

	public void update(double dt) {
            this.updateFrame += this.velocityAnim*dt;
	}

	@Override
	public void renderer(Renderer r) {
            if(this.selected == 1) super.renderer(r);
		GameRenderer.drawCharacter(i,j,elevation,name,r, (int)updateFrame%nFrames,this.updateDir,this.state);
	}

	@Override
	public void toggleAnimation() {
		// TODO Auto-generated method stub
		
	}

}
