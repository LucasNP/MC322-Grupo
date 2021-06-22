package mc322.game;

import java.util.ArrayList;

import mc322.game.itens.Item;

public class Bag {
	
	private ArrayList<Item> itens;
	
	public Bag()
	{
		itens = new ArrayList<Item>();
	}
	
	public void addItem(Item item)
	{
		itens.add(item);
	}
	
	public Item seePocket(int position)
	{
		return itens.get(position);
	}
	
	public Item getItemAtPocket(int position)
	{
		Item removed = itens.get(position);
		itens.remove(position);
		return removed;
	}
	
	
	
	
}
