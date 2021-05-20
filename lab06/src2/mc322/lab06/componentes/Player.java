package mc322.lab06.componentes;
import mc322.lab06.Caverna;
import mc322.lab06.Componente;
import mc322.lab06.HUD;
import mc322.lab06.FastScanner;
import java.util.HashMap;
import java.util.Map;
import java.io.*; 

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Player extends Componente{
    private int acao;
    private Caverna caverna;
    private int terminal;
    private String nome;

    public Player(int i, int j, char simbolo){
          super(i, j, simbolo);
          this.acao = -1;
          this.terminal = 0;
          this.nome = nome;
    }
    
    public int decidir(){
          char tecla;

          if(terminal == 0 || System.getProperty("os.name").startsWith("Windows")){
                FastScanner fs = new FastScanner();
                String input = fs.next();
                tecla = input.toLowerCase().charAt(0);
          }

          else{
                String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
                setTerminal(cmd); 
                while (true) {
                        try{
                            tecla = (char) new InputStreamReader(System.in).read ();
                            break;
                        }
                        catch(IOException e ){}
                }
                cmd[2] = "stty cooked </dev/tty";
                setTerminal(cmd);
          }

          Map<Character, Integer> mapaKeys = new HashMap<>();
          String keyboard = "wadskcq";
          for(int k = 0; k <= 6; k++) mapaKeys.put(keyboard.charAt(k), k);

          if( mapaKeys.get(tecla) != null){
                this.acao = mapaKeys.get(tecla);
          }
          return this.acao;
    }

    public int getI(){
          return this.i;
    }
    public int getJ(){
          return this.j;
    }
    public Caverna getCaverna(){
          return this.caverna;
    }
    public String getNome(){
          return this.nome;
    }

    public void setCaverna(Caverna caverna){
          this.caverna = caverna;
    }
    public void setNome(String nome){
          this.nome = nome;
    }
    public void setAmbiente(int terminal){
          this.terminal = terminal;
    }

    public void mover(int i, int j) {
          if (i>0 && j<5 && j>0 && i <5 ) {
                caverna.mover(this.i,this.j, i, j);
                this.i = i;
                this.j = j;
          }
    }
    private void setTerminal(String[] comando){
          try{
                Runtime run = Runtime.getRuntime();
                Process pr = run.exec(comando);
                pr.waitFor();
                BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
                String line = "";
                while ((line=buf.readLine())!=null) {
                      System.out.println(line);
                }
          }
          catch(Exception e){ e.printStackTrace(); }
    }

}
