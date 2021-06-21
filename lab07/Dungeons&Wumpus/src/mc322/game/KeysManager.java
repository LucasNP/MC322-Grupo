package mc322.game;

import mc322.engine.GameContainer;
import mc322.engine.LinearAlgebra;
import mc322.engine.Pair;
import mc322.game.entitiesCharacters.Heroes;
import mc322.game.entitiesCharacters.Luna;
import mc322.game.entitiesCharacters.Milo;
import mc322.game.entitiesCharacters.Raju;
import mc322.game.entitiesCharacters.Ze;
import java.util.Random;

public abstract class KeysManager {

      public static void keys_movement(GameContainer gc, Dungeon dungeon){
            if(gc.getInput().isKey('W') || gc.getInput().isKey(38)){
                  dungeon.getCurrentRoom().getPlayer().move('W',dungeon.getCurrentRoom());
                  GameRenderer.change_animation_state("moving", dungeon);
                  GameBrain.walk(dungeon);
                  return;
            }
            if(gc.getInput().isKey('A') || gc.getInput().isKey(37)){
                  dungeon.getCurrentRoom().getPlayer().move('A',dungeon.getCurrentRoom());
                  GameRenderer.change_animation_state("moving", dungeon);
                  GameBrain.walk(dungeon);
                  return;
            }
            if(gc.getInput().isKey('S') || gc.getInput().isKey(40)){
                  dungeon.getCurrentRoom().getPlayer().move('S',dungeon.getCurrentRoom());
                  GameRenderer.change_animation_state("moving", dungeon);
                  GameBrain.walk(dungeon);
                  return;
            }
            if(gc.getInput().isKey('D') || gc.getInput().isKey(39)){
                  dungeon.getCurrentRoom().getPlayer().move('D',dungeon.getCurrentRoom());
                  GameRenderer.change_animation_state("moving", dungeon);
                  GameBrain.walk(dungeon);
                  return;
            }
            GameRenderer.change_animation_state("idle", dungeon);
            return;
            
            
      }


      public static void keys_action(GameContainer gc, Dungeon dungeon){

            if(gc.getInput().isKeyDown('E')){
                  Heroes player = dungeon.getCurrentRoom().getPlayer();
                  if(player instanceof Luna || player == null){
                        player = dungeon.getCurrentRoom().getMilo();
                  }
                  else if(player instanceof Milo){
                        player = dungeon.getCurrentRoom().getRaju();
                  }
                  else if(player instanceof Raju){
                        player = dungeon.getCurrentRoom().getZe();
                  }
                  else if(player instanceof Ze){
                        player = dungeon.getCurrentRoom().getLuna();
                  }
                  else System.out.println("Player error while changing character");
                  dungeon.getCurrentRoom().setPlayer(player);
            }

            if(gc.getInput().isKeyDown('Q')){
                  Heroes player = dungeon.getCurrentRoom().getPlayer();

                  if(player instanceof Luna || player == null){
                        player = dungeon.getCurrentRoom().getZe();
                  }
                  else if(player instanceof Milo){
                        player = dungeon.getCurrentRoom().getLuna();
                  }
                  else if(player instanceof Raju){
                        player = dungeon.getCurrentRoom().getMilo();
                  }
                  else if(player instanceof Ze){
                        player = dungeon.getCurrentRoom().getRaju();
                  }
                  else System.out.println("Player error while changing character");
                  dungeon.getCurrentRoom().setPlayer(player);
            }

            if(gc.getInput().isKeyDown('T')){
                  dungeon.toggleFollow();
            }
            
            if(gc.getInput().isKeyDown('O')){
                dungeon.getCurrentRoom().open();
            }
            
            

            if(gc.getInput().wasClicked()){
                  //Pair<Integer,Integer> posClick = gc.getInput().getClick();
                  //System.out.println("Clicked at " + posClick.getFirst() + ", "+posClick.getSecond());
            }
      }
      
      public static void keys_game_flow(GameContainer gc, GameManager game)
      {
    	  if(gc.getInput().isKeyDown('P')){
                game.togglePause();
            }
      }
      
      public static void mouse_action(GameContainer gc, Dungeon dungeon){
          if(gc.getInput().wasClicked()){
                Pair<Integer,Integer> posClick = gc.getInput().getClick();

                posClick = LinearAlgebra.toCartesianas(posClick);

                int x = posClick.getFirst() + 332+32;
                int y = posClick.getSecond() - 475+32;
                x /= 32;
                y /= 32;

                if( x > 14 || x < 0 || y < 0 || y > 14 ) return;
                //System.out.println(x + " , " + y);
                dungeon.getCurrentRoom().getPlayer().follow(x, y, dungeon.getCurrentRoom(),true);
          }
    }
      

}
