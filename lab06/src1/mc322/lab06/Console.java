package mc322.lab06;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

//mod
public class Console{
	private SimpleAudioPlayer musicas[] ;
    private int nMusicas;
    
    public Console()
    {
    	this.nMusicas = 0;
    	this.musicas = new SimpleAudioPlayer[5];
    }
      private class SimpleAudioPlayer {
    	  
            Long currentFrame;
            Clip clip;
            String status;
            AudioInputStream audioInputStream;
            String filePath;

            public SimpleAudioPlayer(String caminho, boolean loop) 
                        throws UnsupportedAudioFileException, 
                        IOException, LineUnavailableException 
            {
                 filePath=caminho;
                 audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
                 clip = AudioSystem.getClip();
                 clip.open(audioInputStream);
                  if(loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
            }

            public void play() {
	        clip.start();
	        status = "play";
	    }
            public void stop() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException 
            {
                currentFrame = 0L;
                clip.stop();
                clip.close();
            }
	  
	}
	
      void limpar(int ms){
            try{ 
                  Thread.sleep(ms);
                  if(System.getProperty("os.name").startsWith("Windows")){
                	  System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                  }
                  else{
                	  new ProcessBuilder("clear").inheritIO().start().waitFor();
                  }
            }
            catch(Exception e){ e.printStackTrace(); }
      }

      void esperar(int ms){
            try{ Thread.sleep(ms); }
            catch(Exception e){ e.printStackTrace(); }
      }
      
      void playMusica(String caminho, boolean loop){
            try{
                  SimpleAudioPlayer audioPlayer = new SimpleAudioPlayer(caminho, loop);
                  audioPlayer.play();
                  if(loop)
                  {
                	  this.musicas[this.nMusicas]=audioPlayer;
                	  this.nMusicas+=1;
                  }
                  
            } 
            catch (Exception ex){
                  System.out.println( "Erro ao tocar a música ou som :(" );
                  ex.printStackTrace();
            }  
      }
      void stopMusica()
      {
    	  try {
    		  this.nMusicas--;
    		  this.musicas[this.nMusicas].stop();
    	  }
    	  catch(Exception ex)
    	  {
    		  System.out.println( "Erro ao parar a música ou som :(" );
    		  ex.printStackTrace();
    	  }
    	  
      }
      
      
      void playMusica(String caminho){
            this.playMusica(caminho, false);
      }
      
      void falar(String fala)
      {
    	  for(int i = 0; i < fala.length(); i++)
    	  {
    		  System.out.print(fala.charAt(i));
    		  if(fala.charAt(i)==' ')
    		  {
    			  this.playMusica("mc322/lab06/data/Type4.wav",true);
    		  }
    		  else
    		  {
    			  Random gerador = new Random(542);
	    		  int num = gerador.nextInt(3);
	    		  if(num==0)
	    			  this.playMusica("mc322/lab06/data/Type1.wav",true);
	    		  else if(num==1)
	    			  this.playMusica("mc322/lab06/data/Type2.wav",true);
	    		  else
	    			  this.playMusica("mc322/lab06/data/Type3.wav",true);
    		  }
              this.esperar(125);
              this.stopMusica();
    	  }
    	  System.out.print("\n");
    	  this.esperar(1000);
    	  //this.limpar(0);
      }
      
      void pensar(String pensamento)
      {
    	  this.playMusica("mc322/lab06/data/Pensar.wav");
    	  for(int i = 0; i < pensamento.length(); i++)
    	  {
    		  if(pensamento.charAt(i)==' ')
    			  this.esperar(125);
    		  System.out.print(pensamento.charAt(i));
    	  }
    	  System.out.println("");
    	  this.esperar(1000);
      }
}

