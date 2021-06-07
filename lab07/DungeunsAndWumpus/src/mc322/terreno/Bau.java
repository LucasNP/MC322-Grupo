package mc322.terreno;

import java.awt.Graphics;

import mc322.Entidade;
import mc322.MiscFunc;

public class Bau extends Entidade{

	public Bau(int i, int j)
	{
		this.i = i;
		this.j = j;
		this.caminho = "tiles/Purple/campfire1.png";
	}
	
	public void render(Graphics g) {
		MiscFunc.Desenha(i, j, img(), g);
	}

	public void tick() {
		
	}

}
