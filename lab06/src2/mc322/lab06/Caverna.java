package mc322.lab06;
import java.util.HashMap;
import java.util.Map;
import mc322.lab06.componentes.Player;
import mc322.lab06.HUD;

public class Caverna{
      private Sala caverna[][];
      private Player jogador;
      private HUD hud;
      private int graficos;
      private String cavernaString;

      Caverna(String caminhoInput, int graficos, String nomeJogador){
            MontadorCaverna montador = new MontadorCaverna(caminhoInput);
            this.cavernaString = "";
            this.graficos = graficos;
            this.caverna = montador.retornaCavernaMontada(); 

            this.hud = new HUD(nomeJogador, graficos);
            this.jogador = (Player) getSalaAt(1,1).olhaTopo();
            this.jogador.setNome(nomeJogador);
            converterCaverna();
      }

      public Player receberPlayer(){
            return this.jogador;
      }
      public HUD receberHUD(){
            return this.hud;
      }

      public void mover(int i0,int j0,int i,int j){
            this.caverna[i][j].adicionarComponente(this.caverna[i0][j0].removerTopo());
            this.caverna[i][j].visitar();
      }

      public char getSimbolo(int i, int j){
            if(caverna[i][j].estaVazia()) return caverna[i][j].getSimboloVazia();
            return caverna[i][j].getSimbolo();
      }

      public Sala getSalaAt(int i, int j){
            return caverna[i][j];
      }

      public void converterCaverna(){
            String parede  = "   ",
                   parede2 = "   ",
                   porta   = "   ",
                   porta2  = "   ";

            Map<Character, Character> map = new HashMap<>();
            String simbolos = "BWOP_#bf";
            for(char c : simbolos.toCharArray()) map.put(c, c);
            
            if(this.graficos == 1){
                  map.put('B', '\u25EF');
                  map.put('W', '\u26C7');
                  map.put('O', '\u272A');

                  map.put('P', '\u26D1');
                  map.put('_', '\u2B1A');
                  map.put('#', '\u25A0');
                  
                  map.put('b', '\u2248');
                  map.put('f', '\u2668');

                  parede  = " \u25A3 ";
                  parede2 = " \u25A3 ";
                  porta   = " \u2E3D ";
                  porta2  = " \u2D67 ";
            }

            this.cavernaString += "    ";
            for(int k = 1; k < 2*this.caverna.length; k++) {
                  if(k % 2 == 1) this.cavernaString += parede2;
                  else this.cavernaString += parede;
            }
            this.cavernaString += '\n';

            for(int i = 1; i < this.caverna.length; i++){
                  this.cavernaString += " " + i + "  ";
                  for(int j = 1; j < this.caverna.length; j++){
                        if(j == 1) this.cavernaString += parede2;
                        else this.cavernaString += porta;
                        this.cavernaString += " " + map.get(this.getSimbolo(i, j)) + " ";
                  }
                  this.cavernaString += parede2;
                  if(i == this.caverna.length - 1) break;
                  this.cavernaString += "\n    ";
                  for(int k = 1; k < 2*this.caverna.length; k++) {
                        if(k % 2 == 1) this.cavernaString += parede;
                        else this.cavernaString += porta2;
                  }
                  this.cavernaString += "\n";
            }
            this.cavernaString += "\n";

            this.cavernaString += "    ";
            for(int k = 1; k < 2*this.caverna.length; k++) {
                  if(k % 2 == 1) this.cavernaString += parede2;
                  else this.cavernaString += parede;
            }
            this.cavernaString += "\n\n";
            this.cavernaString += "      " + " ";
            for(int k = 1; k < this.caverna.length; k++) this.cavernaString += "  "+k+"   ";
            this.cavernaString += '\n';
            this.cavernaString += this.hud.receberFrase();

      }

      public String imprimirCaverna(){
            this.cavernaString = "";
            converterCaverna();
            return this.cavernaString;
      }
}
