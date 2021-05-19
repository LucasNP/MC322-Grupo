package mc322.lab06;
import java.util.HashMap;
import java.util.Map;

import mc322.lab06.componentes.Player; 
public class Caverna{
      private Sala caverna[][];
      private Player jogador;
      private String cavernaString;

      Caverna(String caminhoInput){
            MontadorCaverna montador = new MontadorCaverna(caminhoInput);
            this.cavernaString = "";
            this.caverna = montador.retornaCavernaMontada(); 
            this.jogador = (Player) caverna[1][1].removerTopo();
            caverna[1][1].adicionarComponente(this.jogador);
            converterCaverna();
      }

      public Player receberPlayer(){
          return this.jogador;
      }

      public void mover(int i0,int j0,int i,int j){
            //System.out.println( this.caverna[i0][j0].getSimbolo() );
    	  Componente tmp = null;
    	  	if(this.caverna[i0][j0].getSimbolo(true)=='O')
    	  	{
    	  		tmp = this.caverna[i0][j0].removerTopo();
    	  	}
            this.caverna[i][j].adicionarComponente(this.caverna[i0][j0].removerTopo());
            if(tmp!=null)
            	this.caverna[i0][j0].adicionarComponente(tmp);
            this.caverna[i][j].visitar();
      }

      public char getSimbolo(int i, int j){
            if(caverna[i][j] == null) return caverna[i][j].getSimboloVazia();
            return caverna[i][j].getSimbolo(true);
      }

      public void converterCaverna(){
          String parede  = "   ";

           Map<Character, Character> map = new HashMap<>();
           String simbolos = "BWOP_#bf";
           for(char c : simbolos.toCharArray()) map.put(c, c);
           


           this.cavernaString += "    ";
                this.cavernaString += parede;
           this.cavernaString += '\n';

           for(int i = 1; i < this.caverna.length; i++){
                 this.cavernaString += " " + i + "  ";
                 for(int j = 1; j < this.caverna.length; j++){
                	 this.cavernaString += parede;
                       this.cavernaString += " " + map.get(this.getSimbolo(i, j)) + " ";
                 }
                 this.cavernaString += parede;
                 if(i == this.caverna.length - 1) break;
                 this.cavernaString += "\n    ";
                 this.cavernaString += parede;
                 this.cavernaString += "\n";
           }
           this.cavernaString += "\n";

           this.cavernaString += "    ";
           for(int k = 1; k < 2*this.caverna.length; k++) {
                 if(k % 2 == 1) this.cavernaString += parede;
                 else this.cavernaString += parede;
           }
           this.cavernaString += "\n\n";
           this.cavernaString += "      " + " ";
           for(int k = 1; k < this.caverna.length; k++) this.cavernaString += "  "+k+"   ";
           this.cavernaString += '\n';

     }

      public String imprimirCaverna(){
            this.cavernaString = "";
            converterCaverna();
            return this.cavernaString;
      }
      
      public Sala getSalaAt(int i, int j)
      {
    	  return this.caverna[i][j];
      }
      
}
