package mc322.lab05;

public class Tabuleiro{
      Peca tabuleiro[][];
      int Vez;

      Tabuleiro(String caminhoTabuleiro){    	
          this.Vez = 1;
          this.tabuleiro = carregarTabuleiro(caminhoTabuleiro);
      }

      private Peca[][] montarTabuleiro(String stringTabuleiro[]){
            Peca tab[][] = new Peca[8][8];
            for(int i = 0; i < 8; i++) {
                  for(int j = 0; j < 8; j++) {
                        if(stringTabuleiro[i].charAt(j) == '-') tab[i][j] = null;
                        else tab[i][j] = new Peao(this, stringTabuleiro[i].charAt(j), i, j);
                  }
            }
            return tab;
      }

      private Peca[][] carregarTabuleiro(String caminho){
            CSVReader csv = new CSVReader();
            csv.setDataSource(caminho);
            String stringTabuleiro[] = csv.requestCommands();
            return montarTabuleiro(stringTabuleiro);
	}

      public String imprimirTabuleiro(){
            String tabuleiroConvertido = "";
            for(int i = 0; i < tabuleiro.length; i++){
                  tabuleiroConvertido += (tabuleiro.length - i) + "  ";
                  for(int j = 0; j < tabuleiro.length; j++){
                        if(tabuleiro[i][j] == null)  tabuleiroConvertido += "- "; 
                        else tabuleiroConvertido += tabuleiro[i][j].getSimbolo() + " ";
                  }
                  tabuleiroConvertido += "\n";
            }
            String alfabeto = "abcdefgh";
            tabuleiroConvertido += "  ";
            for(int j = 0; j < tabuleiro.length; j++){
                  tabuleiroConvertido += " " + alfabeto.charAt(j);
            }
            return tabuleiroConvertido;
      }

      public char lerPos(int j, int i){
            if(i<0||i>7||j<0||j>7) return '#';
            if(tabuleiro[i][j] == null) return '-';
            return tabuleiro[i][j].simbolo;
      }

      public void exportarArquivo(String caminhoDoTabuleiro){
            String estadoDoTabuleiro[] = new String[64];
            String alfabeto = "abcdefgh";
            int k = 0;
            for(int i = 0; i < tabuleiro.length; i++){
                  for(int j = 0; j < tabuleiro.length; j++){
                        char letra = alfabeto.charAt(i);
                        int num = (j+1);
                        char simbolo = lerPos(j, i);
                        estadoDoTabuleiro[k] = ( letra + "" + num + simbolo);
                        k++;
                  }
            }
            CSVHandling csv = new CSVHandling();
            csv.setDataExport(caminhoDoTabuleiro);
            csv.exportState(estadoDoTabuleiro);
      } 

      public void executarComando(String comando){
            int jOrigem = (comando.charAt(0) - 'a');
            int iOrigem = 8 - ((comando.charAt(1) - '0'));
            
            int jDestino = (comando.charAt(3) - 'a');
            int iDestino = 8 - ((comando.charAt(4) - '0'));

            System.out.println(iOrigem + " " + jOrigem + "  " + iDestino + " " + jDestino);

            if(tabuleiro[iOrigem][jOrigem] == null){
                  System.out.println("Movimento Inválido: Não é Peça");
                  return;
            }

            RespostaDoMovimento resp = new RespostaDoMovimento();
            resp = tabuleiro[iOrigem][jOrigem].mover(jDestino, iDestino);
            
            if(resp.possivel == true){
                  tabuleiro[iDestino][jDestino] = tabuleiro[iOrigem][jOrigem];
                  tabuleiro[iOrigem][jOrigem]   = null;
                  for(Par par : resp.comidas) tabuleiro[par.y][par.x] = null;

                  if(resp.promover == true){
                        char c = Character.toUpperCase(tabuleiro[iDestino][jDestino].simbolo);
                        tabuleiro[iDestino][jDestino] = new Dama(this, c, iDestino, jDestino);
                  }
            }
            else{
                  System.out.println("Movimento Inválido");
            }
      } 
     
}
