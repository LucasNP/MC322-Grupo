package mc322;

public class ConstrutorMapa {

	private final int tamanho = 18;
	private Sala salas[][];
	private Par origem;
	private String caminho;
	
	public Sala[][] criarSalas(String caminho) { // retorna um array de salas atraves do arquivo csv dado
		this.caminho = caminho;
		Sala rSalas[][] = new Sala[tamanho][tamanho]; // cria o array de salas da dungeon
		this.salas = rSalas;
		
		for(int i = 0;i<tamanho;i++) // inicializa as salas
		{
			for(int j = 0;j<tamanho;j++)
			{
				rSalas[i][j]=new Sala(this,new Par(i,j));
			}
		}
		
		this.origem=new Par(0,0); //seta a origem
		
		return rSalas;
	}

	public Par getOrigem()
	{
		return origem;
	}

	public Entidade[][] construirTerreno(Par pos) {
		
		return null;
	}

	public Entidade[][] construirEntidades(Par pos) {
		
		return null;
	}
	
}
