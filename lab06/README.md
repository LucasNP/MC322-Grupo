# Lab06 versoes 1.0 e 2.0 - Jogo do WUMPUS #

Utilize a versão mais recente (2)
caso algum erro aconteça, tente a versão inicial (1) que apresenta menos recursos adicionais

-----------------

### Estrutura do Projeto ###

```
├── README.md  <- arquivo apresentando a tarefa
│
├── src1 <- pasta de arquivos fonte Java (.java) versao 1
|   │
|   └── mc322 <- raiz do pacote
|       │
|       └── lab06 <- arquivos fonte .java
|            |
|            ├── data   <- recursos sonoros e mapa
|            |
|            └─ componentes <- outras classes java herdeiras de componente
|
├── src2 <- pasta de arquivos fonte Java (.java) versao 2
|   │
|   └── mc322 <- raiz do pacote
|       │
|       └── lab06 <- arquivos fonte .java
|            |
|            ├── data   <- recursos sonoros e mapa
|            |
|            └─ componentes <- outras classes java herdeiras de componente
│
├── bin1 <- pasta de arquivos binarios (.class) versao 1
|   │
|   └── mc322 <- raiz do pacote
|       │
|       └── lab06 <- arquivos binario .class
|            |
|            ├── data   <- recursos sonoros e mapa
|            |
|            └─ componentes <- outras classes java herdeiras de componente
└── bin2 <- pasta de arquivos binarios (.class) versao 2
    │
    └── mc322 <- raiz do pacote
        │
        └── lab06 <- arquivos binario .class
             |
             ├── data   <- recursos sonoros e mapa
             |
             └─ componentes <- outras classes java herdeiras de componente

```

-----------------

### Demonstração do Jogo ###

Com o intuito de uma rápida demonstração do resultado final do trabalho
a dupla fez um video mostrando a gameplay do jogo Mundo de Wumpus, que 
pode ser acessada clicando abaixo:

[![Watch the video](https://img.youtube.com/vi/V6Vmk-Pxt7E/hqdefault.jpg)](https://youtu.be/V6Vmk-Pxt7E)


-----------------


### Arquivos Java do Jogo Mundo de Wumpus ###

   Mundo de Wumpus é um jogo feito em Java que pode ser rodado tanto no terminal
como em uma IDE estilo Eclipse, em ambos os casos deve ser passado como argumento
o path do arquivo CSV com a descrição do mapa, por exemplo, rodando pelo terminal 

```
java mc322.lab06.AppMundoWumpus mc322/lab06/data/Input.csv
```

Para ter acesso aos arquivos fontes Java clique abaixo 
> [Arquivos Java](https://github.com/LucasNP/MC322-Grupo/tree/main/lab06/src2/mc322/lab06)
 
### Arquivo das Classes do Jogo Mundo de Wumpus ###

Para ter acesso aos arquivos binarios das Classes já compilados clique abaixo

> [Arquivos das Classes](https://github.com/LucasNP/MC322-Grupo/tree/main/lab06/bin2/mc322/lab06)

### Arquivos de CSV e Músicas do Jogo Mundo de Wumpus ###

  Para melhor experiência do usuario foram implementas músicas e efeitos
sonoros que se encontram juntos do arquivo teste CSV para montagem do mapa
que se encontram na pasta de dados abaixo

> [Arquivos de Dados](https://github.com/LucasNP/MC322-Grupo/tree/main/lab06/bin2/mc322/lab06/Data)


-----------------


### Destaque da Arquitetura ###

Nessa parte, serão apresentados trechos de código de destaque em que 
foram adotadas soluções que atendem um ou mais dos seis critérios de qualidade 
listados pelo trabalho. Esses destaques serão dividos em categorias referentes
a sua contribuição à melhoria do projeto como um todo, categorias estas que são:

- Expansão e Consistência
- Otimização de Código
- Experiência do Usuário

Assim segue-se:

-----------------

#### Expansão e Consistência ####

#### `Classe Console`

Foi decidido pela criação desta classe para que fosse possivel organizar grande parte 

das saídas do jogo que poderiam ser padronizadas, nesta classe foram adicionado os métodos para 
imprimir as falas com suas animações; imprimir os pensamentos do explorador e seus respectivos sons; 
carregar e controlar efeitos sonoros e músicas, além de um método de esperar 
um determinado tempo em milissegundos antes de continuar o codigo e, ainda por fim, atualizar a tela. 

Uma classe muito útil que abrange as mais diversas funções de forma a permitir 
futura implementação de metodos acerca do funcionamento e gerenciamento do jogo

~~~java
public class Console{
      AudioPlayer musicas[];
      int nMusicas, terminal, graficos;
      public Console() {
            this.nMusicas = 0;
            this.terminal = 0;
            this.graficos = 0;
            this.musicas = new AudioPlayer[5];
      }
     ...
~~~

#### `Criação de um HUD`

Como visto em muitos jogos de pontuação, foi decidido criar uma Classe HUD para
facilitar a exibição, o controle e a coordenação de status do jogo como numero de flechas, 
estado de posse do ouro, "sentidos" do jogador (como fedor e brisa). 

Essa implementação facilita a adição e controle de novos recursos como: 
mais de uma vida, mais flechas, novos itens e exibição de novos estados do jogador :) 

~~~java
public HUD(String nomeJogador, int graficos){
      this.flechas=1;
      this.pontos=0;
      this.ouro=false;
      this.nomeJogador = nomeJogador;
      this.graficos = graficos;
      this.flechaEquipada = false;
      this.frase = "\n";
}
~~~

#### `Classes Abstratas`

Foi decidido a utilização deste tipo de classe para guiar a arquitetura e facilitar a polimorfização de 
suas classes descendentes, além de simplificar o entendimento do programa como um todo. Permitindo ume melhoria no quesito 
de encapsulamento do programa como um todo.

~~~java
public abstract class Componente{
      protected int prioridade;
      protected int i, j; // i = linha, j = coluna. i = y, j = x
      ....
~~~

#### `Variaveis Privadas`

Foi usado de variaveis privadas pois estas tanto auxiliam o proprio editor de texto, quanto o programador na hora 
de escolher os metodos adequados para executar as funcoes nos objetos. Essa tecnica impede o 
uso indevido de objetos externos a sua propria implementação de resolução de uma tarefa, 
criando um encapsulamento que auxilia no entendimento e visualização do código, na correção de bugs caso
algum erro seja encontrado fora da sua classe, e por fim, na implementação que a use como referencia. :smirk:

~~~java
public class ControleJogo {
      private Caverna caverna;
      private HUD hud;
      private Console console;
      private Player jogador;

      private boolean onOuro;
      private boolean jogoRodando;
      private boolean ganhou;
      ...
~~~

#### `Polimorfismo`

Foi decidido intenso uso da técnica de polimorfismo pois este auxilia na estruturação 
de conjuntos de objetos com características semelhantes e a sua implementação torna mais 
rápido e efetivo o seu armazenamento em estruturas como arrays, tabelas hash e listas de 
prioridades, além de funções generalizadas. 

As classes de Aviso -- fedor e brisa --, Wumpus , ouro e jogador são todas polimorfismos de componentes, 
apresentando variáveis de posição (i,j) e símbolo, assim como funções para modificação dessas variáveis.

~~~java
public class Player extends Componente{
private int acao;
private Caverna caverna;
private int terminal;
private String nome;
....
~~~

-----------------

#### Otimização de Código ####

#### `Fast Scanner`

Para uma maior eficiencia do metodo I/O foi-se utilizada uma classe propria 
denominada Fast Scanner que realiza a operação de ler o input do usuario de 
forma mais rápida que o Scanner comum.

~~~java
public class FastScanner {
      BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st=new StringTokenizer("");
      public String next() {
            while (!st.hasMoreTokens()){
                  try { st = new StringTokenizer(br.readLine()); } catch (IOException e) {}
            }
            return st.nextToken(); 
      }
    public int nextInt() { return Integer.parseInt(next()); }
}
~~~


#### `Fila de Prioridade`

Como componentes podem se sobrepor em salas ao longo do jogo cada
uma possui um valor de prioridade associada a este, então para decidir de forma eficiente qual vai 
ser apresentada foi utilizada uma fila de prioridade na Classe de Sala
que abriga todas as componentes de uma dada sala e mantém em seu topo a com maior
prioridade em um certo momento. A fila de prioridade é implementada em Java com Max-Min Heap :yum:

~~~java
private PriorityQueue <Componente> espaco;
Sala(int linha, int coluna, boolean ocupada){
    this.espaco = new PriorityQueue <Componente>(new ComponenteComparator()); 
...
class ComponenteComparator implements Comparator<Componente>{
    public int compare(Componente c1, Componente c2) {
        if (c1.getPrioridade() < c2.getPrioridade()) return 1;
        else if (c1.getPrioridade() > c2.getPrioridade()) return -1;
        return 0;
    }
}
~~~

#### `Uso de Tabelas Hash`

Para evitar comparações excessivas de if/else ou switch foi utilizada a implementação 
de tablas hash do Java, denominada Map, assim podemos manter a contagem do número componentes
para verificação da válidade de uma Caverna (na Classe de Montador da caverna) e associar
as componentes as suas prioridades de forma rápida e com pouco código

Esse destaque facilita a expansão do numero de componentes e reduz o numero de variavies 
no código :D

~~~java
// Montador Caverna
Map<Character, Integer> mapaComponentes = new HashMap<>();
String componentes = "BWOP";
for(char c : componentes.toCharArray()) mapaComponentes.put(c, 0);
~~~

~~~java
// Componentes
String componentes = "BWO";
for(char c : componentes.toCharArray()) this.mapaComponentes.put(c, 3);
this.mapaComponentes.put('P', 2);
this.mapaComponentes.put('f', 1);
this.mapaComponentes.put('b', 0);
this.prioridade = mapaComponentes.get(simbolo);
~~~


-----------------

#### Experiência do Usuário ####

Vale mencionar que a maioria das melhorias nesta secção apenas funcionam caso o jogo
seja rodado pelo terminal.

#### `Animações`

As animaçoes foram implementadas para melhorar a experiencia do usuario com o programa, e consistem
na limpagem do terminal com um comando geral para todos os Sistemas Operacionais para maior fluidez do
fluxo de jogo (caso uma ide estaja sendo usada essa função printa linhas em branco)
e impressão de caracteres de forma pausada nos dialogos.

~~~java
public void limpar(int ms){
    try{ 
          Thread.sleep(ms);
          if(this.terminal == 0) for(int i = 0 ; i < 100; i++) System.out.println();
          else System.out.print("\033[H\033[2J");  
          System.out.flush();  
    }
    catch(Exception e){ e.printStackTrace(); }
}
~~~

#### `Input direto do teclado`

Para uma melhor experiencia do jogo no terminal em sistemas UNIX ao ser chamada 
a função de ler o comando de movimento nós passamos o terminal para "RAW mode"
assim o buffer recebe o caracter de movimento e não precisa ser apertado a tecla ENTER,
logo após ler retornamos o terminal ao "COOKED mode", infelizmente não conseguimos replicar
para windows, assim fazemos uma verificação de OS para essa funcionalidade.

~~~java
else{
    String[] cmd = {"/bin/sh", "-c", "stty raw </dev/tty"};
    setTerminal(cmd); 
    while (true) {
            try{ tecla = (char) new InputStreamReader(System.in).read (); break; }
            catch(IOException e ){}
    }
    cmd[2] = "stty cooked </dev/tty";
    setTerminal(cmd);
}
~~~

#### `Músicas e Efeitos sonoros`

As músicas foram implementadas para melhorar a experiencia do usuario com o programa, 
dando uma identidade completamente nova ao jogo Wumpus. Todas as musicas são originais e 
projetadas para o jogo e suas diversas açoes como a introdução, apresentação de texto 
jogabilidade em si, enfrentar o chefe, colidir com a parede, equipar a flecha ou pegar o ouro.

Elas juntos com os efeitos sonoros foram implementados por meio da nossa Classe AudioPlayer que se 
utiliza da biblioteca de Java chamada javax.sound para tocar, pausar, parar e resumir uma música. 
:musical_note: :ok_hand:

~~~java
public AudioPlayer(String caminho, boolean loop) throws UnsupportedAudioFileException, 
     IOException, LineUnavailableException {
           filePath=caminho;
           audioInputStream = AudioSystem.getAudioInputStream(new File(filePath).getAbsoluteFile());
           clip = AudioSystem.getClip();
           clip.open(audioInputStream);
           if(loop) clip.loop(Clip.LOOP_CONTINUOUSLY);
}
~~~

#### `Caracteres em Unicode`

Além a representação requisita no trabalho com os caracteres comuns como vista a seguir 

![alt text](https://github.com/LucasNP/MC322-Grupo/blob/main/lab06/MundoDeWumpus.png)

Para terminais habilitados com uma fonte que suporte Unicode foi feita uma "renderização"
do jogo para simbolos mais significativos e assim deixando a experiencia mais divertida :)

como se vê a seguir:

![alt text](https://github.com/LucasNP/MC322-Grupo/blob/main/lab06/MundoDeWumpus-Unicode.png)


~~~java
if(this.graficos == 1){
      map.put('B', '\u25EF');
      map.put('W', '\u26C7');
      map.put('O', '\u272A');

      map.put('P', '\u26D1');
      map.put('_', '\u2B1A');
      map.put('#', '\u25A0');
 ...
~~~

