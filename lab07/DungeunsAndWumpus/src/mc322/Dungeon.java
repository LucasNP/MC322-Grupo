package mc322;

public class Dungeon implements BasicObject{
	public Sala salas[][];
	public Par pos; //posi��o do time no mapa
	public Dungeon()
	{
		ConstrutorMapa c = new ConstrutorMapa();
		salas = c.criarSalas("res\\mapa");
		this.pos = c.getOrigem();
	}
	
	public void render()
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
