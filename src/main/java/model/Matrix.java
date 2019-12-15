package model;

import java.util.Random;
import java.util.stream.IntStream;

public class Matrix {

    private final Random random = new Random();

    private final int[][] arr;

    private final int dimension;

    public Matrix(int dimension) {
        arr = IntStream.range(0, dimension)
                .mapToObj(i -> IntStream.generate(() -> generateAvailabilityWithPercent(80))
                        .limit(dimension)
                        .toArray())
                .toArray((i) -> new int[dimension][dimension]);
        arr[0][0] = 1;
        arr[dimension - 1][dimension - 1] = 1;
        int disableRandomValueOnDiag = 1 + random.nextInt(dimension - 2);
        arr[disableRandomValueOnDiag][disableRandomValueOnDiag] = 0;
        this.dimension = dimension;
    }

    public int get(int x, int y) {
        return arr[x][y];
    }

    public void showMatrix() {
        System.out.print("  ");
        for (int i = 0; i < dimension; i++) {
            System.out.print(String.format(" %d", i));
        }
        System.out.println();
        for (int i = 0; i < dimension; i++) {
            System.out.print(String.format("%d: ", i));
            for (int j : arr[i]) {
                System.out.print(String.format("%d ", j));
            }
            System.out.println();
        }
    }

    public void showMatrixWithPath(MatrixPath matrixPath) {
        System.out.print("  ");
        for (int i = 0; i < dimension; i++) {
            System.out.print(String.format(" %d", i));
        }
        System.out.println();
        for (int i = 0; i < dimension; i++) {
            System.out.print(String.format("%d: ", i));
            for (int j = 0; j < dimension; j++) {
                if (matrixPath.contains(new MatrixPoint(i, j))) {
                    System.out.print(String.format("\033[31m%d \033[0m", arr[i][j]));
                } else {
                    System.out.print(String.format("%d ", arr[i][j]));
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
}
