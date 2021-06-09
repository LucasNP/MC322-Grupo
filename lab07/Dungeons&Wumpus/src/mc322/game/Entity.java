package mc322.game;

import mc322.engine.BasicObject;
import mc322.engine.Renderer;

public abstract class Entity implements BasicObject{
      protected String name;
      protected int elevation;
      protected int i;
      protected int j;
      protected int updateDir;
      protected int updateFrame;

      
      public abstract void update(int dt);

      
      public abstract void renderer(Renderer r);
      
}
