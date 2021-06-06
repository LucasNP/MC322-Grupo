package mc322.terreno;

import java.awt.Graphics;

import mc322.Entidade;
import mc322.MiscFunc;

public class Escada extends Entidade{

	public Escada(int i, int j,boolean NS)
	{
		this.i = i;
		this.j = j;
		if(NS)
			this.caminho = "tiles/Purple/tile_ladder_right.png";
		else
			this.caminho = "tiles/Purple/tile_ladder_left.png";
	}
	
	public void render(Graphics g) {
		MiscFunc.Desenha(i, j, img(), g);
	}

	public void tick() {
		
	}

}
