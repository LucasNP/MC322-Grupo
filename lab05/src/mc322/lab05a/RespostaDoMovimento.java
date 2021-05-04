package mc322.lab05;
import java.util.ArrayList;

public class RespostaDoMovimento {
    public boolean possivel;
    public boolean promover;
    public ArrayList<Par> comidas;
    
    RespostaDoMovimento(){
          this.promover = false;
          this.comidas = new ArrayList<Par>();
    }

    public void comerPeca(Par par){
        this.comidas.add(par);
    }
}
