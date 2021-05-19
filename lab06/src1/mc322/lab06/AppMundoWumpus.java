package mc322.lab06;  

public class AppMundoWumpus{
      public static void main(String[] args){
            FastScanner fs = new FastScanner();
            Console console = new Console();
            ControleJogo controle = new ControleJogo();

            // java mc322.lab06 mc322/lab06/data/Input.csv
            console.playMusica("mc322/lab06/data/introWumpus.wav",true);
            
            System.out.print("Insira o seu nome de explorador: ");
            String jogadorNome = fs.next();
            
            
            console.limpar(900);
            console.falar("Bem vindo a temida caverna de WUMPUS explorador " + jogadorNome+ "... ");
            console.falar("Será que você consegue desviar dos buracos e roubar o tesouro de WUMPUS ?");
            controle.executaJogo(jogadorNome, args[0],console);
            console.pensar("Fim de jogo!");
    }
}
