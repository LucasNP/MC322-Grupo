package mc322.game.entitiesCharacters;

import mc322.game.Room;

public abstract class Heros extends Character{

	public Heros(int i, int j,int elevation, Room room) {
		super(i, j, elevation, room);
	}

	protected abstract void interact(int i,int j);
}
