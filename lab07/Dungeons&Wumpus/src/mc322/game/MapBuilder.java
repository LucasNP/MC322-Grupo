package mc322.game;

import mc322.engine.Pair;

public class MapBuilder {

      private final int tamanho = 18;
      private Room salas[][];
      private Pair origem;
      private String caminho;
      
      public Room[][] criarSalas(String caminho) {  //retorna um array de salas atraves do arquivo csv dado
            this.caminho = caminho;
      		Room rSalas[][] = new Room[tamanho][tamanho];  //cria o array de salas da dungeon
            this.salas = rSalas;
            
            for(int i = 0;i<tamanho;i++)  //inicializa as salas
            {
                  for(int j = 0;j<tamanho;j++)
                  {
                        rSalas[i][j]=new Room(this,new Par(i,j));
                  }
            }
            
            this.origem=new Par(0,0); //seta a origem
            
            return rSalas;
      }

      public Par getOrigem()
      {
            return origem;
      }

      public Pair<Entity, Entity>[][] buildTiles(Pair pos, String sala) {
            CSVHandling leitor = new CSVHandling();
            leitor.setDataSource("src/maps/sala/"+sala+".csv");
            String sMapa[][] = leitor.requestCommands();
            System.out.println("Sala "+sala);
            
            Entidade eMapa[][] = new Entidade[15][15];
            
            for(int i = 14; i >=0;i--)
            {
                  System.out.println("linha "+i);
                  for(int j = 0 ; j<15;j++)
                  {
                        System.out.println("coluna "+j);
                        System.out.println("i: "+sMapa[i]+"ij:"+sMapa[i][j]);
                        if(pos.a()==0 && pos.b() == 0)
                              System.out.println("adicionando "+sMapa[i][0].charAt(j)+" em "+i+", "+j);
                        eMapa[i][j]=EntityTilesLoader.getEntity(sMapa[i][0].charAt(MiscFunc.modulo(14-j)),i,j,decidirCanto(i,j));
                  }
            }
            
            
            
            //return eMapa;
            return null;
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
      
      public Entity[][] buildEntities(Pair pos) {
            
            return null;
      }
      
}
