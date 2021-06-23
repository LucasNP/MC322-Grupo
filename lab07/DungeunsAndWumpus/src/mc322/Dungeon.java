package mc322;

import java.awt.Graphics;

public class Dungeon implements BasicObject{
	public Sala salas[][];
	public Par pos; //posição do time no mapa
	
	public Dungeon()
	{
		ConstrutorMapa c = new ConstrutorMapa();
		salas = c.criarSalas("res\\mapa");
		this.pos = c.getOrigem();
	}
	
	public void render(Graphics g)
	{
		
	}
	
	public void tick()
	{
		
	}
	
	public Sala getSalaAtual()
	{
		return salas[this.pos.a()][this.pos.b()];
	}
	
	
}
