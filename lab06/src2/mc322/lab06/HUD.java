package mc322.lab06;

public class HUD{
    private int flechas;
    private int pontos;
    private boolean ouro;
    private boolean brisa;
    private boolean fedor;
    private boolean flechaEquipada;

    private String frase;
    private String nomeJogador;
    private int graficos;

    public HUD(String nomeJogador, int graficos){
          this.flechas=1;
          this.pontos=0;
          this.ouro=false;
          this.nomeJogador = nomeJogador;
          this.graficos = graficos;
          this.flechaEquipada = false;
          this.frase = "\n";
    }
    
    public int getPontos(){
          return this.pontos;
    }
    
    public void mostrar(char aviso){
          this.frase = "\n";

          if(graficos == 1) this.frase += "\u26D1 ";
          this.frase += "Explorador(a) : " + nomeJogador + "\n";

          if(graficos == 1) this.frase += "\u2666 ";
          this.frase += "Total de Pontos: " + this.pontos +"\n";

          if(graficos == 1 && this.ouro == true) this.frase += "\u272A ";
          if(this.ouro)  this.frase += "Eita como esse ouro pesa !!! '>^<)\n";

          if(graficos == 1) this.frase += "\u27BC ";
          if(this.flechas ==0) this.frase += "Acabaram as flechas :O !\n";
          else if(this.flechas ==1) this.frase += "Eu ainda tenho uma flecha :)\n";
          else this.frase += "Eu ainda tenho " + this.flechas + " flechas :)\n";

          if(aviso == 'b') {
                if(graficos == 1) this.frase += "\u2248 ";
                this.frase += "Estou sentindo um ventinho!\n";
          }
          else if(aviso =='f') {
                if(graficos == 1) this.frase += "\u2668 ";
                this.frase += "Este lugar está fedendo!\n";
          }
    }

    public String receberFrase(){
          return this.frase;
    }
    
    public void pegarOuro(){
          this.ouro = true;
    }
    public boolean carregandoOuro(){
          return this.ouro;
    }
    
    public boolean equiparFlecha(Console console){
          if(flechas>0){
                this.flechas--;
                this.incrementarPonto(-100);
                this.flechaEquipada = true;
                console.pensar("Hehe, coloquei minha flecha no arco");
                return true;
          }
          console.pensar("Não tenho mais flechas!");
          return false;
    }
    
    public void atirar(){
          this.flechaEquipada = false;
    }
    
    public boolean flechaEquipada(){
          return this.flechaEquipada;
    }
    
    public void incrementarPonto(int adicionar){
          this.pontos += adicionar;
    }
}

