package mc322.game;

import java.util.*;
import mc322.engine.Renderer;
import mc322.engine.gfx.ImageTile;

public class GameRenderer {

      public static void drawTile(int i,int j,double elevation, String name, Renderer r, int updateX, int updateY, String color){
            ImageTile image = GameMapTokens.getImageTile(name, color);
            //elevation+=0.5;
            r.drawIsometricImage(i+(int)elevation, j-(int)elevation, image, updateX, updateY);
      }

      public static void drawItem(int i,int j,double elevation, String name, Renderer r, int updateX, int updateY){
            ImageTile image = GameMapTokens.getImageItem(name, "Purple");
            //elevation+=0.5;
            r.drawIsometricImage(i+(int)elevation, j-(int)elevation, image, updateX, updateY);
      }

      public static void drawCharacter(int i,int j,double elevation, String name, Renderer r, int updateX,int dir ,String state){
            ImageTile image = GameMapTokens.getImageCharacter(name, state);
            elevation+=0.5;
            r.drawIsometricImage(i+(int)elevation, j-(int)elevation, image, updateX, dir);
      }


      public static void change_animation_state(String state, Dungeon dungeon){
            Room cRoom = dungeon.getCurrentRoom();

            cRoom.getMilo().change_state(state);
            cRoom.getLuna().change_state(state);
            cRoom.getRaju().change_state(state);
            cRoom.getZe().change_state(state);

            return;
      }


      public static void drawBackground(Renderer r, double timming_background_light){
            int red, green, blue;
            int pH = r.getHeight();
            int pW = r.getWidth();
            
            double seno = Math.sin(timming_background_light);
            seno *= 70;
            int lim = (int) seno + 500;


            for(int i = lim; i > 0; i--){
                  red = green = blue = i/4;
                  blue *=2;
                  red *=2;

                  int a = 1;
                  int alpha = (int)(a * 255.99);

                  String hex = String.format("%02x%02x%02x%02x", alpha, red, green, blue);
                  r.drawCirc(pW/2, pH/2, lim/2-i, (int) Long.parseLong(hex, 16) );
                  //break;
            }
            //r.drawLine(10, 10, pW, pH, 0xffffffff);

      }

}
