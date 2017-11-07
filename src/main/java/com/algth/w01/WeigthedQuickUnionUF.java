package main.java.com.algth.w01;
/**
 * This is a costly method. Prefered Weighted approach
 *
 */
public class WeigthedQuickUnionUF {
	private int[] id;
	private int[] sz;
	
	/**
	 * Set ID of each object to itself
	 * (n array accesses)
	 * @param n
	 */
	public WeigthedQuickUnionUF(int n) {
		id = new int[n];
		for(int i = 0; i < n; i++) {
			id[i] = i;
		}
	}
	
	/**
	 * Chase parent pointers until reach root
	 * (depth of i array accesses)
	 * @param i
	 * @return
	 */
	private int root(int i) {
		while(i != id[i]) {
			id[i] = id[id[i]];
			i = id[i];
		}
		return i;
	}
	
	/**
	 * Check if p and q have same root
	 * (takes time proportional to depth of p and q: log(N) )
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean connected(int p, int q) {
		return root(p) == root(q);
	}
	
	/**
	 * Change root of p to point to root of q
	 * (takes constant time, given roots: log(N) )
	 * @param p
	 * @param q
	 */
	public void union(int p, int q) {
		int i = root(p);
		int j = root(q);
		if(i == j) return;
		if(sz[i] < sz[j]) {
			id[i] = j;
			sz[j] += sz[i];
		} else {
			id[j] = i;
			sz[i] += sz[j];
		}
	}
}
