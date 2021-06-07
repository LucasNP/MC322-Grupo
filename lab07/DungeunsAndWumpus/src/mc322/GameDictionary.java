package mc322;

import mc322.terreno.Bau;
import mc322.terreno.Elevado;
import mc322.terreno.Escada;
import mc322.terreno.Parede;
import mc322.terreno.Pilar;
import mc322.terreno.Porta;

public class GameDictionary {

	public static Entidade getTerreno(char carac, int i, int j, char aux)
	{
		Entidade retorno = null;
		switch(carac)
		{
			case '#':
				retorno = new Parede(i,j,false,aux);
				break;
			case 'a':
				retorno = new Elevado(i,j);
				break;
			case 'b':
				retorno = new Pilar(i,j);
				break;
			case 'd':
				retorno = new Porta(i,j,aux);
				break;
			case 'k':
				retorno = new Parede(i,j,true,'n');
				break;
			case 'l':
				retorno = new Parede(i,j,true,'s');
				break;
			case 'm':
				retorno = new Escada(i,j,true);
				break;
			case 'n':
				retorno = new Escada(i,j,false);
				break;
			case 'o':
				retorno = new Bau(i,j);
				break;
			default:
				System.out.println("erro ao criar entidade, caracer invalido: "+ carac);
				break;
		}
		return retorno;
	}
}
