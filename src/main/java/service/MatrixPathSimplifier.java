package service;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import model.Matrix;
import model.MatrixPath;
import model.MatrixPoint;

@AllArgsConstructor
public class MatrixPathSimplifier {

    private final BresenhamPathCreator bresenhamPathCreator;
    private final MatrixPathValidator matrixPathValidator;

    public MatrixPath simplify(Matrix matrix, MatrixPath matrixPath) {
        List<MatrixPoint> newPath = new ArrayList<>();
        newPath.add(matrixPath.getPoints().get(0));

        while (newPath.get(newPath.size() - 1) != matrixPath.getPoints().get(matrixPath.getPoints().size() - 1)) {
            MatrixPoint currentPoint = newPath.get(newPath.size() - 1);
            for (int i = matrixPath.getPoints().size() - 1; i > 0 ; i--) {

                MatrixPath bresenhamPath = bresenhamPathCreator.createPath(currentPoint, matrixPath.getPoints().get(i));
                if (matrixPathValidator.validateMatrixPath(matrix, bresenhamPath)) {
                    newPath.add(matrixPath.getPoints().get(i));
                    break;
                }
            }
        }

        return new MatrixPath(newPath);
    }

}
