package model;

import java.util.Random;
import org.jetbrains.annotations.NotNull;

public class Matrix {

    private final Random random = new Random();

    private final int[][] arr;

    private final MatrixPoint start;

    private final MatrixPoint end;

    private final int valueAtTheEnd;

    private final int dimension;

    public Matrix(int dimension) {
        arr = new int[][]
                {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                        {0, 0, 0, 0, 0, 2, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 0, 1, 1, 1},
                        {1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 9, 4, 3}};

        arr[0][0] = 1;
        this.dimension = arr.length;
        this.valueAtTheEnd = 9;
        start = new MatrixPoint(0, 0);
        end = findEnd(9);
    }

    @NotNull
    private MatrixPoint findEnd(int value) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == value) {
                    return new MatrixPoint(i, j);
                }
            }
        }
        throw new RuntimeException("Impossible to find end");
    }

    public int get(int x, int y) {
        return arr[x][y];
    }

    public void showMatrix() {
        System.out.print("   ");
        for (int i = 0; i < dimension; i++) {
            System.out.print(String.format(" %s", intToString(i)));
        }
        System.out.println();
        for (int i = 0; i < dimension; i++) {
            System.out.print(String.format("%s: ", intToString(i)));
            for (int j : arr[i]) {
                System.out.print(String.format("%s ", intToString(j)));
            }
            System.out.println();
        }
    }

    public void showMatrixWithPath(MatrixPath matrixPath) {
        System.out.print("   ");
        for (int i = 0; i < dimension; i++) {
            System.out.print(String.format(" %s", intToString(i)));
        }
        System.out.println();
        for (int i = 0; i < dimension; i++) {
            System.out.print(String.format("%s: ", intToString(i)));
            for (int j = 0; j < dimension; j++) {
                if (matrixPath.contains(new MatrixPoint(i, j))) {
                    System.out.print(String.format("\033[31m%s \033[0m", intToString(arr[i][j])));
                } else {
                    System.out.print(String.format("%s ", intToString(arr[i][j])));
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getDimension() {
        return dimension;
    }

    private int generateAvailabilityWithPercent(int percent) {
        int randomValue = random.nextInt(100);
        if (randomValue > percent) {
            return 0;
        } else {
            return 1;
        }
    }

    public MatrixPoint getStart() {
        return start;
    }

    public MatrixPoint getEnd() {
        return end;
    }

    private String intToString(int n) {
        if (n < 10) {
            return " " + n;
        } else {
            return "" + n;
        }
    }
}
