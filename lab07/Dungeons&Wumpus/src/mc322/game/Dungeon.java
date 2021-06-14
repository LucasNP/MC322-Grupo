package mc322.game;

import mc322.engine.BasicObject;
import mc322.engine.Pair;
import mc322.engine.Renderer;
import mc322.engine.LinearAlgebra;

public class Dungeon implements BasicObject{

      private MapBuilder mapBuilder;
      private Room[][] rooms;
      private Pair <Integer, Integer> pos;

      public Dungeon(){
            MapBuilder mapBuilder = new MapBuilder();
            this.rooms = mapBuilder.buildRooms(GameMapTokens.getDungeonPATH());
            this.pos   = LinearAlgebra.getOrigin();
      }

      @Override
      public void update(double dt){
    	  getCurrentRoom().update(dt);
      }

      @Override
      public void renderer(Renderer r){
            getCurrentRoom().renderer(r);
      }

      public Room getCurrentRoom(){
            return rooms[this.pos.getFirst()][this.pos.getSecond()];
      }

}
