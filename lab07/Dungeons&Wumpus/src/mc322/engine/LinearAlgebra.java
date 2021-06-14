package mc322.engine;

import mc322.engine.Pair;

public abstract class LinearAlgebra{
      public static Pair<Integer, Integer> toIsometrica(Pair<Integer, Integer> p){
            int nx = p.getFirst() + p.getSecond();
            int ny = (p.getSecond() - p.getFirst())/2;

            Pair <Integer, Integer> np = Pair.of(nx, ny);
            return np;
      }
      
      public static Pair<Integer, Integer> toCartesianas(Pair<Integer, Integer> p){
            int nx = p.getFirst()/2 - p.getSecond();
            int ny = p.getFirst()/2 + p.getFirst(); 
            Pair <Integer, Integer> np = Pair.of(nx, ny);
            return np;
      }

      public static Pair<Integer, Integer> getOrigin(){
            Pair <Integer, Integer> origin = Pair.of(0, 0);
            return origin;
      }

      public static int getModulo(int number){
            if(number < 0) number = -number;
            return number;
      }
}
