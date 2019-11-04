package com.n8pickle.puzzle;

import edu.princeton.cs.algs4.*;

public class Solver {
    private Board intitial;
    private int moveAmount = 0;
    Iterable<Board> solution;

    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        intitial = initial;
        solution = solution();

    }

    public int moves()                     // min number of moves to solve initial board
    {
        return moveAmount;
    }

    public Iterable<Board> solution()      // sequence of boards in a shortest solution
    {
        Stack<SearchNode> finalResult = new Stack<>();
        MinPQ<SearchNode> priorityQueue = new MinPQ<>();
        Stack<Board> returnable = new Stack<>();
        finalResult.push(new SearchNode(intitial, moves(), null));
        while (!finalResult.peek().initial.isGoal()) {
            for (Board neighbor : finalResult.peek().initial.neighbors()) {
                priorityQueue.insert(new SearchNode(neighbor, moves(), finalResult.peek()));
            }
            finalResult.push(priorityQueue.min());
            moveAmount++;
            priorityQueue = new MinPQ<>();
        }
        for (SearchNode pathToGoal : finalResult) {
            returnable.push(pathToGoal.initial);
        }
        return returnable;
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
    private class SearchNode implements Comparable<SearchNode>{
        Board initial;
        int numMoves;
        int priority;
        SearchNode previous;

        public SearchNode(Board initial, int numMoves, SearchNode previous) {
            this.numMoves = numMoves;
            this.initial = initial;
            this.previous = previous;
            priority = initial.manhattan() + numMoves;
        }

        @Override
        public int compareTo(SearchNode newSearchNode) {
            if(this.initial.manhattan() + this.numMoves == newSearchNode.initial.manhattan() + newSearchNode.numMoves)
                return 0;
            if(this.initial.manhattan() + this.numMoves >= newSearchNode.initial.manhattan() + newSearchNode.numMoves)
                return 1;
            else
                return -1;
        }
    }
}

