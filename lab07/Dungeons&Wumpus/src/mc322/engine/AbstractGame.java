package mc322.engine;

public abstract interface AbstractGame{
      
      public abstract void update(GameContainer gc, double dt);
      public abstract void renderer(GameContainer gc, Renderer r);

}
