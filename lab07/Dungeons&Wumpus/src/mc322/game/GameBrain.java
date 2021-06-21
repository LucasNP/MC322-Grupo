package mc322.game;

import java.util.ArrayList;

import mc322.engine.GameContainer;
import mc322.engine.LinearAlgebra;
import mc322.engine.Pair;
import mc322.game.entitiesCharacters.Heroes;
import mc322.game.entitiesCharacters.Luna;
import mc322.game.entitiesCharacters.Milo;
import mc322.game.entitiesCharacters.Raju;
import mc322.game.entitiesCharacters.Ze;
import java.util.Random;

public abstract class GameBrain{

      public static Pair<Integer, Integer> getOrigin(){
            
            //Purple:
            //Pair <Integer, Integer> origin = Pair.of(11, 1);
            
            //Green:
            //Pair <Integer, Integer> origin = Pair.of(8, 12);

            //Yellow:
            Pair <Integer, Integer> origin = Pair.of(11, 7);

            //Blue:
            //Pair <Integer, Integer> origin = Pair.of(10, 13);

            //Red:
            //Pair <Integer, Integer> origin = Pair.of(13, 12);

            //Black
            //Pair <Integer, Integer> origin = Pair.of(3, 14);


            return origin;
      }

      
      public static char[][] solveMaze(char map[][],int iBeg,int jBeg, int iEnd, int jEnd) // for Square maps
  	{
  		char auxMap[][] = new char[map.length][map.length];
  		for(int i = 0;i<map.length;i++)
  		{
  			for(int j = 0;j<map.length;j++)
  			{
  				auxMap[i][j] = map[i][j];
  			}
  		}
  		
  		//set begin
  		if(map[iBeg][jBeg]=='#')
  			throw new ImpossibleOriginOrDestiny();
  		
  		//set end
  		if(map[iEnd][jEnd]=='.')
  			map[iEnd][jEnd]='e';
  		else if(map[iEnd][jEnd]=='#')
  			throw new ImpossibleOriginOrDestiny();
  		else
  			map[iEnd][jEnd]='E';
  		
  		//arrays of new points
  		ArrayList<Pair<Integer,Integer>> news = new ArrayList<Pair<Integer,Integer>>();
  		ArrayList<Pair<Integer,Integer>> newNews = new ArrayList<Pair<Integer,Integer>>();
  		
  		//directions
  		int dirI[] = {1,0,0,-1};
  		int dirJ[] = {0,-1,1,0};
  		
  		//add origin
  		newNews.add(Pair.of(iEnd,jEnd));

  		
  		boolean running = true;
  		
  		int counting = 0;
  		while(running && counting <map.length*map.length/2)
  		{
  			//pass all newnews points to new
  			news.clear();
  			for(int i = 0;i<newNews.size();i++)
  			{
  				news.add(newNews.get(i));
  			}
  			newNews.clear();
  			
  			//if there is no points to solve, break
  			if(news.size()==0)
  			{
  				running = false;
  				break;
  			}
  			
  			//run all points
  			for(int i = 0;i<news.size();i++)
  			{
  				if(news.get(i).getFirst() == iBeg && news.get(i).getSecond()== jBeg)
  				{
  					running = false;
  					break;
  				}
  				
  				//in all directions
  				for(int k =0;k<4;k++)
  				{
  					//inside the map
  					if(!LinearAlgebra.between(news.get(i).getFirst()+dirI[k],0,map.length-1) || !LinearAlgebra.between(news.get(i).getSecond()+dirJ[k],0,map.length-1))
  						continue;
  					
  					//to think better solutions, the v and c comments are relative to the character walking (and not this algoritm)
  					//where is under me
  					char v = map[news.get(i).getFirst()+dirI[k]][news.get(i).getSecond()+dirJ[k]];
  					//what i go
  					char c = auxMap[news.get(i).getFirst()][news.get(i).getSecond()];
  					
  					if(v=='#'||v=='E'||v=='e'||v=='A'||v=='V'||v=='<'||v=='>')
  						continue;
  						
  					
					if(v=='U')
					{
						if(c=='.')
							continue;
						if(c=='M')
							if(k==1||k==2)
								continue;
						if(c=='N')
							if(k==0||k==3)
								continue;
						
					}
					if(v=='.')
					{
						if(c=='U')
							continue;
						if(c=='M')
							if(k==1||k==2)
								continue;
						if(c=='N')
							if(k==0||k==3)
								continue;
						
					}
					
					
					switch(k)
					{
						case 0:
							map[news.get(i).getFirst()+dirI[k]][news.get(i).getSecond()+dirJ[k]] = 'A';
							break;
						case 1:
							map[news.get(i).getFirst()+dirI[k]][news.get(i).getSecond()+dirJ[k]] = '>';
							break;
						case 2:
							map[news.get(i).getFirst()+dirI[k]][news.get(i).getSecond()+dirJ[k]] = '<';
							break;
						case 3:
							map[news.get(i).getFirst()+dirI[k]][news.get(i).getSecond()+dirJ[k]] = 'V';
							break;
					}
					
					newNews.add(Pair.of(news.get(i).getFirst()+dirI[k], news.get(i).getSecond()+dirJ[k]));
  				}
  				if(!running)
  					break;
  			}
  			counting ++;
  		}
  		
  		return map;
  	}

      static void walk(Dungeon dungeon) {
            if(!dungeon.getFollow()){
                  GameRenderer.change_animation_state("idle", dungeon);
                  dungeon.getCurrentRoom().getPlayer().change_state("moving");
                  return;
            }

            Room cRoom = dungeon.getCurrentRoom();
            Random rand = new Random();

            if(cRoom.getMilo() != cRoom.getPlayer()){
                  if(rand.nextInt(9)<7) cRoom.getMilo().follow(cRoom.getLuna(),cRoom);
                  else cRoom.getMilo().follow(cRoom.getRaju(),cRoom);
            }

            if(cRoom.getLuna() != cRoom.getPlayer()){
                  if(rand.nextInt(13)<9) cRoom.getLuna().follow(cRoom.getPlayer(),cRoom);
                  else cRoom.getLuna().follow(cRoom.getZe(),cRoom);
            }

            if(cRoom.getZe() != cRoom.getPlayer()) {
                  if(rand.nextInt(15)<8) cRoom.getZe().follow(cRoom.getRaju(),cRoom);
                  else
                        cRoom.getZe().follow(cRoom.getLuna(),cRoom);
            }

            if(cRoom.getRaju() != cRoom.getPlayer()){
                  if(rand.nextInt(8)<5) cRoom.getRaju().follow(cRoom.getMilo(),cRoom);
                  else cRoom.getRaju().follow(cRoom.getLuna(),cRoom);
            }
      }

}
