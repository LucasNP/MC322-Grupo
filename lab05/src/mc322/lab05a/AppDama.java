package mc322.lab05;

public class AppDama {
      String source;
      String target;

      private static String[] lerComandos(String caminho){
            CSVReader csv = new CSVReader();
            csv.setDataSource(caminho);
            return csv.requestCommands();
      }

      public String[] executaJogo(String caminhoComandos, String caminhoTabuleiro, String caminhoOutput){
            Tabuleiro tab = new Tabuleiro(caminhoTabuleiro);
            String comandos[] = lerComandos(caminhoComandos);
            String EstadosDoJogo[] = new String[comandos.length];

            System.out.println("Tabuleiro inicial:");
            System.out.println(tab.imprimirTabuleiro()+"\n");

            for(int i = 0; i < comandos.length; i++){
                  System.out.println("Source: " + comandos[i].charAt(0) + comandos[i].charAt(1));
                  System.out.println("Target: " + comandos[i].charAt(3) + comandos[i].charAt(4));

                  tab.executarComando(comandos[i]);
                  EstadosDoJogo[i] = tab.imprimirTabuleiro();
                  System.out.println(EstadosDoJogo[i]+"\n");
            }

            tab.exportarArquivo(caminhoOutput);
            return EstadosDoJogo;
      }
}
