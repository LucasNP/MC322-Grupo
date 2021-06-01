package mc322;

import java.awt.Graphics;

public class Controle implements BasicObject{
	private Dungeon dg;
	public Controle()
	{
		this.dg = new Dungeon();
	}

	public void render(Graphics g) {
		this.dg.getSalaAtual().render(g);
		
	}

	public void tick() {
		this.dg.getSalaAtual().tick();
		
	}
}
