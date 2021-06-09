package mc322.game;

import mc322.engine.BasicObject;
import mc322.engine.Renderer;

public abstract class Entity implements BasicObject{
      protected String name;
      protected int elevation;
      protected int i;
      protected int j;

      
      public abstract void update(double dt);

      
      public abstract void renderer(Renderer r);
      
}
