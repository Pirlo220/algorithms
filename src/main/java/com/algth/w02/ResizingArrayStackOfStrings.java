package main.java.com.algth.w02;

/***
 * In order to resize, every time the stack is full you double its capacity. 
 * It is called amortized analysis.
 * It take 3N accesses.
 *
 */
public class ResizingArrayStackOfStrings {
	private String[] s;
	private int N = 0;

	public ResizingArrayStackOfStrings() {
		s = new String[1];
	}

	public void push(String item){
		if(N == s.length) {
			resize(2 * s.length);
		}
		s[N++] = item;
	}
	
	/**
	 * When the size is a quarter of the length of the array we should halve. 
	 * 
	 */
	public String pop() {
		String item = s[--N];
		s[N] = null;
		if(N > 0 && N == s.length / 4) {
			resize(s.length / 2);
		}
		return item;
	}
	
	private void resize(int capacity) {
		String[] copy = new String[capacity];
		for (int i = 0; i < N; i++) {
			copy[i] = s[i];
		}
		s = copy;
	}
}
