package mc322;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{ //herdeiro de Canvas e possui metodo run da iterface runnable (necessario para os threads)
	
	
	public static final long serialVersionUID = 1L; //pediu pra gerar, acho q isso serve pra ele identificar a tela
	public static final int WIDTH = 640*2, HEIGHT = WIDTH /12*7; //Tamanho da tela
	private Thread thread; //rodar isso em paralelo
	private boolean running = false; // se o jogo ainda está rodando
	private Controle controle;
	
	public Game()
	{
		this.controle = new Controle();
		new Window(WIDTH, HEIGHT,"Dungeons and Wumpus",this);
	}
	
	
	public synchronized void start(){ //inicia o jogo, chamado na classe window
		thread = new Thread(this);
		thread.start();
		running=true;
	}


	public void run() { //exigido em runnable, esse é o motor do jogo
		this.requestFocus();//não ter que clicar na tela pra mexer no jogo (exige o foco)
		long lastTime = System.nanoTime();	//----------------MOTOR DO JOGO------------------// o motor serve para todos computadores rodarem no mesmo fps
		double amountOfTicks=60.0;	// fps do jogo                                     mesmo que os computadores tenham capacidade de processamento diferentes
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames=0;
		while(running){  // while do jogo
			long now = System.nanoTime();
			delta += (now - lastTime)/ns;
			lastTime = now;
			while(delta >= 1){
				tick(); // aqui entra o tick do jogo, onde acontece a lógica
				delta --;
			}
			if(running)
				render(); // aqui entra o render do jogo, onde acontece o display
			frames++;
			
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop(); // desliga o jogo
	}


	public synchronized void stop(){
		try{
			thread.join();
			running=false;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}


	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs==null){
			this.createBufferStrategy(2);
			return;
			
		}
		Graphics g = bs.getDrawGraphics(); // aqui onde aparece as coisas
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT); //background preto
		
		controle.render(g);
		
		g.dispose();
		bs.show();
		
	}


	private void tick() {
		controle.tick();
	}
	
}
