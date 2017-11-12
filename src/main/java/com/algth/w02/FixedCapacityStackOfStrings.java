package main.java.com.algth.w02;

public class FixedCapacityStackOfStrings {
	private String[] s;
	private int n = 0;
	
	public FixedCapacityStackOfStrings(int capacity) {
		s = new String[capacity];
	}
	
	public boolean isEmpty() {
		return n == 0;
	}
	
	public void push(String item) {
		// use to index into array
		// then increment n
		s[n++] = item;
	}
	
	public String pop() {
		// decrement n
		// then use to index into array
		String node =  s[--n];
		s[n] = null;
		return node;
	}
}
