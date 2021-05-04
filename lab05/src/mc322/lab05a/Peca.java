package mc322.lab05;

public abstract class Peca {
    protected char simbolo;
    protected Tabuleiro tab;
    protected int y, x;
    protected boolean cima;
    
    Peca(Tabuleiro tab, char simbolo, int y, int x){
          this.y = y;
          this.x = x;
          this.tab = tab;
          this.simbolo = simbolo;
          if(simbolo == 'p'|| simbolo == 'P') this.cima = true;
          else this.cima = false;
    }
    
    public RespostaDoMovimento mover(int xAlvo, int yAlvo){
          RespostaDoMovimento res = new RespostaDoMovimento();
          res.possivel = false;
          return res;
    }
    
    public char getSimbolo(){        
            return simbolo;
    }
    
    public static boolean descobrirTime(char simbolo){
          if(simbolo == 'p'||simbolo == 'P')return true;
          return false;
    }
}
