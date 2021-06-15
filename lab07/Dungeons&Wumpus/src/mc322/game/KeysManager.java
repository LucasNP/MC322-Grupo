package mc322.game;

import mc322.engine.GameContainer;
import mc322.engine.LinearAlgebra;
import mc322.engine.Pair;

public abstract class KeysManager {

	public static void keys(GameContainer gc, Dungeon dungeon)
	{
		if(gc.getInput().isKeyDown('A'))
		{
			dungeon.getCurrentRoom().getPlayer().move('A',dungeon.getCurrentRoom());
		}
		
		if(gc.getInput().isKeyDown('D'))
		{
			dungeon.getCurrentRoom().getPlayer().move('D',dungeon.getCurrentRoom());
		}
		if(gc.getInput().isKeyDown('S'))
		{
			dungeon.getCurrentRoom().getPlayer().move('S',dungeon.getCurrentRoom());
		}
		if(gc.getInput().isKeyDown('W'))
		{
			dungeon.getCurrentRoom().getPlayer().move('W',dungeon.getCurrentRoom());
		}
		if(gc.getInput().isKeyDown(38))
		{
			dungeon.getCurrentRoom().getPlayer().move('W',dungeon.getCurrentRoom());
		}
		if(gc.getInput().isKeyDown(37))
		{
			dungeon.getCurrentRoom().getPlayer().move('A',dungeon.getCurrentRoom());
		}
		if(gc.getInput().isKeyDown(40))
		{
			dungeon.getCurrentRoom().getPlayer().move('S',dungeon.getCurrentRoom());
		}
		if(gc.getInput().isKeyDown(39))
		{
			dungeon.getCurrentRoom().getPlayer().move('D',dungeon.getCurrentRoom());
		}
		
		
		if(gc.getInput().wasClicked())
		{
			Pair<Integer,Integer> posClick = gc.getInput().getClick();
			System.out.println("Clicked at " + posClick.getFirst() + ", "+posClick.getSecond());
			posClick = LinearAlgebra.toIsometrica(posClick);
			posClick = LinearAlgebra.toCartesianas(posClick);
			System.out.println("Clicked at " + posClick.getFirst() + ", "+posClick.getSecond());
			
		}
		
	}
	
}
