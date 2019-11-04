package com.n8pickle.puzzle;

import edu.princeton.cs.algs4.Queue;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.Arrays;

public class Board {
    private int[][] blocks;
    private int N;
    private Point zeroIndex;

    public Board(int[][] blocks)
    {
        N = blocks.length;
        this.blocks = Arrays.stream(blocks).map(int[]::clone).toArray(int[][]::new);
    }
    // construct a board from an N-by-N array of blocks
    // (where blocks[i][j] = block in row i, column j)

    public int size()                      // board size N
    {
        return blocks.length;
    }

    public int hamming()                   // number of blocks out of place
    {
        int column;
        int row;
        int outOfPlace = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == 0) {
                    continue;
                }
                column = (blocks[i][j] - 1) % N;
                row = (blocks[i][j] - 1) / N;
                if((Math.abs((i - row)) +  Math.abs((j - column))) != 0)
                    outOfPlace += 1;
            }
        }
        return outOfPlace;
    }

    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
        int column;
        int row;
        int result = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (blocks[i][j] == 0) {
                    continue;
                }
                column = (blocks[i][j] -1) % N;
                row = (blocks[i][j] -1) / N;
                result += Math.abs((i - row)) +  Math.abs((j - column));
            }
        }
        return result;
    }


    public boolean isGoal()                // is this board the goal board?
    {
        return manhattan() == 0;
    }

    public boolean isSolvable()            // is this board solvable?
    {
        int inversions = 0;
        int emptySpaceRow = 0;
        int outOfPlace;
        int[] numbers = new int[N*N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                numbers[j + (i*N)] = blocks[i][j];
            }
        }
        for(int i = 0; i < N*N - 1; i++) {
            outOfPlace = numbers[i];
            for(int j = i + 1; j < N*N; j++) {
                if (numbers[j] < outOfPlace) {
                    if(numbers[j] != 0)
                        inversions++;
                    else
                        emptySpaceRow = (i - 1) / 4;
                }
            }
        }
        if (N % 2 == 1 && inversions % 2 == 0 || N % 2 == 0 && (emptySpaceRow + inversions) % 2 != 0)
            return true;
        return false;
    }

    public boolean equals(Object y)        // does this board equal y?
    {
        if(y == null)
            return false;
        if(y.getClass() != this.getClass())
            return false;
        return this.toString().equals(y.toString());
    }

    public Iterable<Board> neighbors()     // all neighboring boards
    {
        Board child;
        Board child1;
        Board child2;
        Board child3;
        Queue q = new Queue<Board>();
        switch(deliverableChildren()) {
            case 1:
                child = new Board(blocks);
                child1 = new Board(this.blocks);
                child2 = new Board(blocks);
                child.blocks[zeroIndex.y][zeroIndex.x] = child.blocks[zeroIndex.y][zeroIndex.x -1];
                child.blocks[zeroIndex.y][zeroIndex.x -1] = 0;
                child1.blocks[zeroIndex.y][zeroIndex.x] = child1.blocks[zeroIndex.y - 1][zeroIndex.x];
                child1.blocks[zeroIndex.y - 1][zeroIndex.x] = 0;
                child2.blocks[zeroIndex.y][zeroIndex.x] = child2.blocks[zeroIndex.y + 1][zeroIndex.x];
                child2.blocks[zeroIndex.y + 1][zeroIndex.x] = 0;
                q.enqueue(child);
                q.enqueue(child1);
                q.enqueue(child2);
                break;
            case 3:
                child = new Board(blocks);
                child1 = new Board(blocks);
                child3 = new Board(blocks);
                child.blocks[zeroIndex.y][zeroIndex.x] = child.blocks[zeroIndex.y][zeroIndex.x -1];
                child.blocks[zeroIndex.y][zeroIndex.x -1] = 0;
                child1.blocks[zeroIndex.y][zeroIndex.x] = child1.blocks[zeroIndex.y - 1][zeroIndex.x];
                child1.blocks[zeroIndex.y - 1][zeroIndex.x] = 0;
                child3.blocks[zeroIndex.y][zeroIndex.x] = child3.blocks[zeroIndex.y][zeroIndex.x + 1];
                child3.blocks[zeroIndex.y][zeroIndex.x + 1] = 0;
                q.enqueue(child);
                q.enqueue(child1);
                q.enqueue(child3);
                break;
            case 4:
                child = new Board(this.blocks);
                child1 = new Board(this.blocks);
                child.blocks[zeroIndex.y][zeroIndex.x] = child.blocks[zeroIndex.y][zeroIndex.x -1];
                child.blocks[zeroIndex.y][zeroIndex.x -1] = 0;
                child1.blocks[zeroIndex.y][zeroIndex.x] = child1.blocks[zeroIndex.y - 1][zeroIndex.x];
                child1.blocks[zeroIndex.y - 1][zeroIndex.x] = 0;
                q.enqueue(child);
                q.enqueue(child1);
                break;
            case 5:
                child = new Board(blocks);
                child1 = new Board(blocks);
                child2 = new Board(blocks);
                child.blocks[zeroIndex.y][zeroIndex.x] = child.blocks[zeroIndex.y][zeroIndex.x + 1];
                child.blocks[zeroIndex.y][zeroIndex.x + 1] = 0;
                child1.blocks[zeroIndex.y][zeroIndex.x] = child1.blocks[zeroIndex.y - 1][zeroIndex.x];
                child1.blocks[zeroIndex.y - 1][zeroIndex.x] = 0;
                child2.blocks[zeroIndex.y][zeroIndex.x] = child2.blocks[zeroIndex.y + 1][zeroIndex.x];
                child2.blocks[zeroIndex.y + 1][zeroIndex.x] = 0;
                q.enqueue(child);
                q.enqueue(child1);
                q.enqueue(child2);
                break;
            case 8:
                child = new Board(blocks);
                child2 = new Board(blocks);
                child.blocks[zeroIndex.y][zeroIndex.x] = child.blocks[zeroIndex.y][zeroIndex.x + 1];
                child.blocks[zeroIndex.y][zeroIndex.x + 1] = 0;
                child2.blocks[zeroIndex.y][zeroIndex.x] = child2.blocks[zeroIndex.y - 1][zeroIndex.x];
                child2.blocks[zeroIndex.y - 1][zeroIndex.x] = 0;
                q.enqueue(child);
                q.enqueue(child2);
                break;
            case 9:
                child1 = new Board(blocks);
                child2 = new Board(blocks);
                child3 = new Board(blocks);
                child1.blocks[zeroIndex.y][zeroIndex.x] = child1.blocks[zeroIndex.y + 1][zeroIndex.x];
                child1.blocks[zeroIndex.y + 1][zeroIndex.x] = 0;
                child2.blocks[zeroIndex.y][zeroIndex.x] = child2.blocks[zeroIndex.y][zeroIndex.x - 1];
                child2.blocks[zeroIndex.y][zeroIndex.x - 1] = 0;
                child3.blocks[zeroIndex.y][zeroIndex.x] = child3.blocks[zeroIndex.y][zeroIndex.x + 1];
                child3.blocks[zeroIndex.y][zeroIndex.x + 1] = 0;
                q.enqueue(child1);
                q.enqueue(child2);
                q.enqueue(child3);
                break;
            case 10:
                child = new Board(blocks);
                child2 = new Board(blocks);
                child.blocks[zeroIndex.y][zeroIndex.x] = child.blocks[zeroIndex.y][zeroIndex.x - 1];
                child.blocks[zeroIndex.y][zeroIndex.x - 1] = 0;
                child2.blocks[zeroIndex.y][zeroIndex.x] = child2.blocks[zeroIndex.y + 1][zeroIndex.x];
                child2.blocks[zeroIndex.y + 1][zeroIndex.x] = 0;
                q.enqueue(child);
                q.enqueue(child2);
                break;
            case 14:
                child2 = new Board(blocks);
                child3 = new Board(blocks);
                child2.blocks[zeroIndex.y][zeroIndex.x] = child2.blocks[zeroIndex.y + 1][zeroIndex.x];
                child2.blocks[zeroIndex.y + 1][zeroIndex.x] = 0;
                child3.blocks[zeroIndex.y][zeroIndex.x] = child3.blocks[zeroIndex.y][zeroIndex.x + 1];
                child3.blocks[zeroIndex.y][zeroIndex.x + 1] = 0;
                q.enqueue(child2);
                q.enqueue(child3);
                break;
            default:
                child = new Board(blocks);
                child1 = new Board(blocks);
                child2 = new Board(blocks);
                child3 = new Board(blocks);
                child.blocks[zeroIndex.y][zeroIndex.x] = child.blocks[zeroIndex.y][zeroIndex.x -1];
                child.blocks[zeroIndex.y][zeroIndex.x -1] = 0;
                child1.blocks[zeroIndex.y][zeroIndex.x] = child1.blocks[zeroIndex.y - 1][zeroIndex.x];
                child1.blocks[zeroIndex.y - 1][zeroIndex.x] = 0;
                child2.blocks[zeroIndex.y][zeroIndex.x] = child2.blocks[zeroIndex.y + 1][zeroIndex.x];
                child2.blocks[zeroIndex.y + 1][zeroIndex.x] = 0;
                child3.blocks[zeroIndex.y][zeroIndex.x] = child3.blocks[zeroIndex.y][zeroIndex.x + 1];
                child3.blocks[zeroIndex.y][zeroIndex.x + 1] = 0;
                q.enqueue(child);
                q.enqueue(child1);
                q.enqueue(child2);
                q.enqueue(child3);
                break;
        }
        return q;
    }

    private int deliverableChildren() {
        int moveTypes = 0;
        zeroIndex = new Point(0, -1);
        // find index of zero
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(blocks[i][j] == 0) {
                    zeroIndex.x = j;
                    zeroIndex.y = i;
                }
            }
            if(zeroIndex.y != -1)
                break;
        }
        // find restrictions of finding deliverable children
        if(zeroIndex.y == (N - 1)) {
            moveTypes += 3;
        } else if (zeroIndex.y == 0) {
            moveTypes += 9;
        }
        if(zeroIndex.x == (N - 1)) {
            moveTypes += 1;
        } else if (zeroIndex.x == 0) {
            moveTypes += 5;
        }
        return moveTypes;
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
        int[][] iab0 = {{1, 0}, {3, 2}};
        Board b0 = new Board(iab0);
        int[][] iab = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 0}};
        Board b1 = new Board(iab);
        int[][] iab2 = {{8, 1, 3}, {4, 0, 2}, {7, 6, 5}};
        Board b2 = new Board(iab2);

        //Check if board is solvable
        System.out.println("Check for solvability: ");
        System.out.println(b1.isSolvable());
        System.out.println(b2.isSolvable());

        System.out.println("Check for Manhatton: ");
        System.out.println( b0.manhattan());
        System.out.println( b1.manhattan());
        System.out.println( b2.manhattan());

        System.out.println("Check for Hamming: ");
        System.out.println(b2.hamming());

        System.out.println("Check for isGoal: ");
        System.out.println( b1.isGoal());
        System.out.println( b2.isGoal());

        System.out.println("Check all Children Delivered: ");
        for(Board printBoard: b1.neighbors()) {
            System.out.println(printBoard);
        }
        for(Board printBoard: b2.neighbors()) {
            System.out.println(printBoard);
        }
    }

}

