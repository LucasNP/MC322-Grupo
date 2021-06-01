package mc322;

import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Entidade implements BasicObject{
	private String caminho;
	private Image img= new ImageIcon(caminho).getImage();
	private int i;
	private int j;
	
}
