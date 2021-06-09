//package mc322.terreno;

//import java.awt.Graphics;
//import java.awt.Image;

//import javax.swing.ImageIcon;

//import mc322.Entidade;
//import mc322.MiscFunc;

//public class Parede extends Entidade{

	
	//private String caminho2;
	//private boolean elevado;
	//public Parede(int i, int j,boolean meio, char direcao)
	//{
		//this.i=i;
		//this.j= j;
		//elevado = false;
		//if(meio)
		//{
			//if(direcao == 'n' || direcao =='s')
				//this.caminho = "tiles/Purple/tile_side_wall_right.png";
			//else if(direcao == 'o' || direcao =='e')
				//this.caminho = "tiles/Purple/tile_side_wall_left.png";
			//else if(direcao == 'N' || direcao =='S')
			//{
				//this.caminho = "tiles/Purple/tile_side_wall_right.png";
				//this.caminho2 = "tiles/Purple/tile2.png";
				//elevado = true;
			//}
			//else
			//{
				//elevado = true;
				//this.caminho = "tiles/Purple/tile_side_wall_left.png";
				//this.caminho2 = "tiles/Purple/tile2.png";
			//}
			
		//}
		//else
		//{
			//if(direcao == 'N' ||direcao == 'n')
				//this.caminho = "tiles/Purple/tile_right_wall.png";
			//else if(direcao == 'O' ||direcao == 'o')
				//this.caminho = "tiles/Purple/tile_left_wall.png";
			//else
				//this.caminho = "";
		//}
		
		
	//}
	
	//private Image img2()
	//{
		//return new ImageIcon(inicioCaminho+caminho2).getImage();
	//}
	
	//public void render(Graphics g) {
		//if(elevado)
			//MiscFunc.Desenha(i, j, 0,img2(), g);
		//else
			//MiscFunc.Desenha(i, j, 0,img(), g);
		//MiscFunc.Desenha(i, j,1, img(), g);
	//}

	//public void tick() {
		
	//}

//}
