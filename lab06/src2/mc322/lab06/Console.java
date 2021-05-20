package mc322.lab06;
import mc322.lab06.AudioPlayer;  

import java.io.File;
import java.io.IOException;
import java.util.Random;


public class Console{
      AudioPlayer musicas[];
      int nMusicas;
      int terminal;
      int graficos;

      public Console() {
            this.nMusicas = 0;
            this.terminal = 0;
            this.graficos = 0;
            this.musicas = new AudioPlayer[5];
      }

      void setarAmbiente(int terminal){
            this.terminal = terminal;
      }
      void setarGraficos(int graficos){
            this.graficos = graficos;
      }

      public void limpar(int ms){
            try{ 
                  Thread.sleep(ms);
                  if(this.terminal == 0) for(int i = 0 ; i < 100; i++) System.out.println();
                  else System.out.print("\033[H\033[2J");  
                  System.out.flush();  
            }
            catch(Exception e){ e.printStackTrace(); }
      }

      public void esperar(int ms){
            try{ Thread.sleep(ms); }
            catch(Exception e){ e.printStackTrace(); }
      }
      
      public void playMusica(String caminho, boolean loop){
            try{
                  AudioPlayer audioPlayer = new AudioPlayer(caminho, loop);
                  audioPlayer.play();
                  if(loop){
                        this.musicas[this.nMusicas]=audioPlayer;
                        this.nMusicas+=1;
                  }
            } 
            catch (Exception ex){
                  System.out.println( "Erro ao tocar a música ou som :(" );
                  ex.printStackTrace();
            }  
      }
      public void playMusica(String caminho){
            this.playMusica(caminho, false);
      }

      public void stopMusica(){
            try {
                  this.nMusicas--;
                  this.musicas[this.nMusicas].stop();
            }
            catch(Exception ex){
                  System.out.println( "Erro ao parar a música ou som :(" );
                  ex.printStackTrace();
            }
      }

      public void pauseMusica(){
            try {
                  this.musicas[this.nMusicas-1].pause();
            }
            catch(Exception ex){
                  System.out.println( "Erro ao pausar a música ou som :(" );
                  ex.printStackTrace();
            }
    	  
      }
      
      public void resumeMusica(){
            try {
                  this.musicas[this.nMusicas-1].resumeAudio();
            }
            catch(Exception ex){
                  System.out.println( "Erro ao despausar a música ou som :(" );
                  ex.printStackTrace();
            }
      }

      public void falar(String fala){
            if(this.graficos == 1) System.out.print("\u2710"+": ");
            for(int i = 0; i < fala.length(); i++){
                  System.out.print(fala.charAt(i));
                  if(fala.charAt(i)==' ') this.playMusica("mc322/lab06/data/Type4.wav", true);
                  else{
                        Random gerador = new Random(542);
                        int num = gerador.nextInt(3);
                        if(num==1) this.playMusica("mc322/lab06/data/Type1.wav", true);
                        else if(num==1) this.playMusica("mc322/lab06/data/Type2.wav", true);
                        else this.playMusica("mc322/lab06/data/Type3.wav", true);
                  }
                  this.esperar(105);
                  this.stopMusica();
            }
            System.out.println();
            this.esperar(900);
      }
      
      public void pensar(String pensamento){
            this.playMusica("mc322/lab06/data/Pensar.wav");
            char quotes = '"';
            if(this.graficos == 1) System.out.print("\u26D1"+": "+quotes);
            for(int i = 0; i < pensamento.length(); i++){
                  if(pensamento.charAt(i)==' ') this.esperar(125);
                  System.out.print(pensamento.charAt(i));
            }
            System.out.println(quotes);
            this.esperar(1000);
      }
}


