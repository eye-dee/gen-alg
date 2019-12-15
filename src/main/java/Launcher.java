import model.Matrix;
import model.MatrixPath;
import service.BresenhamPathCreator;
import service.GenerationService;
import service.MatrixPathGenerator;
import service.MatrixPathSimplifier;
import service.MatrixPathValidator;

public class Launcher {

    public static void main(String[] args) {
        var matrix = new Matrix(10);
        matrix.showMatrix();

        var matrixPathGenerator = new MatrixPathGenerator();
        var matrixPathSimplifier = new MatrixPathSimplifier(new BresenhamPathCreator(),
                new MatrixPathValidator());
        var generationService = new GenerationService(matrixPathGenerator, matrixPathSimplifier);
        var generation = generationService.newGeneration(matrix, 10);

        generation.stream()
                .peek(MatrixPath::showPath)
                .forEach(matrix::showMatrixWithPath);
    }
}
