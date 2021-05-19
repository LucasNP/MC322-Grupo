package mc322.lab06;
import mc322.lab06.componentes.Player;
import java.util.Random;

//mod
public class ControleJogo {
      public void executaJogo(String jogadorNome, String caminhoInput,Console console){
            Caverna caverna = new Caverna(caminhoInput);
            System.out.println(caverna.imprimirCaverna());

            boolean rodando = true;
            Player jogador = caverna.receberPlayer();
            jogador.setCaverna(caverna);


            HUD hud = jogador.getHud();
            console.stopMusica();
            console.playMusica("mc322/lab06/data/Wumpus.wav",true);
            while(rodando){
                int acao = jogador.decidir();

                // 0 - UP
                // 1 - LEFT
                // 2 - RIGHT
                // 3 - DOWN

                int dI[] = {-1, 0, 0, 1};
                int dJ[] = {0, -1, 1, 0};
                
                if(acao <4){
                	
                	boolean atirou = false;
                	if(hud.flechaEstaEquipada())
                	{
                		hud.atirar(console,jogadorNome);
                		atirou = true;
                	}
                	int pi=jogador.getI()+ dI[acao];
                	int pj=jogador.getJ()+ dJ[acao];
                	
                	
                	
                    //System.out.println(acao);
                    //System.out.println(dI[k] + " " + dJ[k]);
                	if(pi<1||pi>4||pj<1||pj>4)
                	{
                		console.pensar("Auch! dei de cara na parede!");
                		console.limpar(0);
                		System.out.println(caverna.imprimirCaverna());
                		continue;
                	}
                	
                	jogador.getCaverna().getSalaAt(pi,pj).visitar();
                	console.limpar(0);
                    System.out.println(caverna.imprimirCaverna());
                    hud.mostrar(jogador.getCaverna().getSalaAt(jogador.getI(),jogador.getJ()).verSegundo());
                	
                	hud.incrementarPonto(-15);
                    if(!jogador.getCaverna().getSalaAt(pi,pj).estaVazia())
                    {
                    	char simboloSala = jogador.getCaverna().getSalaAt(pi,pj).getSimbolo(false);
                        switch(simboloSala)
                        {
                        case 'B':
                        	console.falar(jogadorNome+" caiu no buraco e ficou preso!");
                        	console.falar(jogadorNome+ " foi derrotado.");
                        	hud.incrementarPonto(-1000);
                        	rodando = false;
                        	break;
                        	
                        case 'W':
	                        {
	                        	if(atirou)
	                        	{
	                        		console.stopMusica();
	                        		console.playMusica("mc322/lab06/data/BossWumpus.wav",true);
	                        		console.falar(jogadorNome+" deu de cara com WUMPUS.");
	                        		if(this.lutaComWumpus(console, jogadorNome))
	                        		{
	                        			console.stopMusica();
	                        			console.playMusica("mc322/lab06/data/Wumpus.wav",true);
	                        			hud.incrementarPonto(500);
	                        			jogador.getCaverna().getSalaAt(jogador.getI()+ dI[acao],jogador.getJ()+ dJ[acao]).removerTopo();
	                        			break;
	                        		}
	                        		else
	                        		{
	                        			console.stopMusica();
	                        			hud.incrementarPonto(-1000);
	                        			rodando = false;
	                        			break;
	                        		}
	                        	}
	                        	else
	                        	{
	                        		console.falar(jogadorNome+ " encontrou o WUMPUS e estava desarmado!");
	                        		console.playMusica("mc322/lab06/data/ceVaiMorre.wav");
	                        		console.esperar(1000);
	                        		console.falar(jogadorNome+" foi derrotado.");
	                        		hud.incrementarPonto(-1000);
	                        		rodando = false;
	                        		break;
	                        	}
	                        	
	                        }
	                        
                        case 'O':
                        	console.pensar("Aqui tem ouro!");
                        	break;
                        default:
                        	break;
                        }
                    }
                    jogador.mover(jogador.getI() + dI[acao], jogador.getJ() + dJ[acao]);
                    	
                }

                switch(acao){
                      case 4:
                            hud.equiparFlecha(console);
                            break;
                      case 5:
                    	  if(jogador.getCaverna().getSalaAt(jogador.getI(),jogador.getJ()).getSimbolo(false)=='O')
                    	  {
                    		  jogador.getCaverna().getSalaAt(jogador.getI(),jogador.getJ()).removerTopo();
                    		  console.falar(jogadorNome+" pegou o ouro");
                    		  hud.pegarOuro();
                    	  }
                    	  else
                    		  console.pensar("Não tem ouro aqui...");
                            break;
                      case 6:
                            // if estar com ouro e na posicao de saida
                    	  	if(hud.carregandoOuro()&&jogador.getI()==1&&jogador.getJ()==1)
                    	  	{
                    	  		hud.incrementarPonto(1000);
                    	  		// terminar o jogo
                    	  		rodando=false;
                    	  	}
                            //else "quer mesmo encerrar o jogo?
                    	  	else
                    	  	{
                    	  		console.pensar("Quer mesmo acabar o jogo sem pontuar?");
                    	  		FastScanner fs = new FastScanner();
                    	        String res = fs.next();
                    	        res = res.toLowerCase();
                    	        if(res=="quero"||res.charAt(0)=='y'||res.charAt(0)=='s'||res.charAt(0)=='p')
                    	        {
                    	        	return;
                    	        }
                    	  		
                    	  	}
                            break;
                       default:
                    	   break;
                }

                console.limpar(0);
                System.out.println(caverna.imprimirCaverna());
                hud.mostrar(jogador.getCaverna().getSalaAt(jogador.getI(),jogador.getJ()).verSegundo());
            }
            console.falar(jogadorNome+" fez "+jogador.getHud().getPontos()+" pontos.");
            console.esperar(3000);
            
      }
      
      boolean lutaComWumpus(Console console,String jogadorNome)
      {
    	  Random gerador = new Random();
    	  int gerado = gerador.nextInt(100);
    	  if(gerado<50)
    	  {
    		  //perde
    		  if(gerado<25)
    		  {
    			  console.falar("Sua flecha atingiu um braço de WUMPUS.");
	    		  console.falar("WUMPUS o pegou com o outro braço.");
	    		  console.playMusica("mc322/lab06/data/ceVaiMorre.wav");
	      		  console.esperar(1000);
	    		  console.falar(jogadorNome+" foi derrotado.");
	    		  return false;
    		  }
    		  else
    		  {
    			  console.falar("Sua flecha atingiu a orelha de WUMPUS.");
	    		  console.falar("WUMPUS ficou irritado, arrancou a flecha e atirou sobre você.");
	    		  console.playMusica("mc322/lab06/data/ceVaiMorre.wav");
	      		  console.esperar(1000);
	    		  console.falar(jogadorNome+" foi derrotado.");
	    		  return false;
    		  }
    		  
    		  
    	  }
    	  else
    	  {
    		  //ganha
    		  if(gerado > 75)
    		  {
    			  console.falar("Sua flecha atingiu a cabeça de WUMPUS.");
        	  	  console.falar("WUMPUS foi derrotado!");
        		  return true;
    		  }
    		  else
    		  {
    			  console.falar("Sua flecha atingiu o coração de WUMPUS.");
        	  	  console.falar("WUMPUS foi derrotado!");
        		  return true;
    		  }
    	  	  
    	  }
    		  
      }
}
