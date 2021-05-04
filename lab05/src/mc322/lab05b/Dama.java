package mc322.lab05;

public class Dama extends Peca{
    private boolean cima; 
    private static int diretor(int num){ return (num/(Math.abs(num)));
    }
    
    Dama(Tabuleiro tab, char simbolo, int i, int j){
          super(tab, simbolo, i, j);
    }
    
    public RespostaDoMovimento mover(int xAlvo, int yAlvo){
          RespostaDoMovimento res = new RespostaDoMovimento();
          res.possivel = false;
          
          if(xAlvo < 0 || xAlvo > 7 || yAlvo < 0 || yAlvo > 7) return res;
          if(Math.abs(xAlvo - this.x) != Math.abs(yAlvo - this.y)) return res;
          if(tab.lerPos(xAlvo, yAlvo) != '-') return res;

          int dx = diretor(xAlvo - this.x);
          int dy = diretor(yAlvo - this.y);

          int tempX = this.x;
          int tempY = this.y;

          boolean possoCume = true;

          while(tempX != xAlvo){
                tempX += dx;
                tempY += dy;
                
                if(tab.lerPos(tempX, tempY) != '-'){
                      if(!possoCume) return res;
                      if(descobrirTime(tab.lerPos(tempX, tempY)) == this.cima) return res;
                      else{
                            res.comerPeca(new Par(((xAlvo+x)/2),((yAlvo+y)/2)));
                            possoCume = false;
                      }
                }
                else possoCume = true;
          }

          this.x = xAlvo;
          this.y = yAlvo;

          res.possivel = true;
          return res;
    }

}
