package mc322.game;

import mc322.engine.BasicObject;
import mc322.engine.Renderer;

public abstract class Entity implements BasicObject{
      protected String path;
      protected int elevation;
      protected int i;
      protected int j;

      @Override
      public void update(double dt){

      }

      @Override
      public void renderer(Renderer r){

      }
      
}
