<<<<<<< HEAD
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
=======
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
>>>>>>> b694b8c1d5d6f4fb736048082edf2d4fcd72884b
