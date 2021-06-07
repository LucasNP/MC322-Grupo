package mc322.terreno;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import mc322.Entidade;
import mc322.MiscFunc;

public class Bau extends Entidade{

	private boolean elevado;
	String caminho2;
	
	public Bau(int i, int j, boolean segundoNivel)
	{
		elevado = false;
		if(segundoNivel)
		{
			elevado = true;
			this.caminho2 = "tiles/Purple/tile2.png";
		}
		this.i = i;
		this.j = j;
		this.caminho = "tiles/Purple/campfire1.png";
	}
	
	public void render(Graphics g) {
		if(elevado)
		{
			MiscFunc.Desenha(i,j,0,img2(),g);
			MiscFunc.Desenha(i, j,1, img(), g);
		}
		else
			MiscFunc.Desenha(i, j,0, img(), g);
	}

	public void tick() {
		
	}
	
	private Image img2()
	{
		return new ImageIcon(inicioCaminho+caminho2).getImage();
	}

}
