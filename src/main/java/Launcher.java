import model.Matrix;
import model.MatrixPath;
import service.MatrixPathGenerator;

public class Launcher {

    public static void main(String[] args) {
        Matrix matrix = new Matrix(10);
        MatrixPathGenerator matrixPathGenerator = new MatrixPathGenerator();

        matrix.showMatrix();

        MatrixPath matrixPath = generatePathExceptionally(matrix, matrixPathGenerator);
        matrixPath.showPath();
        matrix.showMatrixWithPath(matrixPath);
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
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
        catch (RuntimeException e) {
            System.out.println("Unable to generate path:" + e.getMessage());
            if (deep < 100) {
                return generatePathExceptionally(matrix, matrixPathGenerator, deep + 1);
            } else {
                throw new RuntimeException("too much recursive calls, give up");
            }
        }
    }
}
