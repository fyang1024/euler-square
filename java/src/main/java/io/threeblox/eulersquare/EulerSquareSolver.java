package io.threeblox.eulersquare;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;

public class EulerSquareSolver {

    class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Position position = (Position) o;
            return x == position.x &&
                    y == position.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    void solve() {
        Stack<Position> solutionStack = new Stack<>();
        boolean[][] board = new boolean[8][8];
        for (int i = 0; i < 4; i++) {
            for (int j = i; j < 4; j++) {
                Position position = new Position(i, j);
                solutionStack.push(position);
                board[i][j] = true;
                search(solutionStack, board);
            }
        }
    }

    void search(Stack<Position> solutionStack, boolean[][] board) {
        if (solutionStack.size() == board.length * board[0].length) {
            // found solution, print solution
            System.out.println("found solution");
        } else {
            Set<Position> nextPosistions = getNextPosistions(solutionStack.peek(), board);
            if (nextPosistions.isEmpty()) {
                solutionStack.pop();
                if (solutionStack.isEmpty()) {
                   System.out.println("dead path");
                }
            } else {
                for(Position nextPosition : nextPosistions) {
                    solutionStack.push(nextPosition);
                    board[nextPosition.x][nextPosition.y] = true;
                    search(solutionStack, board);
                }
            }

        }
    }

    Set<Position> getNextPosistions(Position currentPosition, boolean[][] board) {
        Set<Position> nextPositions = new HashSet<>();
        Position newPosition = new Position(currentPosition.x + 1, currentPosition.y + 2);
        if (isValid(newPosition, board)) {
            nextPositions.add(newPosition);
        }
        newPosition = new Position(currentPosition.x + 1, currentPosition.y - 2);
        if (isValid(newPosition, board)) {
            nextPositions.add(newPosition);
        }
        newPosition = new Position(currentPosition.x + 2, currentPosition.y + 1);
        if (isValid(newPosition, board)) {
            nextPositions.add(newPosition);
        }
        newPosition = new Position(currentPosition.x + 2, currentPosition.y - 1);
        if (isValid(newPosition, board)) {
            nextPositions.add(newPosition);
        }
        newPosition = new Position(currentPosition.x - 1, currentPosition.y + 2);
        if (isValid(newPosition, board)) {
            nextPositions.add(newPosition);
        }
        newPosition = new Position(currentPosition.x - 1, currentPosition.y - 2);
        if (isValid(newPosition, board)) {
            nextPositions.add(newPosition);
        }
        newPosition = new Position(currentPosition.x - 2, currentPosition.y + 1);
        if (isValid(newPosition, board)) {
            nextPositions.add(newPosition);
        }
        newPosition = new Position(currentPosition.x - 2, currentPosition.y - 1);
        if (isValid(newPosition, board)) {
            nextPositions.add(newPosition);
        }
        return nextPositions;
    }

    boolean isValid(Position position, boolean[][] board) {
        return position.x >= 0 && position.x < board.length
                && position.y >= 0 && position.y < board[0].length
                && !board[position.x][position.y];
    }

    public static void main(String[] args) {
        new EulerSquareSolver().solve();
    }


}
