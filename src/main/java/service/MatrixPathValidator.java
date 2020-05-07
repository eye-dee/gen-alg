package service;

import model.Matrix;
import model.MatrixPath;

public class MatrixPathValidator {

    public boolean validateMatrixPath(Matrix matrix, MatrixPath matrixPath) {
        return matrixPath.getPoints()
                .stream()
                .allMatch(point -> matrix.get(point.getX(), point.getY()) == 1);
    }
}
