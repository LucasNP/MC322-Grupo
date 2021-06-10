package mc322.game;

import mc322.engine.BasicObject;
import mc322.engine.Pair;
import mc322.engine.Renderer;
import mc322.engine.LinearAlgebra;

public class Dungeon implements BasicObject{
      private GameMapTokens gameMapTokens;
      private LinearAlgebra linearAlgebra;

      private Room[][] rooms;
      private Pair <Integer, Integer> pos;
      private MapBuilder mapBuilder;

      public Dungeon(){
            MapBuilder mapBuilder = new MapBuilder();
            this.rooms = mapBuilder.buildRooms(gameMapTokens.getDungeonPATH());
            this.pos   = linearAlgebra.getOrigin();
      }

      @Override
      public void update(double dt){

      }

      @Override
      public void renderer(Renderer r){
            getCurrentRoom().renderer(r);
      }

      public Room getCurrentRoom(){
            return rooms[this.pos.getFirst()][this.pos.getSecond()];
      }

}
