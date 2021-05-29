package mc322;

public class Controle implements BasicObject{
	private Dungeon dg;
	public Controle()
	{
		this.dg = new Dungeon();
	}

	public void render() {
		this.dg.getSalaAtual().render();
		
	}

	public void tick() {
		this.dg.getSalaAtual().tick();
		
	}
}
