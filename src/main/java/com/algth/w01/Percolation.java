package main.java.com.algth.w01;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import javafx.scene.input.TouchPoint;

public class Percolation {
	private WeightedQuickUnionUF wuf;
	private boolean[] grid;
	private int nValue;
	private int top;
	private int bottom;
	
	public Percolation(int n) {
		// create n-by-n grid, with all sites blocked
		if(n <= 0) {
			new IllegalArgumentException();
		}
		wuf = new WeightedQuickUnionUF((n * n) + 2);
		grid = new boolean[n * n];
		top = n * n;
		bottom = n + n + 1;
		// union first row
		for(int i = 0; i < n; i++) {
			wuf.union(i, top);
		}
		// union last row
		for(int i = ((n * n) - n); i < (n * n); i++) {
			wuf.union(i, bottom);
		}
		
		nValue = n;
	}

	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		int currentIndex = (row  * nValue - (nValue - col)) - 1;
		if(!isOpen(row, col)) {
			grid[currentIndex] = true;	
		}
		
		// Top neighbor
		if(row > 1) {
			int topNeighbor = ((row -1) * nValue - (nValue - col)) - 1;
			if(isOpen((row - 1), col)) {
				wuf.union(topNeighbor, currentIndex);
			}
		}
		
		// LEFT neighbor		
		if(col > 1) {
			int leftNeighbor = (row  * nValue - (nValue - (col -1))) - 1;			
			if(isOpen(row, col - 1)){
				wuf.union(leftNeighbor, currentIndex);
			}
		}
		
		// RIGHT neighbor		
		if(col < nValue) {
			int rightNeighbor = (row  * nValue - (nValue - (col + 1))) - 1;			
			if(isOpen(row, col + 1)){
				wuf.union(rightNeighbor, currentIndex);
			}
		}
		
		// Bottom neighbor
		if(row < nValue) {
			int BottomNeighbor = ((row + 1) * nValue - (nValue - col)) - 1;
			if(isOpen(row + 1, col)) {
				wuf.union(BottomNeighbor, currentIndex);
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
		int numberOfOpenSites = 0;
		for(int i = 0; i < grid.length; i++) {
			if(grid[i]) numberOfOpenSites++;
		}
		return numberOfOpenSites;
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
