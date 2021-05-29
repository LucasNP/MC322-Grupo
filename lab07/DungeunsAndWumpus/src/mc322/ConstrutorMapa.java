package mc322;

public class ConstrutorMapa {

	private final int tamanho = 18;
	private Sala salas[][];
	private Par origem;
	private String caminho;
	
	public Sala[][] criarSalas(String caminho) {
		this.caminho = caminho;
		Sala rSalas[][] = new Sala[tamanho][tamanho];
		this.salas = rSalas;
		this.origem=new Par(0,0);
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
