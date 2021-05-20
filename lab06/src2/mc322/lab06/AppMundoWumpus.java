package mc322.lab06;

import java.io.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.*;  


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class AppMundoWumpus{
      public static boolean isNumeric(String str){
          for (char c : str.toCharArray()){
              if (!Character.isDigit(c)) return false;
          }
          return true;
      }

      public static void main(String[] args){
            // java mc322.lab06.AppMundoWumpus mc322/lab06/data/Input.csv
            FastScanner fs = new FastScanner();
            Console console = new Console();


            console.playMusica("mc322/lab06/data/IntroWumpus.wav",true);

            String string_terminal, string_graficos;
            int terminal, graficos;
            graficos = -1;
            terminal = -1;
            console.falar("Por favor configure: ");
            while(true){
                  System.out.print("Este jogo está sendo rodado no terminal ? (digite 0 ou 1):  ");
                  string_terminal = fs.next();
                  if(isNumeric(string_terminal)) terminal = Integer.parseInt(string_terminal);
                  
                  if(terminal == 0 || terminal == 1) {
                        console.setarAmbiente(terminal);
                        break;
                  }
            }
            while(true && terminal == 1){
                  System.out.println("Nice graphics ? ");
                  System.out.println("ATENÇÂO: Para os Nice graphics é necessario usar uma fonte com unicode no terminal");
                  System.out.print("(digite 0 ou 1): ");
                  string_graficos = fs.next();
                  if(isNumeric(string_graficos)) graficos = Integer.parseInt(string_graficos);
                  
                  if(graficos == 0 || graficos == 1) {
                        console.limpar(50);
                        console.setarGraficos(graficos);
                        break;
                  }
                  console.limpar(50);
            }

            System.out.println("Como se joga: ");
            console.falar("Movimentação: WASD  |  Pegar Ouro:  C   |  Equipar Flecha:  K   |   Sair:  Q ");

            console.limpar(50);

            System.out.print("Bota o teu nome de explorador ae bixo: ");
            String nomeJogador = fs.next();
            String caminhoInput = args[0];
            Caverna caverna = new Caverna(caminhoInput, graficos, nomeJogador);

            console.limpar(900);
            console.falar("Bem vindo(a) a temida caverna de WUMPUS explorador(a) " + nomeJogador + "....");
            console.limpar(50);
            console.falar("Será que você consegue desviar dos buracos e roubar o tesouro do monstruoso WUMPUS ?");
            console.limpar(900);
            
            console.stopMusica();
            ControleJogo controle = new ControleJogo(caverna, console, terminal);
            controle.executaJogo();
    }
}
