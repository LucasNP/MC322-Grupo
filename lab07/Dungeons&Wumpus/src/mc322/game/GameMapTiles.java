package mc322.game;

import mc322.engine.gfx.Image;
import mc322.engine.gfx.ImageTile;

import java.util.HashMap;
import java.util.Map;

public class GameMapTiles{
      private int tileWidth;
      private int tileHeight;

      private Map<Character, String> mapTokens;
      private Map<Character, ImageTile> mapTiles;

      private String ALPHABET;
      private String DIR;

      public GameMapTiles(){
            mapTiles  = new HashMap<>();
            mapTokens = new HashMap<>();
            
            tileHeight = 64;
            tileWidth  = 64;

            DIR =  "assets";
            ALPHABET = ".abcde";

            FillTokens();
            FillMapTiles();
      }

      public void FillTokens(){
            mapTokens.put('.', DIR+"/tiles/Purple/blank.png");
            mapTokens.put('a', DIR+"/tiles/Purple/tile.png");
            mapTokens.put('b', DIR+"/tiles/Purple/pilar.png");
            mapTokens.put('c', DIR+"/tiles/Purple/tile_wall.png");
            mapTokens.put('d', DIR+"/tiles/Purple/metal_door.png");
            mapTokens.put('e', DIR+"/tiles/Purple/torch.png");

      }

      public void FillMapTiles(){
            ImageTile image;
            for(int i = 0; i < ALPHABET.length(); i++){
                  char c = ALPHABET.charAt(i);
                  String path = mapTokens.get(c);
                  image = new ImageTile(path, tileWidth, tileHeight);
                  mapTiles.put(c, image);
            }
      }

      // Getters
      public ImageTile getImageTile(char c){
            return mapTiles.get(c);
      }

}
