package mc322.game;

import mc322.engine.GameContainer;

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
	}
	
}
