package mc322.lab06.componentes;
import mc322.lab06.Caverna;
import mc322.lab06.Componente;
import mc322.lab06.HUD;
import mc322.lab06.FastScanner;
import java.util.HashMap;
import java.util.Map;

public class Player extends Componente{
    private int acao;
    private HUD hud;
    private Caverna caverna;

    public Player(int i, int j, char simbolo, HUD hud) {
          super(i, j, simbolo);
          this.acao = 10;
          this.hud = hud;
    }
    
    public int decidir(){
          FastScanner fs = new FastScanner();
          String input = fs.next();
          input = input.toLowerCase();

          Map<Character, Integer> mapaKeys = new HashMap<>();
          String keyboard = "wadskcq";
          for(int k = 0; k <= 6; k++) mapaKeys.put(keyboard.charAt(k), k);
          if( mapaKeys.get(input.charAt(0)) != null){
              this.acao = mapaKeys.get(input.charAt(0));
          }
          return this.acao;
    }

    public int getI(){
          return this.i;
    }

    public int getJ(){
          return this.j;
    }

    public void setCaverna(Caverna caverna){
          this.caverna = caverna;
    }

    public void mover(int i, int j) {
          if(i<1 || i>4 || j<1 || j>4) return;
          //System.out.println(this.i + " " + this.j + " " + i + " " + j + " ");
          caverna.mover(this.i,this.j, i, j);
          this.i = i;
          this.j = j;
    }
    
    public HUD getHud()
    {
    	return this.hud;
    }
    
    public Caverna getCaverna()
    {
    	return this.caverna;
    }

}
