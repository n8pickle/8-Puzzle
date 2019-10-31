package com.n8pickle.puzzle;

import edu.princeton.cs.algs4.Stack;

public class Board {
    private int[][] blocks;
    private int N;
    private Board finished;

    public Board(int[][] blocks)
    {
        this.blocks = blocks;
        N = blocks[0].length;
        if(!isGoal())
            finished = new Board(createSolution());
        else
            finished = null;
    }
    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)

    public int size()                      // board size N
    {
        return blocks.length;
    }

    public int hamming()                   // number of blocks out of place
    {
        int outOfPlace = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; i++) {
                if(blocks[i][j] != finished.blocks[i][j])
                    outOfPlace += 1;
            }
        }
        return outOfPlace;
    }

    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        int distanceAway;
        int column;
        int row;
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                column = blocks[i][j] % 3;
                row = (blocks[i][j] -1) % 3;
                result += Math.abs((i - row)) +  Math.abs((j - column));
                // % 3 gets column
                //num - 1 / 3 gets row
                }
            }
            return result;
        }


    public boolean isGoal()                // is this board the goal board?
    {
        finished = new Board(createSolution());
        return this.equals(finished);
    }

    public boolean isSolvable()            // is this board solvable?
    {
        int outOfPlace;
        int inversions = 0;
        int emptySpaceRow = 0;
        int[] numbers = new int[blocks.length];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                numbers[j + (i*N)] = blocks[i][j];
            }
        }
        for(int k = 0; k < numbers.length - 1; k++) {
            if(numbers[k] - 1 != k) {
                outOfPlace = numbers[k];
                for (int l = k + 1; l < numbers.length - 1; l++) {
                    if (numbers[l] < outOfPlace && numbers[l] != 0)
                        inversions++;
                    else if (numbers[l] == 0)
                        emptySpaceRow = l/N;
                }
            }
        }
        if (N % 2 == 1 && inversions % 2 == 0 || N % 2 == 0 && (emptySpaceRow + inversions) % 2 != 0)
            return true;
        return false;
    }

    public boolean equals(Object y)        // does this board equal y?
    {
        return this.toString().equals(y.toString());
    }

    public Iterable<Board> neighbors()     // all neighboring boards
    {
        Stack s = new Stack();
        ...
        return s;
    }

    public String toString()               // string representation of this board (in the output format specified below)
    {
        StringBuilder s = new StringBuilder();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
    }

    public static void main(String[] args) // unit tests (not graded)
    {

    }

    private int[][] createSolution() {
        int[][] solution = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                solution[i][j] = i+j+1;
            }
        }
        return solution;
    }

}

