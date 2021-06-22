package mc322.game.itens;

import mc322.engine.Renderer;

public class Key extends Item{

	String Color;
	public Key(String Color)
	{
		this.Color = Color;
		switch(Color)
		{
		case "Black":
			this.updateDir = 0;
			break;
		case "Purple":
			this.updateDir = 1;
			break;
		case "Yellow":
			this.updateDir = 2;
			break;
		case "Red":
			this.updateDir = 3;
			break;
		case "Green":
			this.updateDir = 4;
			break;
		case "Blue":
			this.updateDir = 5;
			break;
		default:
			System.err.println("The color of the key is wrong: " + Color);
			break;
		}
	}
	
	@Override
	public void update(double dt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void renderer(Renderer r) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void toggleAnimation() {
		// TODO Auto-generated method stub
		
	}

	public String toString()
	{
		return ""+this.Color+" key";
	}
}
