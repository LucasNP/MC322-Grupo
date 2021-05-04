Classe appdama:

* variaveis
* tabuleiro
* comandos


- le os comandos do csv e gera um vetor de strings do tipo "f4:d4".
- cria o tabuleiro
- executa jogo: retorna vetor com strings dos jogos
- chama a impressao do tabuleiro



Classe Tabuleiro:


* tabuleiro (matriz de pecas)
* turno (de quem é a vez de jogar)

- executar comando (le o comando passado e executa ja com significado para a maquina)
	- mover peca (verificando se é do turno correto e se o movimento é possivel)
		- comer (separado pois pode jogar novamente)
			- manter turno
		- verificar promocao
	- mudar turno		
- apresentar na tela o tabuleiro formatado
	- retornar a string com o formato do tabuleiro



Classe peca:


* Caracter
* Ponteiro para o tabuleiro
* x
* y


-verifica se é possível atingir a posição desejada
	-verificar se há peça a comer (????)
-trocar caracter (para quando virar dama)
