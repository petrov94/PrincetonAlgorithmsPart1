package week4.homework;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solver {

    private class Node implements Comparable<Node> {

        private Board board;
        private int moves;
        private int priority;
        private Node previous;

        public Node(Board board, Node previous) {
            this.board = board;
            this.previous = previous;
            if(previous==null)moves=0;
            else this.moves = previous.moves + 1;
            this.priority = board.manhattan() + this.moves;
        }

        @Override
        public int compareTo(Node o) {
            return (this.priority - o.priority);
        }
    }

    private MinPQ<Node> boardMinPQ;
    private MinPQ<Node> twinMinPQ;
    private Node lastElement;
    private Board initial;

    public Solver(Board initial) {
        if(initial==null) throw new IllegalArgumentException();
        this.initial = initial;
        this.boardMinPQ = new MinPQ<>();
        boardMinPQ.insert(new Node(initial, null));

        this.twinMinPQ = new MinPQ<>();
        this.twinMinPQ.insert(new Node(initial.twin(),null));

        while(true) {
            lastElement = expand(boardMinPQ);
            if (lastElement != null || expand(twinMinPQ) != null) return;
        }
    }

    public boolean isSolvable() {
       return (lastElement!=null);
    }

    private Node expand(MinPQ<Node> moves) {
        if(moves.isEmpty()) return null;
        Node bestMove = moves.delMin();
        if (bestMove.board.isGoal()) return bestMove;
        for (Board neighbor : bestMove.board.neighbors()) {
            if (bestMove.previous == null || !neighbor.equals(bestMove.previous.board)) {
                moves.insert(new Node(neighbor, bestMove));
            }
        }
        return null;
    }


    public int moves() {
        return isSolvable()? lastElement.moves: -1;
    }

    public Iterable<Board> solution() {
        List<Board> solution = null;
        if(lastElement!=null){
            solution = new ArrayList<>();
            solution.add(this.initial);
        }
        while (lastElement!=null){
            solution.add(lastElement.board);
            lastElement=lastElement.previous;
        }
        return solution;
    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }

}
