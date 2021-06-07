<<<<<<< HEAD
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

	public Entidade[][] construirTerreno(Par pos, String sala) {
		CSVHandling leitor = new CSVHandling();
		leitor.setDataSource("src/maps/sala/"+sala+".csv");
		String sMapa[][] = leitor.requestCommands();
		//System.out.println("Sala "+sala);
		
		Entidade eMapa[][] = new Entidade[15][15];
		
		for(int i = 14; i >=0;i--)
		{
			//System.out.println("linha "+i);
			for(int j = 0 ; j<15;j++)
			{
				//System.out.println("coluna "+j);
				//System.out.println("i: "+sMapa[i]+"ij:"+sMapa[i][j]);
				//if(pos.a()==0 && pos.b() == 0)
					//System.out.println("adicionando "+sMapa[i][0].charAt(j)+" em "+i+", "+j);
				eMapa[i][j]=GameDictionary.getTerreno(sMapa[i][0].charAt(MiscFunc.modulo(14-j)),i,j,decidirCanto(i,j));
			}
		}
		
		
		
		return eMapa;
	}

	private char decidirCanto(int i, int j)
	{
		if(j==0)
			return 'n';
		if(j == 14)
			return 's';
		if(i== 0)
			return 'o';
		return 'e';
	}
	
	public Entidade[][] construirEntidades(Par pos) {
		
		return null;
	}
	
}
=======
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
>>>>>>> b694b8c1d5d6f4fb736048082edf2d4fcd72884b
