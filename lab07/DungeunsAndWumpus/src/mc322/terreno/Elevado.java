package mc322.terreno;

import java.awt.Graphics;

import mc322.Entidade;
import mc322.MiscFunc;

public class Elevado extends Entidade{

	public Elevado(int i, int j)
	{
		this.i=i;
		this.j= j;
		this.caminho = "tiles/Purple/tile2.png";
	}
	
	public void render(Graphics g) {
		MiscFunc.Desenha(i, j, img(), g);
	}

	public void tick() {
		
	}

}
