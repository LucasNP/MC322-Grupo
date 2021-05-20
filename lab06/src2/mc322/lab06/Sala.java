package mc322.lab06;
import java.util.Comparator;
import java.util.PriorityQueue;
import mc322.lab06.componentes.*;

public class Sala{
      private PriorityQueue <Componente> espaco;
      private Player jogador;
      private boolean visitada;
      private boolean ocupada;
      private int linha;
      private int coluna;

      Sala(int linha, int coluna, boolean ocupada){
            this.linha = linha;
            this.coluna = coluna;
            this.ocupada = ocupada;
            this.visitada = false;
            this.espaco = new PriorityQueue <Componente>(new ComponenteComparator()); 
      }

      //sobrecarga para caso passe um simbolo
      Sala(int linha, int coluna, boolean ocupada, char simbolo){
            this(linha, coluna, ocupada);
            switch(simbolo){
                  case 'P':
                        this.jogador = new Player(linha,coluna, simbolo);
                        this.adicionarComponente(this.jogador);
                        this.visitada = true;
                        break;
                  case 'b':
                        this.adicionarComponente(new Aviso(linha,coluna,simbolo));
                        break;
                  case 'f':
                        this.adicionarComponente(new Aviso(linha,coluna,simbolo));
                        break;
                  case 'B':
                        this.adicionarComponente(new Buraco(linha,coluna,simbolo));
                        break;
                  case 'W':
                        this.adicionarComponente(new Wumpus(linha,coluna,simbolo));
                        break;
                  case 'O':
                        this.adicionarComponente(new Ouro(linha,coluna,simbolo));
                        break;
                  case '_':
                        break;
                  default:
                        System.out.println("Erro ao criar um componente na sala, simbolo invalido!");
                        break;
            }
      }

      public void visitar(){
            this.visitada = true;
      }
      public boolean seOcupada(){
            return this.ocupada;
      }

      public Componente removerTopo(){
            return this.espaco.poll();
      }
      public Componente olhaTopo(){
            return this.espaco.peek();
      }

      public void adicionarComponente(Componente componente){
            this.espaco.add(componente);
      }

      char getSimbolo(){
            if(!visitada) return '_';
            if(espaco.peek() == null) return '#';
            return espaco.peek().getSimbolo();
      }
      char getSimboloVazia(){
            if(this.visitada == false) return '_';
            else return '#';
      }

      class ComponenteComparator implements Comparator<Componente>{
            public int compare(Componente c1, Componente c2) {
                  if (c1.getPrioridade() < c2.getPrioridade()) return 1;
                  else if (c1.getPrioridade() > c2.getPrioridade()) return -1;
                  return 0;
            }
      }

      public char Segundo(){
            Componente tmp = this.removerTopo();
            char res = this.getSimbolo();
            this.adicionarComponente(tmp);
            return res;
      }
      
      public boolean estaVazia(){
            return (espaco.peek()==null);
      }
}
