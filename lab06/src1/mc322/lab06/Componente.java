package mc322.lab06;
import java.util.HashMap;
import java.util.Map;

public abstract class Componente{
      protected int prioridade;
      protected int i, j; // i = linha, j = coluna. i = y, j = x
      private char simbolo;

      private Map<Character, Integer> mapaComponentes = new HashMap<>();

      protected Componente(int i, int j, char simbolo){
            this.simbolo = simbolo;
            this.i=i;
            this.j=j;

            String componentes = "BWO";
            for(char c : componentes.toCharArray()) this.mapaComponentes.put(c, 3);
            this.mapaComponentes.put('P', 2);
            this.mapaComponentes.put('f', 1);
            this.mapaComponentes.put('b', 0);
            this.prioridade = mapaComponentes.get(simbolo);
    }
    
    public boolean setPos(int i, int j){
        this.i=i;
        this.j=j;
        return true;
    }
    
    public Par getPos(int i, int j){
        return new Par(i,j);
    }
    public int getPrioridade(){
        return prioridade;
    }
    public char getSimbolo(){
        return this.simbolo;
    }
}
