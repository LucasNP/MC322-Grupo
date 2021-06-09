package mc322.game;

import mc322.engine.gfx.ImageTile;

import java.util.HashMap;
import java.util.Map;

public class GameMapTokens{
      private ImageTile image;
      private int tileWidth;
      private int tileHeight;

      private String PNG = ".png";
      private String CSV = ".csv";

      private String ASSETS = "/_assets";
      private String SOUNDS = "/_sounds";
      private String DATA   = "/_data";

      private Map<String, ImageTile> mapTokens;

      private String DIR_BOSSES     = ASSETS + "/bosses/";
      private String DIR_CHARACTERS = ASSETS + "/characters/";
      private String DIR_ENEMIES    = ASSETS + "/enemies/";
      private String DIR_ITENS      = ASSETS + "/itens/";
      private String DIR_MENU       = ASSETS + "/menu/";
      private String DIR_NPCS       = ASSETS + "/NPCs/";
      
      private String DIR_TILES      = ASSETS + "/tiles/";

      private String DIR_CSV        = DATA + "/layouts/";
      private String DUNGEON        = DATA + "/dungeon.txt";

      public GameMapTokens(){
            mapTokens  = new HashMap<>();
            tileHeight = 64;
            tileWidth  = 64;
      }

      // TokensTiles: blank, door, pillar, tile_half, tile_ladder, tile_side_wall, tile_wall, tile, torch
      // TokensColors: Red, Blue, Purple, Yellow, Green, White, Black
      public ImageTile getImageTile(String object, String color){
            if(mapTokens.get(object) == null){
                  String path = DIR_TILES + color + "/" + object + PNG;
                  image = new ImageTile(path, tileWidth, tileHeight);
                  mapTokens.put(object, image);
            }
            return mapTokens.get(object);
      }

      // TokensCharacter: Milo, Luna, Raju, Ze
      // TokensStates: idle, moving
      public ImageTile getImageCharacter(String object, String state){
            if(mapTokens.get(object) == null){
                  String path = DIR_CHARACTERS + object + "/" + state + PNG;
                  System.out.println(path);
                  image = new ImageTile(path, tileWidth, tileHeight);
                  mapTokens.put(object, image);
            }
            return mapTokens.get(object);
      }

}
