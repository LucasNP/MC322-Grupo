package mc322.lab06.componentes;
import java.util.Random;

import mc322.lab06.Componente;
import mc322.lab06.componentes.Player;
import mc322.lab06.Console;

public class Wumpus extends Componente{

      public Wumpus(int i, int j, char simbolo) {
            super(i, j, simbolo);
      }

      public boolean lutaComWumpus(Console console, Player jogador){
            Random gerador = new Random();
            int gerado = gerador.nextInt(100);
            console.falar("Assutado(a) você atira sua flecha, ela sai voando e...");
            if(gerado<50){
                  if(gerado<25){
                        console.falar("Ela atinge o braço esquerdo de WUMPUS");
                        console.falar("Ele.... chora.. ?");
                        console.falar("Irritado por vê-lo chorando, WUMPUS pega seu corpo com seu outro braço e...");
                        console.falar("Wumpus o engole completamente...");
                  }
                  else{
                        console.falar("Ela atinge a orelha direita de WUMPUS");
                        console.falar("WUMPUS fica irritado, arranca a flecha, a atira de volta e...");
                        console.falar("A flecha atinge seu joelho e...");
                  }
                  console.playMusica("mc322/lab06/data/ceVaiMorre.wav");
                  console.esperar(1000);
                  console.falar("O(a) Explorador(a) " + jogador.getNome() + " pereceu na caverna");
                  return false;
            }
            else{
                  if(gerado > 75) {
                        console.falar("Ela atinge a cabeça de WUMPUS...");
                        console.falar("O corpo de WUMPUS cae no chão... ao lado de sua ninhada de ovo...?");
                        console.falar("Os filhotes...... não sobreviverão sem a mãe....");
                  }
                  else{
                        console.falar("Ela atinge o coração de WUMPUS");
                        console.falar("Você vê lagrimas nos olhos de WUMPUS...");
                        console.falar("O corpo e expressão de medo de WUMPUS descansam no chão...");
                  }
                  console.falar("Parabens...... você....");
                  console.falar("...matou WUMPUS.... O_O");
                  return true;
            }
      }


}

