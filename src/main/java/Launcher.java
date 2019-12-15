import model.Matrix;
import model.MatrixPath;
import model.MatrixPoint;
import service.BresenhamPathCreator;
import service.MatrixPathGenerator;
import service.MatrixPathSimplifier;
import service.MatrixPathValidator;

public class Launcher {

    public static void main(String[] args) {
        var matrix = new Matrix(10);
        var matrixPathGenerator = new MatrixPathGenerator();

        matrix.showMatrix();

        var matrixPath = generatePathExceptionally(matrix, matrixPathGenerator);
        matrixPath.showPath();
        matrix.showMatrixWithPath(matrixPath);

        var matrixPathSimplifier = new MatrixPathSimplifier(new BresenhamPathCreator(),
                new MatrixPathValidator());

        var simplifiedPath = matrixPathSimplifier.simplify(matrix, matrixPath);
        simplifiedPath.showPath();
        matrix.showMatrixWithPath(simplifiedPath);
    }

    private static MatrixPath generatePathExceptionally(Matrix matrix, MatrixPathGenerator matrixPathGenerator) {
        return generatePathExceptionally(matrix, matrixPathGenerator, 0);
    }

    private static MatrixPath generatePathExceptionally(
            Matrix matrix,
            MatrixPathGenerator matrixPathGenerator,
            int deep
    ) {
        try {
            return matrixPathGenerator.generateNewPath(matrix);
        }
        catch (IllegalArgumentException e) {
            System.out.println("Unable to generate path:" + e.getMessage());
            if (deep < 100) {
                return generatePathExceptionally(matrix, matrixPathGenerator, deep + 1);
            } else {
                throw new RuntimeException("too much recursive calls, give up");
            }
        }
    }
}
