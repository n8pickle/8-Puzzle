package com.n8pickle.puzzle;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Board {
    private int[][] blocks;
    private int N;
    private final Board finished;

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
        for(int i = 0; i < N; i++) {
            
        }
    }

    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {

    }

    public boolean isGoal()                // is this board the goal board?
    {
        return this.equals(finished);
    }

    public boolean isSolvable()            // is this board solvable?
    {

    }

    public boolean equals(Object y)        // does this board equal y?
    {
        return this.toString().equals(y.toString());
    }

    public Iterable<Board> neighbors()     // all neighboring boards
    {
        return new MyIterator<E>();
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
                solution[j][i] = i+j+1;
            }
        }
        return solution;
    }

    private class MyIterator<E> implements Iterator<E>
    {
        RandomizedQueue.Node curr = head;

        public E Remove()
        {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return curr.getNext() != tail;
        }

        @Override
        public E next() {
            if(!hasNext())
                throw new NoSuchElementException();
            curr = curr.getNext();
            return (E) curr.getE();
        }
    }

}

