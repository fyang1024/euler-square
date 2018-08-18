package io.threeblox.eulersquare;

public class EulerSquareSolver {

    private static int[] xMove = {2, 1, -1, -2, -2, -1, 1, 2};
    private static int[] yMove = {1, 2, 2, 1, -1, -2, -2, -1};

    private int width, length;
    private int[][] solution;

    public EulerSquareSolver(int width, int length) {
        this.width = width;
        this.length = length;
        solution = new int[length][width];

    }

    private boolean isValid(int x, int y) {
        return x >=0 && x < length && y >= 0 && y < width && solution[x][y] == 0;
    }

    private void reset() {
        for (int i = 0; i < length; i++)
            for (int j = 0; j < width; j++)
                solution[i][j] = 0;
    }

    private void solve() {
        for (int i = 0; i < length / 2 + length % 2; i++) {
            for (int j = 0; j < width / 2 + width % 2; j++) {
                reset();
                solution[i][j] = 1;
                if(!solve(i, j)) {
                    System.out.println(String.format("No solution exists when starting from (%d, %d)", i+1, j+1));
                } else {
                    printSolution();
                }
            }
        }
    }

    private boolean solve(int x, int y) {
        if (solution[x][y] == width * length) {
            return true;
        }
        for (int i = 0; i < xMove.length; i++) {
            int nextX = x + xMove[i];
            int nextY = y + yMove[i];
            if (isValid(nextX, nextY)) {
                solution[nextX][nextY] = solution[x][y] + 1;
                if (solve(nextX, nextY)) {
                    return true;
                } else {
                    solution[nextX][nextY] = 0;
                }
            }
        }
        return false;
    }

    private void printSolution() {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print("-----");
            }
            System.out.println("-");
            for (int j = 0; j < width; j++) {
                System.out.print(solution[i][j] >= 10 ?
                        String.format("| %d ", solution[i][j])
                        : String.format("| %d  ", solution[i][j]));
            }
            System.out.println("|");
        }
        for (int j = 0; j < width; j++) {
            System.out.print("-----");
        }
        System.out.println("-");
        System.out.println();
    }

    public static void main(String[] args) {
        new EulerSquareSolver(10, 10).solve();
    }
}
