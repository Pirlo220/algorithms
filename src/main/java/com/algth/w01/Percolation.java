package main.java.com.algth.w01;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private final WeightedQuickUnionUF wuf;
	private final boolean[] grid;
	private final int nValue;
	private final int top;
	private final int bottom;
	private int num = 0;
	
	public Percolation(int n) {
		// create n-by-n grid, with all sites blocked
		if(n <= 0) {
			throw new IllegalArgumentException();
		}
		this.wuf = new WeightedQuickUnionUF((n * n) + 2);
		this.grid = new boolean[n * n];
		this.top = n * n;
		this.bottom = n + n + 1;
		// union first row
		for (int i = 0; i < n; i++) {
			this.wuf.union(i, this.top);
		}
		// union last row
		for (int i = ((n * n) - n); i < (n * n); i++) {
			wuf.union(i, this.bottom);
		}
		
		this.nValue = n;
	}
	
	 /**
     * Validate the row and col indices.
     *
     * @param row row index
     * @param col col index
     */
    private void validate(int row, int col) {
        if (row <= 0 || row >= this.nValue) {
            throw new IndexOutOfBoundsException("Invalid input : row index out of bounds !");
        }
        if (col <= 0 || row >= this.nValue) {
            throw new IndexOutOfBoundsException("Invalid input : col index out of bounds !");
        }
    }

	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		validate(row, col);
		if (isOpen(row, col)) return;
		
		int currentIndex = (row  * nValue - (nValue - col)) - 1;
				
		this.grid[currentIndex] = true;
		this.num++;		
		
		// Top neighbor
		if (row > 1) {
			int topNeighbor = ((row -1) * nValue - (nValue - col)) - 1;
			if (isOpen((row - 1), col)) {
				wuf.union(topNeighbor, currentIndex);
			}
		}
		
		// LEFT neighbor		
		if(col > 1) {
			int leftNeighbor = (row  * nValue - (nValue - (col -1))) - 1;			
			if (isOpen(row, col - 1)){
				wuf.union(leftNeighbor, currentIndex);
			}
		}
		
		// RIGHT neighbor		
		if (col < nValue) {
			int rightNeighbor = (row  * nValue - (nValue - (col + 1))) - 1;			
			if (isOpen(row, col + 1)){
				wuf.union(rightNeighbor, currentIndex);
			}
		}
		
		// Bottom neighbor
		if (row < nValue) {
			int bottomNeighbor = ((row + 1) * nValue - (nValue - col)) - 1;
			if (isOpen(row + 1, col)) {
				wuf.union(bottomNeighbor, currentIndex);
			}
		}		
	}

	// is site (row, col) open?
	public boolean isOpen(int row, int col) {		
		return grid[(row  * nValue - (nValue - col)) - 1];
	}
	
	// is site (row, col) full?
	public boolean isFull(int row, int col) {
		return wuf.connected(top, (row - 1) * (col - 1));		
	}
	
	// number of open sites
	public int numberOfOpenSites() {
		return num;
	}

	public boolean percolates() {
		return wuf.connected(top, bottom);
	}
	  // test client, described below
    
    public static void main(String[] args) {
        In in = new In(args[0]);
        int n = in.readInt();
        Percolation percolation = new Percolation(n);
        boolean isPercolated = false;
        int count = 0;
        while (!in.isEmpty()) {
            int row = in.readInt();
            int col = in.readInt();
            if (!percolation.isOpen(row, col)) {
                count++;
            }
            percolation.open(row, col);
            isPercolated = percolation.percolates();
            if (isPercolated) {
                break;
            }
        }
        StdOut.println(count + " open sites");
        if (isPercolated) {
            StdOut.println("percolates");
        } else {
            StdOut.println("does not percolate");
        }

	
    }
}
