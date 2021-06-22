package mc322.game;

import java.util.ArrayList;

import mc322.engine.BasicObject;
import mc322.engine.Pair;
import mc322.engine.Renderer;
import mc322.game.itens.Item;

public class Bag implements BasicObject{
	
	private ArrayList<Pair<Item,Integer>> itens;
	
	public Bag()
	{
		itens = new ArrayList<Pair<Item,Integer>>();
	}
	
	public void addItem(Item item)
	{
		for(int i =0;i<itens.size();i++)
		{
			if(item.getClass().equals(itens.get(i).getFirst().getClass()))
			{
				itens.add(Pair.of(itens.get(i).getFirst(),itens.get(i).getSecond()+1));
				itens.remove(i);
				return;
			}
			
		}
		itens.add(Pair.of(item,1));
	}
	
	public Pair<Item,Integer> seePocket(int position)
	{
		return itens.get(position);
	}
	
	public Item getItemAtPocket(int position)
	{
		Pair<Item,Integer> removed = itens.get(position);
		itens.remove(position);
		if(removed.getSecond() >1) itens.add(Pair.of(removed.getFirst(),removed.getSecond()-1));
		return removed.getFirst();
		
	}
	
	public String toString()
	{
		String ans = "";
		for(int i =0;i<itens.size();i++)
		{
			ans+=itens.get(i).getFirst()+ " ("+itens.get(i).getSecond()+") ";
			if(i!=itens.size()-1)
				ans+=", ";
		}
		return ans;
	}

	public void update(double dt) {
		
		
	}

	public void renderer(Renderer r) {
		
		
	}
	
	
	
	
}
