package week4.homework;

import edu.princeton.cs.algs4.MinPQ;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Board {

    private int[][] tiles;
    private int size;


    public Board(int[][] tiles) {
        this.size = tiles.length;
        this.tiles = new int[dimension()][dimension()];
        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++) {
                this.tiles[row][col] = tiles[row][col];
            }
        }
    }

    // string representation of this board
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(this.size);
        builder.append("\n");
        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++) {
                if (col == 0 && row != 0) {
                    builder.append("\n");
                }
                builder.append(" " + tiles[row][col]);
            }
        }
        return builder.toString();
    }

    // board dimension n
    public int dimension() {
        return this.size;
    }

    // number of tiles out of place
    public int hamming() {
        int hamming = 0;
        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++) {
                if (tiles[row][col] != 0 && matrixtoN(row, col) != tiles[row][col]) {
                    hamming++;
                }
            }
        }
        return hamming;
    }

    private int matrixtoN(int row, int column) {
        return row * size + column + 1;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattanSum = 0;
        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++) {
                if (tiles[row][col] != 0 && matrixtoN(row, col) != tiles[row][col]) {
                    manhattanSum += sum(tiles[row][col], row, col);
                }
            }
        }
        return manhattanSum;
    }

    private int sum(int element, int row, int col) {
        return Math.abs(row - getRow(element)) + Math.abs(col - getColumn(element));
    }

    private int getRow(int element) {
        return (element - 1) / dimension();
    }

    private int getColumn(int element) {
        return (element - 1) % dimension();
    }

    // is this board the goal board?
    public boolean isGoal() {
        return hamming() == 0;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) {
            return false;
        }
        if (!(y instanceof Board) || ((Board) y).dimension() != this.dimension()) {
            return false;
        }
        Board second = (Board) y;
        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++) {
                if (this.tiles[row][col] != second.tiles[row][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[] findSpaceLocation() {
        int[] coordinates = new int[2];
        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension(); col++) {
                if (this.tiles[row][col] == 0) {
                    coordinates[0] = row;
                    coordinates[1] = col;
                }
            }
        }
        return coordinates;
    }

    private int[][] swap(int row1, int col1, int row2, int col2) {
        int[][] copy = copyArray(tiles);
        int tmp = copy[row1][col1];
        copy[row1][col1] = copy[row2][col2];
        copy[row2][col2] = tmp;

        return copy;
    }


    private int[][] copyArray(int[][] blocks) {
        int[][] copy = new int[blocks.length][blocks.length];
        for (int row = 0; row < blocks.length; row++) {
            for (int col = 0; col < blocks.length; col++) {
                copy[row][col] = blocks[row][col];
            }
        }

        return copy;
    }

    public Iterable<Board> neighbors() {
        LinkedList<Board> neighbors = new LinkedList<Board>();

        int[] location = findSpaceLocation();
        int spaceRow = location[0];
        int spaceCol = location[1];

        if (spaceRow > 0) {
            neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow - 1, spaceCol)));
        }
        if (spaceRow < dimension() - 1) {
            neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow + 1, spaceCol)));
        }
        if (spaceCol > 0) {
            neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow, spaceCol - 1)));
        }
        if (spaceCol < dimension() - 1) {
            neighbors.add(new Board(swap(spaceRow, spaceCol, spaceRow, spaceCol + 1)));
        }

        return neighbors;
    }

    public Board twin() {
        for (int row = 0; row < dimension(); row++) {
            for (int col = 0; col < dimension() - 1; col++) {
                if (tiles[row][col] != 0 && tiles[row][col + 1] != 0) {
                    return new Board(swap(row, col, row, col + 1));
                }
            }
        }
        throw new RuntimeException();
    }

    // unit testing (not graded)
    public static void main(String[] args) {

    }
}
