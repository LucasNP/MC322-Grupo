package mc322.game;

import mc322.game.entitiesTiles.*;

public class EntityTilesLoader {

      public static Entity getEntity(char token, int i, int j, String dir){
            Entity entityTile = null;

            boolean interna;
            int elevation;

            switch(token){
                   //External Wall;
                  case '#':
                        interna = false;
                        entityTile = new Wall(i, j, interna, dir);
                        break;

                   //Internal Wall;
                  case 'L':
                  case 'l':
                        interna = true;
                        dir = "north-south";
                        if(Character.isUpperCase(token)) elevation = 1;
                        entityTile = new Wall(i, j, interna, dir, elevation);
                        break;
                  case 'K':
                  case 'k':
                        interna = true;
                        dir = "west-east";
                        if(Character.isUpperCase(token)) elevation = 1;
                        entityTile = new Wall(i, j, interna, dir, elevation);
                        break;

                         //Door
                  case 'd':
                        entityTile = new Door(i, j, dir);
                        break;

                   //Elevated Floor
                  case 'a':
                        entityTile = new Platform(i, j);
                        break;

                   //Ladder
                  case 'm':
                        dir = "north-south";
                        entityTile = new Ladder(i, j, dir);
                        break;
                  case 'n':
                        dir = "west-east";
                        entityTile = new Ladder(i, j, dir);
                        break;

                   //Pillar
                  case 'b':
                        entityTile = new Pillar(i, j);
                        break;

                  // SafeZone
                  case 's':
                        entityTile = new SafeZone(i, j);
                        break;

                   //Chest
                  case 'o':
                  case 'O':
                        interna = true;
                        dir = "north-south";
                        if(Character.isUpperCase(token)) elevation = 1;
                        entityTile = new Chest(i, j, interna, dir, elevation);
                        break;
                  case 'r':
                  case 'R':
                        interna = true;
                        dir = "east-west";
                        if(Character.isUpperCase(token)) elevation = 1;
                        entityTile = new Chest(i, j, interna, dir, elevation);
                        break;

                   //Blank Space
                  case '.':
                        break;

                  default:
                        System.out.println("Error while loading EntityTile");
                        break;
            }

            return entityTile;
      }
}
