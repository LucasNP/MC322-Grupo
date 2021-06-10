package mc322.game;

import mc322.engine.BasicObject;
import mc322.engine.Renderer;

public abstract class Entity implements BasicObject{
      
      protected String name;
      
      protected boolean initAnimation;
      protected int velocityAnim;
      protected int nFrames;

      protected int elevation;
      protected int i;
      protected int j;
      protected int updateDir;
      protected double updateFrame;

      
      public abstract void update(double dt);
      public abstract void renderer(Renderer r);
      public abstract void toggleAnimation();
}
