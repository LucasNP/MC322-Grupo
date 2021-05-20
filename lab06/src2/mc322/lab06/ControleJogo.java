package mc322.lab06;
import mc322.lab06.componentes.Player;
import mc322.lab06.componentes.Wumpus;

public class ControleJogo {
      private Caverna caverna;
      private HUD hud;
      private Console console;
      private Player jogador;

      private boolean onOuro;
      private boolean jogoRodando;
      private boolean ganhou;

      ControleJogo(Caverna caverna, Console console, int ide){
            this.caverna = caverna;
            this.console = console;
            this.hud     = this.caverna.receberHUD();
            this.jogador = this.caverna.receberPlayer();
            this.jogador.setCaverna(caverna);
            this.jogador.setAmbiente(ide);
            this.jogoRodando = true;
            this.onOuro = false;
            this.ganhou = true;
      }


      public void acaoPegarOuro(int i, int j){
            if(this.jogador.getCaverna().getSalaAt(i, j).getSimbolo() == 'O'){
                  this.jogador.getCaverna().getSalaAt(i, j).removerTopo();
                  this.console.falar(this.jogador.getNome() + " pegou o ouro");
                  this.console.pensar("Tô ricaço agora hoho ^v^");
                  this.hud.pegarOuro();
                  this.hud.incrementarPonto(1000);
                  return;
            }
            this.console.pensar("Não tem ouro aqui... :/");
            return;
      }

      public void acaoTerminarJogo(int i, int j){
            if(this.hud.carregandoOuro() && i == 1 && j ==1){
                  this.console.pensar("Saindo da caverna...");
                  this.jogoRodando = false;
                  return;
            }
            this.console.pensar("Quer mesmo abandonar a caverna e o ouro? (digite 0 ou 1)");
            FastScanner fs = new FastScanner();
            int res = fs.nextInt();
            if(res == 1) this.jogoRodando = false;
            return;
      }

      public void acaoWumpus(boolean atirou, int pi, int pj, Wumpus wumpus){
            if(atirou){
                  this.console.stopMusica();
                  this.console.playMusica("mc322/lab06/data/BossWumpus.wav",true);
                  this.console.falar(this.jogador.getNome()+" encontrou um WUMPUS selvagem");
                  
                  if(wumpus.lutaComWumpus(this.console, this.jogador)){
                        this.console.stopMusica();
                        this.console.playMusica("mc322/lab06/data/BossWumpus.wav",true);
                        this.hud.incrementarPonto(500);
                        this.jogador.getCaverna().getSalaAt(pi,pj).removerTopo();
                  }
                  else{
                        this.ganhou = false;
                        this.hud.incrementarPonto(-1000);
                        this.jogoRodando = false;
                  }
                  this.console.stopMusica();
                  this.console.playMusica("mc322/lab06/data/Wumpus.wav",true);
                  return;
            }

            console.falar(this.jogador.getNome() + " encontrou o WUMPUS e estava desarmado! ;3;");
            console.playMusica("mc322/lab06/data/ceVaiMorre.wav");
            console.esperar(1000);
            console.falar(this.jogador.getNome() + " foi cortado ao meio pelas garras de WUMPUS");
            hud.incrementarPonto(-1000);
            this.jogoRodando = false;
      }

      public void executaJogo(){
            this.console.playMusica("mc322/lab06/data/Wumpus.wav",true);
            System.out.println(this.caverna.imprimirCaverna());

            while(this.jogoRodando){
                  int i = jogador.getI();
                  int j = jogador.getJ();

                  int acao = this.jogador.decidir();
                  this.console.limpar(0);
                  System.out.println(caverna.imprimirCaverna());


                  int dI[] = {-1, 0, 0, 1};
                  int dJ[] = {0, -1, 1, 0};

                  if(acao < 4){
                        int pi = i + dI[acao];
                        int pj = j + dJ[acao];

                        boolean safe = ( pi>0 && pj<5 && pj>0 && pi <5 );
                        if(!safe) {
                              console.pensar("Auch! dei de cara na parede!");
                              console.limpar(0);
                              System.out.println(this.caverna.imprimirCaverna());
                              continue;
                        }

                        jogador.getCaverna().getSalaAt(pi,pj).visitar();
                        console.limpar(0);
                        System.out.println(caverna.imprimirCaverna());
                        hud.incrementarPonto(-15);

                        Sala topo = jogador.getCaverna().getSalaAt(pi, pj);

                        Componente ouro = null;
                        if(onOuro == true){
                              ouro = this.jogador.getCaverna().getSalaAt(i, j).removerTopo();
                              onOuro = false;
                        }
                        jogador.mover(pi, pj);
                        if(ouro != null) caverna.getSalaAt(i, j).adicionarComponente(ouro);

                        if(topo.olhaTopo() != null){
                              char evento = topo.olhaTopo().getSimbolo();
                              switch(evento){
                                    case 'B':
                                          this.console.limpar(0);
                                          System.out.println(caverna.imprimirCaverna());
                                          console.playMusica("mc322/lab06/data/buraco.wav");
                                          this.console.falar("O(a) desatento(a) " + jogador.getNome() + " caiu no buraco e se espatifou no chão :T");
                                          this.console.falar("....mais uma alma que encontrou seu fim na caverna de WUMPUS...");
                                          hud.incrementarPonto(-1000);
                                          this.console.esperar(3000);
                                          this.ganhou = false;
                                          this.jogoRodando = false;
                                          onOuro = false;
                                          break;
                                    case 'W':
                                          Wumpus wumpus = (Wumpus) topo.olhaTopo();
                                          acaoWumpus(this.hud.flechaEquipada(), pi, pj, wumpus);
                                          this.hud.atirar();
                                          onOuro = false;
                                          break;
                                    case 'O':
                                          this.console.limpar(0);
                                          System.out.println(caverna.imprimirCaverna());
                                          console.pensar("Uepa!! encontrei o ouro!");
                                          onOuro = true;
                                          break;
                                    default:
                                          break;
                              }
                        }
                        if( !jogador.getCaverna().getSalaAt(pi, pj).estaVazia() ){
                              hud.mostrar(jogador.getCaverna().getSalaAt(pi, pj).Segundo());
                        }
                        if(this.hud.flechaEquipada()){ 
                              this.console.limpar(0);
                              System.out.println(caverna.imprimirCaverna());
                              console.falar("Você atirou no nada e perdeu sua flecha...");
                              console.pensar("Por que diabos eu trouxe só uma flecha ??? T^T");
                              this.hud.atirar();
                        }
                }

                switch(acao){
                      case 4:
                            hud.equiparFlecha(this.console);
                            break;
                      case 5:
                            onOuro = false;
                            acaoPegarOuro(i, j);
                            this.console.limpar(0);
                            System.out.println(caverna.imprimirCaverna());
                            break;
                      case 6:
                            acaoTerminarJogo(i, j);
                            break;
                }

                console.limpar(300);
                System.out.println(caverna.imprimirCaverna());
            }

            console.esperar(600);
            console.stopMusica();
            if(this.ganhou == true){
                  console.playMusica("mc322/lab06/data/vitoria.wav");
                  console.falar("Incrivel explorador(a) " + this.jogador.getNome() + " você conseguiu !!");
                  console.falar("Agora só construir uma piscina de moeadas com esse ouro....");
            }
            else{
                  console.falar("Descanse em paz explorador(a)....");
                  console.falar("G A M E   O V E R");
            }
            System.out.println();
            System.out.println("Obrigado por jogar ~^V^~");
            System.out.println("Você fez " + hud.getPontos() + " pontos.");
      }
}
