package mc322.terreno;

import java.awt.Graphics;

import mc322.Entidade;
import mc322.MiscFunc;

public class Porta extends Entidade{

	char direcao;
	public Porta(int i, int j, char direcao)
	{
		this.i=i;
		this.j= j;
		this.direcao = direcao;
			if(direcao == 'N' ||direcao == 'n')
				this.caminho = "tiles/Purple/metal_door_right1.png";
			else if(direcao == 'O' ||direcao == 'o')
				this.caminho = "tiles/Purple/metal_door_left1.png";
			else if(direcao == 'S' ||direcao == 's')
				this.caminho = "tiles/Purple/metal_door_down1.png";
			else
				this.caminho = "tiles/Purple/metal_door_down7.png";
		
		
	}
	
	public void render(Graphics g) {
		
		if(direcao == 'N' ||direcao == 'n')
			MiscFunc.Desenha(i, j,1, img(), g);
		else if(direcao == 'O' ||direcao == 'o')
			MiscFunc.Desenha(i, j,1, img(), g);
		else if(direcao == 'S' || direcao == 's')
			MiscFunc.Desenha(i, j,0, img(), g);
		else
			MiscFunc.Desenha(i, j,0, img(), g);
		
	}

	public void tick() {
		
	}

}
