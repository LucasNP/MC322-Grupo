package mc322;

import java.awt.Image;

import javax.swing.ImageIcon;

public abstract class Entidade implements BasicObject{
	protected String caminho;
	protected int i;
	protected int j;
	protected String inicioCaminho = "src/";
	protected Image img;
	
	protected Image img()
	{
		img = new ImageIcon(inicioCaminho+caminho).getImage();
		return img;
	}
	
}
