package mc322.game.entitiesCharacters;

import mc322.engine.Renderer;
import mc322.game.GameRenderer;
import mc322.game.Room;

public class Milo extends Heros{

	int velocidade_anim = 10;
	private double temp = 0;
	
	public Milo(int i, int j,int elevation)
	{
		super(i,j,elevation);
		this.name = "Milo";
		this.updateDir =3;
		
	}
	
	@Override
	protected void interact(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void atack(int i, int j) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void move(int i, int j,Room room) {
		room.move(this.i,this.j,i,j);
		this.i = i;
		this.j = j;

		
	}
	
	public void move(char dir,Room room) {
		
		int tI=0;
		int tJ=0;
		int newDir = updateDir;
		switch(dir)
		{
		case 'A':
			tI = i;
			tJ = j-1;
			newDir = 2;
			break;
		case 'S':
			tI = i-1;
			tJ = j;
			newDir = 1;
			break;
		case 'D':
			tI = i;
			tJ = j+1;
			newDir = 0;
			break;
		case 'W':
			tI = i+1;
			tJ = j;
			newDir = 3;
			break;
		}
		if(verifyMovement(tI,tJ,room))
			{
				move(tI,tJ,room);
				
			}
		this.updateDir = newDir;
	}

	@Override
	public void die() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hurt(int damage) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean verifyMovement(int i, int j, Room room) {
		if(room == null)
		{
			System.out.println("erro: sala é nula");
			return false;
		}
		if(room.isAccessible(i,j))
			return true;
		return false;
	}

	@Override
	public void update(double dt) {
        temp += velocidade_anim*dt;
		
	}

	@Override
	public void renderer(Renderer r) {
		GameRenderer.drawCharacter(i,j,elevation,name,r, (int)(temp%6), this.updateDir,"idle");
		
	}

	@Override
	public void toggleAnimation() {
		// TODO Auto-generated method stub
		
	}

}
