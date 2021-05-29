package mc322;


public class Sala implements BasicObject {
	private Entidade entidades[][] = new Entidade[13][13];
	private Entidade terreno[][] = new Entidade[13][13];
	private Par pos;

	public Sala(ConstrutorMapa c, Par pos)
	{
		this.pos = pos;
		terreno = c.construirTerreno(pos);
		entidades = c.construirEntidades(pos);
	}
	
	public void render() {
		for(int i =0;i<18;i++)
		{
			for(int j=0;j<18;j++)
			{
				if(terreno[i][j] != null)
					terreno[i][j].render();
			}
		}
		for(int i =0;i<18;i++)
		{
			for(int j=0;j<18;j++)
			{
				if(entidades[i][j] != null)
					entidades[i][j].render();
			}
		}
	}

	public void tick() {
		for(int i =0;i<18;i++)
		{
			for(int j=0;j<18;j++)
			{
				if(entidades[i][j] != null)
					entidades[i][j].tick();
			}
		}
	}
}
