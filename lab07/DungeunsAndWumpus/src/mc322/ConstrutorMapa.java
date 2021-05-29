package mc322;

public class ConstrutorMapa {

	private final int tamanho = 18;
	private Sala salas[][];
	private Par origem;
	
	public Sala[][] criarSalas() {
		Sala rSalas[][] = new Sala[tamanho][tamanho];
		this.salas = rSalas;
		this.origem=new Par(0,0);
		return rSalas;
	}

	public Par getOrigem()
	{
		return origem;
	}
}
