package mc322.game;

import mc322.engine.Pair;
import mc322.game.entitiesTiles.*;

public abstract class EntityTilesLoader {

      public static Pair<Entity, Entity> getEntity(char token, int i, int j, String dir){
            Pair<Entity, Entity> entityTile = null;

            boolean internal;
            int elevation = 0;

            switch(token){
                  // External Wall;
                  case '#':
                        internal = false;
                        entityTile = Pair.of(new Wall(i, j, internal, dir,elevation),null);
                        break;

                  // Internal Wall;
                  case 'l':
                        internal = true;
                        dir = "west";
                        entityTile = Pair.of(new Wall(i, j, internal, dir, elevation),null);
                        break;
                  case 'L':
                        internal = true;
                        elevation = 1;
                        dir = "west";
                        entityTile = Pair.of(new Platform(i,j),new Wall(i, j, internal, dir, elevation));
                        break;
                  case 'k':
                        internal = true;
                        dir = "north";
                        entityTile = Pair.of(new Wall(i, j, internal, dir, elevation),null);
                        break;
                  case 'K':
                        internal = true;
                        elevation = 1;
                        dir = "north";
                        entityTile = Pair.of(new Platform(i,j),new Wall(i, j, internal, dir, elevation));
                        break;

                        // Door
                  case 'd':
                        entityTile = Pair.of(new Door(i, j, dir,elevation),null);
                        break;

                  // Elevated Floor
                  case 'a':
                        entityTile = Pair.of(new Platform(i, j),null);
                        break;

                  // Ladder
                  case 'm':
                        dir = "north-south";
                        entityTile = Pair.of(new Ladder(i, j, dir,elevation),null);
                        break;
                  case 'n':
                        dir = "west-east";
                        entityTile = Pair.of(new Ladder(i, j, dir,elevation),null);
                        break;

                  // Pillar
                  case 'b':
                        entityTile = Pair.of(new Pillar(i, j,dir,elevation),null);
                        break;

                  // SafeZone
                  case 's':
                        entityTile = Pair.of(new SafeZone(i, j),null);
                        break;

                  // Chest
                  case 'o':
                        dir = "north-south";
                        entityTile = Pair.of(new Chest(i, j, dir, elevation),null);
                        break;
                  case 'O':
                        dir = "north-south";
                        elevation = 1;
                        entityTile = Pair.of(new Platform(i, j),new Chest(i, j, dir, elevation));
                        break;
                  case 'r':
                        dir = "east-west";
                        entityTile = Pair.of(new Chest(i, j, dir, elevation),null);
                        break;
                  case 'R':
                        dir = "east-west";
                        elevation = 1;
                        entityTile = Pair.of(new Platform(i, j),new Chest(i, j, dir, elevation));
                        break;

                  // Blank Space
                  case '.':
                        break;

                  default:
                        System.out.println("Error while loading EntityTile");
                        break;
            }

            return entityTile;
      }
}
