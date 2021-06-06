package mc322;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class Sala implements BasicObject {
	private Entidade entidades[][] = new Entidade[13][13];
	private Entidade terreno[][] = new Entidade[13][13];
	
	//texturas
	protected String inicioCaminho = "src/";
	private Image lWall = new ImageIcon(inicioCaminho+"tiles/Purple/tile_left_wall.png").getImage();
    private Image rWall = new ImageIcon(inicioCaminho+"tiles/Purple/tile_right_wall.png").getImage();
    private Image ground = new ImageIcon(inicioCaminho+"tiles/Purple/tile1.png").getImage();
    private Image pilar = new ImageIcon(inicioCaminho+"tiles/Purple/tile2.png").getImage();
	

	public Sala(ConstrutorMapa c, Par pos) //chamado em contrutorMapa
	{
		terreno = c.construirTerreno(pos);
		entidades = c.construirEntidades(pos);
	}
	
	public void render(Graphics g) { // deverá ser chamado no controle
		this.renderTerrain(g);
		for(int i =0;i<18;i++)
		{
			for(int j=0;j<18;j++)
			{
			if(terreno!=null)
				if(terreno[i][j] != null)
					terreno[i][j].render(g);
			}
		}
		for(int i =0;i<18;i++)
		{
			for(int j=0;j<18;j++)
			{
			if(entidades!=null)
				if(entidades[i][j] != null)
					entidades[i][j].render(g);
			}
		}
	}

	public void tick() {
		for(int i =0;i<18;i++)
		{
			for(int j=0;j<18;j++)
			{
			if(terreno!=null)
				if(entidades[i][j] != null)
					entidades[i][j].tick();
			}
		}
	}
	
	private void renderTerrain(Graphics g)
	{
		int lin = 14;
	  	int col = 14;

        for(int i = lin; i >= 0; i--){ // for para desenhar o chão
              for(int j = 0; j <= col; j++){
            	  MiscFunc.Desenha(i, j, ground, g);
              }
        }

        MiscFunc.Desenha(lin+1, -1, pilar, g); //desenha o pilar superior
        MiscFunc.Desenha(lin+2, -2, pilar, g);

        for(int i = lin-1; i > 0; i--){ // for para desenhar a parede esquerda
              if(i == 7) continue;
              MiscFunc.Desenha(i+1, -1, lWall, g);
              MiscFunc.Desenha(i+2, -2, lWall, g);
        }

        MiscFunc.Desenha(1, -1, pilar, g); // desenha o pilar esquerdo
        MiscFunc.Desenha(2, -2, pilar, g);

        for(int j = 1; j < col; j++){// for para desenhar a parede direita
              if(j == 7) continue;
              MiscFunc.Desenha(col+1, j-1, rWall, g);
              MiscFunc.Desenha(col+2, j-2, rWall, g);
        }

        MiscFunc.Desenha(col+1, col-1, pilar, g); // desenha pilar a direita
        MiscFunc.Desenha(col+2, col-2, pilar, g);
	}
	
}
