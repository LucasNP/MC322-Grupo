package mc322.lab06;
import java.util.HashMap;
import java.util.Map;
import mc322.lab06.componentes.Player;

public class MontadorCaverna{
      private Sala cavernaMontada[][];
      private boolean deubomCaverna;

      MontadorCaverna(String caminhoInput){
            this.deubomCaverna = true;
            CSVHandling csv = new CSVHandling();
            csv.setDataSource(caminhoInput);            
            String stringCaverna[][] = csv.requestCommands();

            this.deubomCaverna = verificaCaverna(stringCaverna);

            cavernaMontada = new Sala[5][5];
            if(deubomCaverna) criaCaverna(stringCaverna);
            else System.out.println("Erro ao criar Caverna :( -- formato de Input Inválido");
      }

      boolean verificaCaverna(String stringCaverna[][]){
            if(stringCaverna[0][1].charAt(0) != 'P') return false;

            Map<Character, Integer> mapaComponentes = new HashMap<>();
            String componentes = "BWOP";
            for(char c : componentes.toCharArray()) mapaComponentes.put(c, 0);

            for(int i = 0; i<stringCaverna.length; i++){
                  if(mapaComponentes.get(stringCaverna[i][1].charAt(0)) != null){
                        int novoValor = mapaComponentes.get(stringCaverna[i][1].charAt(0)) + 1;
                        mapaComponentes.put(stringCaverna[i][1].charAt(0), novoValor);
                  }
            }

            boolean condicoes[] = new boolean[4];

            condicoes[0] = (mapaComponentes.get('B') >= 2) && (mapaComponentes.get('B') <= 3);
            condicoes[1] = mapaComponentes.get('W') == 1;
            condicoes[2] = mapaComponentes.get('O') == 1;
            condicoes[3] = mapaComponentes.get('P') == 1;

            for(int i = 0; i < 4; i++) this.deubomCaverna &= condicoes[i];
            return this.deubomCaverna;
      }

      void criaCaverna(String stringCaverna[][]){
            for(int k = 0; k < stringCaverna.length; k++){
                  int i = Character.getNumericValue( stringCaverna[k][0].charAt(0) );
                  int j = Character.getNumericValue( stringCaverna[k][0].charAt(2) );

                  char simbolo = stringCaverna[k][1].charAt(0);

                  boolean ocupada = false;
                  if(simbolo != '_') ocupada = true; 
                  this.cavernaMontada[i][j] = new Sala(i, j, ocupada, simbolo);
            }

            for(int k = 0; k < stringCaverna.length; k++){
                  int i = Character.getNumericValue( stringCaverna[k][0].charAt(0) );
                  int j = Character.getNumericValue( stringCaverna[k][0].charAt(2) );

                  char simbolo = stringCaverna[k][1].charAt(0);

                  if(simbolo == 'B' || simbolo == 'W'){
                        char simbolo_arredores = 'f';
                        if(simbolo == 'B') simbolo_arredores = 'b';

                        int dr[] = {1, 0, -1, 0, 1};
                        for(int a = 0; a < 4; a++){
                              int pi = i + dr[a];
                              int pj = j + dr[a+1];

                              boolean safe = ( pi>0 && pj<5 && pj>0 && pi <5 );
                              if( safe ){
                                    boolean c1 = ( cavernaMontada[pi][pj] != null ); 
                                    char c_aux = cavernaMontada[pi][pj].getSimbolo();
                                    if(c1 && !cavernaMontada[pi][pj].seOcupada() ){
                                          this.cavernaMontada[pi][pj] = new Sala(pi, pj, false, simbolo_arredores);
                                    }
                              }
                        }
                  }
            }
      }

      Sala[][] retornaCavernaMontada(){
            return this.cavernaMontada;
      }
}

