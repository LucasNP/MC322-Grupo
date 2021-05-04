package mc322.lab05;

public class Peao extends Peca{
    private static int diretor(int num){ return (num/(Math.abs(num))); }
    
    Peao(Tabuleiro tab, char simbolo, int i, int j){
          super(tab, simbolo, i, j);
    }
    
    public RespostaDoMovimento mover(int xAlvo, int yAlvo){
          RespostaDoMovimento res = new RespostaDoMovimento();
          res.possivel = false;

          if(xAlvo < 0 || xAlvo > 7 || yAlvo < 0 || yAlvo > 7) return res;

          if( this.cima && (yAlvo == this.y - 1)) return res; // preta
          if( !this.cima && (yAlvo == this.y + 1)){
                System.out.println(yAlvo + " " + this.y);
                return res; // branca
          }

          if(Math.abs(xAlvo - this.x) != Math.abs(yAlvo - this.y)) return res;
          if(tab.lerPos(xAlvo, yAlvo) != '-') return res;

          int dx = diretor(xAlvo - this.x);
          int dy = diretor(yAlvo - this.y);

          int tempX = this.x;
          int tempY = this.y;

          int k = 0;
          while(tempX != xAlvo){
                k++;
                tempX += dx;
                tempY += dy;

                if(tab.lerPos(tempX, tempY) != '-' && k == 1){
                      if(descobrirTime(tab.lerPos(tempX, tempY)) == this.cima) return res;
                      else res.comerPeca(new Par(((xAlvo+x)/2),((yAlvo+y)/2)));
                }
                else{
                      if(k > 2) return res;
                }
          }

          this.x = xAlvo;
          this.y = yAlvo;


          if(this.cima && this.y == 7) res.promover = true;
          if(!this.cima && this.y == 0) res.promover = true;

          res.possivel = true;
          return res;
    }

}

