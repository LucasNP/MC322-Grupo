package mc322.game.entitiesCharacters;

public abstract class Heros extends Character{

	public Heros(int i, int j,int elevation) {
		super(i, j, elevation);
	}

	protected abstract void interact(int i,int j);
}
