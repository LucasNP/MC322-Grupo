package mc322;


public class Sala implements BasicObject {
	Entidade entidades[][] = new Entidade[13][13];
	Entidade terreno[][] = new Entidade[13][13];

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
