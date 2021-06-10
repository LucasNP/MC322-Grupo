package mc322.game;

import mc322.engine.BasicObject;
import mc322.engine.Renderer;

public abstract class Entity implements BasicObject{
      protected String name;
      protected int elevation;
      protected int i;
      protected int j;

      @Override
      public abstract void update(double dt);

      @Override
      public abstract void renderer(Renderer r);
}
