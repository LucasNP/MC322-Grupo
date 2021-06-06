package mc322.terreno;

import java.awt.Graphics;

import mc322.Entidade;
import mc322.MiscFunc;

public class Pilar extends Entidade{

	public Pilar(int i, int j)
	{
		this.i=i;
		this.j= j;
		this.caminho = "tiles/Purple/tile2.png";
	}
	
	public void render(Graphics g) {
		MiscFunc.Desenha(i, j, img(), g);
		MiscFunc.Desenha(i-1, j+1, img(), g);
	}

	public void tick() {
		
	}

}
