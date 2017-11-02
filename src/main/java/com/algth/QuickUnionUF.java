package main.java.com.algth;
/**
 * This is a costly method. Prefered Weighted approach
 *
 */
public class QuickUnionUF {
	private int[] id;
	
	/**
	 * Set ID of each object to itself
	 * (n array accesses)
	 * @param n
	 */
	public QuickUnionUF(int n) {
		id = new int[n];
		for(int i = 0; i < n; i++) {
			id[i] = i;
		}
	}
	
	/**
	 * Chase parent pointers until reach root
	 * (depth of i array accesses: N)
	 * @param i
	 * @return
	 */
	private int root(int i) {
		while(i != id[i]) {
			i = id[i];
		}
		return i;
	}
	
	/**
	 * Check if p and q have same root
	 * (depth of p and q array accesses)
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	/**
	 * Change root of p to point to root of q
	 * (depth of p and array accesses)
	 * @param p
	 * @param q
	 */
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		id[i] = j;
	}
}
