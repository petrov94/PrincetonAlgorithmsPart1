package week1.homework;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private boolean[] matrix;
    private final WeightedQuickUnionUF percolation;
    private final int size;
    private int openSiteNumber = 0;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("Invalid size value.");
        size = n;
        matrix = new boolean[(n * n) + 2];
        for (int i = 0; i < (n * n) + 2; i++) {
            matrix[i] = false;
        }
        percolation = new WeightedQuickUnionUF((n * n) + 2);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) {
            return;
        }
        int index = migrateFromGridToUF(row, col);
        matrix[index] = true;
        openSiteNumber++;
        if (row == 1) {
            percolation.union(index, (this.size * this.size));
        }
        if (row == this.size) {
            percolation.union(index, (this.size * this.size) + 1);
        }
        if (col != 1 && isOpen(row, col - 1))
            percolation.union(index, migrateFromGridToUF(row, col - 1));
        if (col != size && isOpen(row, col + 1))
            percolation.union(index, migrateFromGridToUF(row, col + 1));
        if (row != 1 && isOpen(row - 1, col))
            percolation.union(index, migrateFromGridToUF(row - 1, col));
        if (row != size && isOpen(row + 1, col))
            percolation.union(index, migrateFromGridToUF(row + 1, col));
    }

    private int migrateFromGridToUF(int row, int col) {
        return (row - 1) * size + col - 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return matrix[migrateFromGridToUF(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            return false;
        }
        return percolation.connected(migrateFromGridToUF(row, col), this.size * this.size);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openSiteNumber;
    }

    // does the system percolate?
    public boolean percolates() {
        return percolation.connected(this.size * this.size + 1, this.size * this.size);
    }

    private void validate(int i, int j) {
        if (i < 1 || i > size || j < 1 || j > size) {
            throw new IllegalArgumentException("Give index is out of the scope.");
        }
    }

    // test client (optional)
    public static void main(String[] args) {
        Percolation obj = new Percolation(5);
        System.out.println(obj.isFull(1, 1));
        System.out.println(obj.numberOfOpenSites());
        System.out.println(obj.isFull(1, 2) + " " + obj.percolates() + "    " + obj.isOpen(1, 2));
        obj.open(1, 2);
        obj.open(2, 1);
        System.out.println(obj.numberOfOpenSites());
        System.out.println(obj.isFull(1, 2));
        obj.open(3, 2);
        System.out.println(obj.numberOfOpenSites());
        System.out.println(obj.isFull(3, 2));
        obj.open(3, 1);
        System.out.println(obj.numberOfOpenSites());
        System.out.println(obj.isFull(3, 1));
        obj.open(4, 1);
        System.out.println(obj.numberOfOpenSites());
        System.out.println(obj.isFull(4, 1));
        obj.open(5, 1);
        System.out.println(obj.numberOfOpenSites());
        System.out.println(obj.isFull(5, 1));
        obj.open(1, 1);
        System.out.println(obj.numberOfOpenSites());
        System.out.println(obj.isFull(1, 1));
        System.out.println(obj.isFull(1, 2) + " " + obj.numberOfOpenSites() + "  " + obj.percolates());
    }
}

