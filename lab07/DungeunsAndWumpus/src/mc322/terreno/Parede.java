package mc322.terreno;

import java.awt.Graphics;

import mc322.Entidade;
import mc322.MiscFunc;

public class Parede extends Entidade{

	public Parede(int i, int j,boolean meio, char direcao)
	{
		this.i=i;
		this.j= j;
		if(meio)
		{
			if(direcao == 'N' || direcao =='n'|| direcao =='S'|| direcao =='s')
				this.caminho = "tiles/Purple/tile_side_wall_left.png";
			else
				this.caminho = "tiles/Purple/tile_side_wall_right.png";
		}
		else
		{
			if(direcao == 'N' ||direcao == 'n')
				this.caminho = "tiles/Purple/tile_right_wall.png";
			else if(direcao == 'O' ||direcao == 'o')
				this.caminho = "tiles/Purple/tile_left_wall.png";
			else
				this.caminho = "";
		}
		
		
	}
	
	public void render(Graphics g) {
		MiscFunc.Desenha(i, j, img(), g);
		MiscFunc.Desenha(i-1, j+1, img(), g);
	}

	public void tick() {
		
	}

}
