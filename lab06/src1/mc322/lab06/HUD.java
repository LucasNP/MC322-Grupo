package mc322.lab06;

//mod
public class HUD
{
    private int flechas;
    private int pontos;
    private boolean ouro;
    private boolean brisa;
    private boolean fedor;
    private boolean flechaEquipada;
    
    
    public HUD(){
        this.flechas=1;
        this.pontos=0;
        this.ouro=false;
        this.flechaEquipada = false;
    }
    
    public int getPontos()
    {
        return this.pontos;
    }
    
    public void mostrar(char aviso)
    {
        System.out.println("tenho "+this.pontos+" pontos!");
        if(this.fedor)
            System.out.println("Esse lugar estÃ¡ fedendo!");
        if(this.brisa)
            System.out.println("Estou sentindo um ventinho!");
        if(this.ouro)
            System.out.println("O ouro pesa '-.-!");
        if(this.flechas ==0)
        {
            System.out.println("Acabaram as flechas D:!");
        }
        else if(this.flechas ==1)
            System.out.println("Eu ainda tenho uma flecha :)");
        else
            System.out.println("Eu ainda tenho " + this.flechas + " flechas :)");
        if(aviso == 'b')
        {
        	System.out.println("Sinto uma brisa");
        }
        else if(aviso =='f')
        {
        	System.out.println("Sinto um fedor");
        }
        
    }
    
    public void pegarOuro()
    {
        this.ouro = true;
    }
    
    public boolean carregandoOuro()
    {
        return this.ouro;
    }
    
    public boolean equiparFlecha(Console console)
    {
        if(flechas>0)
        {
            this.flechas--;
            this.incrementarPonto(-100);
            this.flechaEquipada = true;
            console.pensar("Equipei a flecha");
            return true;
        }
        console.pensar("Não tenho mais flechas!");
        return false;
        
    }
    
    public void atirar(Console console, String nomeJogador)
    {
    	console.falar(nomeJogador+" atirou");
    	this.flechaEquipada = false;
    }
    
    public boolean flechaEstaEquipada()
    {
    	return this.flechaEquipada;
    }
    
    public void incrementarPonto(int adicionar)
    {
        this.pontos+=adicionar;
    }
}
