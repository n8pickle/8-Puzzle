package com.n8pickle.puzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Solver {
    private Board intitial;
    private int moveAmount;
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        this.intitial = initial;
    }

    public int moves()                     // min number of moves to solve initial board
    {
        return moveAmount;
    }

    public Iterable<Board> solution()      // sequence of boards in a shortest solution
    {

    }

    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // check if puzzle is solvable; if so, solve it and output solution
        if (initial.isSolvable()) {
            Solver solver = new Solver(initial);
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

        // if not, report unsolvable
        else {
            StdOut.println("Unsolvable puzzle");
        }
    } // solve a slider puzzle (given below)

    private class SearchNode() implements Comparable<T>{
        Board b = intitial;
        int numMoves = moves();
        int priority;
        SearchNode previous;

    }
}