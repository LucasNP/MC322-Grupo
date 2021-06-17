package mc322.engine;

import java.util.ArrayList;

import mc322.engine.Pair;

public abstract class LinearAlgebra{
      public static Pair<Integer, Integer> toIsometrica(Pair<Integer, Integer> p){
            int nx = p.getFirst() + p.getSecond();
            int ny = (p.getSecond() - p.getFirst())/2;

            Pair <Integer, Integer> np = Pair.of(nx, ny);
            return np;
      }
      
      public static Pair<Integer, Integer> toCartesianas(Pair<Integer, Integer> p){
    	  int ny = (p.getSecond()*2 + p.getFirst())/2;
    	  int nx = p.getFirst() - ny;
//    	  int nx = p.getFirst()/2 - p.getSecond();
//        int ny = p.getFirst()/2 + p.getFirst();
            
            Pair <Integer, Integer> np = Pair.of(nx, ny);
            return np;
      }

      public static Pair<Integer, Integer> getOrigin(){
            Pair <Integer, Integer> origin = Pair.of(11, 7);
            return origin;
      }

      public static int getModulo(int number){
            if(number < 0) number = -number;
            return number;
      }
      
      public static int clamp(int value, int min, int max )
  	{
  		if(value<min)
  			return min;
  		if(value>max)
  			return max;
  		return value;
  	}
  	
  	public static boolean between(int value, int min, int max)
  	{
  		if(value<min)
  			return false;
  		if(value>max)
  			return false;
  		return true;
  	}
  	
  	public static char[][] solveMaze(char map[][]) // for Square maps
  	{
  		ArrayList<Pair<Pair<Integer,Integer>,Integer>> news = new ArrayList<Pair<Pair<Integer,Integer>,Integer>>();
  		ArrayList<Pair<Pair<Integer,Integer>,Integer>> newNews = new ArrayList<Pair<Pair<Integer,Integer>,Integer>>();
  		int dirI[] = {1,0,0,-1};
  		int dirJ[] = {0,-1,1,0};
  		
  		for(int i = 0;i<map.length;i++)
  		{
  			for(int j = 0;j<map.length;j++)
  			{
  				if(map[i][j]=='E')
  					newNews.add(Pair.of(Pair.of(i,j),1));
  				
  			}
  		}
  		
  		boolean running = true;
  		
  		int counting = 0;
  		while(running && counting <map.length*map.length/2)
  		{
  			for(int i = 0;i<newNews.size();i++)
  			{
  				news.add(newNews.get(i));
  			}
  			
  			newNews.clear();
  			
  			if(news.size()==0)
  			{
  				running = false;
  				break;
  			}
  			
  			for(int i = 0;i<news.size();i++)
  			{
  				for(int k =0;k<4;k++)
  				{
  					if(!between(news.get(i).getFirst().getFirst()+dirI[k],0,map.length-1) || !between(news.get(i).getFirst().getSecond()+dirJ[k],0,map.length-1))
  						continue;
  					if(map[news.get(i).getFirst().getFirst()+dirI[k]][news.get(i).getFirst().getSecond()+dirJ[k]]=='.' || map[news.get(i).getFirst().getFirst()+dirI[k]][news.get(i).getFirst().getSecond()+dirJ[k]]=='B')
  					{
  						if(map[news.get(i).getFirst().getFirst()+dirI[k]][news.get(i).getFirst().getSecond()+dirJ[k]]=='B')
  						{
  							running = false;
  						}
  						
  						switch(k)
  						{
  							case 0:
  								map[news.get(i).getFirst().getFirst()+dirI[k]][news.get(i).getFirst().getSecond()+dirJ[k]] = 'A';
  								break;
  							case 1:
  								map[news.get(i).getFirst().getFirst()+dirI[k]][news.get(i).getFirst().getSecond()+dirJ[k]] = '>';
  								break;
  							case 2:
  								map[news.get(i).getFirst().getFirst()+dirI[k]][news.get(i).getFirst().getSecond()+dirJ[k]] = '<';
  								break;
  							case 3:
  								map[news.get(i).getFirst().getFirst()+dirI[k]][news.get(i).getFirst().getSecond()+dirJ[k]] = 'V';
  								break;
  						}
  						
  						newNews.add(Pair.of(Pair.of(news.get(i).getFirst().getFirst()+dirI[k], news.get(i).getFirst().getSecond()+dirJ[k]),0));
  					}
  				}
  				if(!running)
  					break;
  			}
  			news.clear();
  			counting ++;
  		}
  		
  		return map;
  	}
}
