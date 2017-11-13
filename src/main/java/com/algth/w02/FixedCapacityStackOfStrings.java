package main.java.com.algth.w02;

public class FixedCapacityStackOfStrings<Item> {
	private Item[] s;
	private int n = 0;
	
	public FixedCapacityStackOfStrings(int capacity) {
		s = new Item[capacity];
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public void push(Item item) {
		// use to index into array
		// then increment n
		s[n++] = item;
	}
	
	public Item pop() {
		// decrement n
		// then use to index into array
		Item node =  s[--n];
		s[n] = null;
		return node;
	}
}
