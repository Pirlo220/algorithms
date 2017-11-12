package main.java.com.algth.w02;

/***
 * In order to resize, every time the stack is full you double its capacity. It is called amortized analysis
 * It take 3N accesses
 * @author sergio
 *
 */
public class ResizingArrayStackOfStrings {
	private String[] s;
	private int N;

	public ResizingArrayStackOfStrings() {
		s = new String[1];
	}
	
	public void push(String item){
		if(N == s.length) {
			resize(2 * s.length);
		}
		s[N++] = item;
	}
	
	private void resize(int capacity) {
		String[] copy = new String[capacity];
		for (int i = 0; i < N; i++) {
			copy[i] = s[i];
		}
		s = copy;
	}
}
