package service;

import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import model.Matrix;
import model.MatrixPath;

@AllArgsConstructor
public class GenerationService {

    private static final int TRESHOLD = 1_000_000;

    private final MatrixPathGenerator matrixPathGenerator;
    private final MatrixPathSimplifier matrixPathSimplifier;

    public Set<MatrixPath> newGeneration(Matrix matrix, int size) {
        Set<MatrixPath> res = new HashSet<>();

        int failureCounter = 0;
        while (res.size() < size) {
            var matrixPath = matrixPathGenerator.generatePath(matrix);
            MatrixPath simplifiedPath = matrixPathSimplifier.simplify(matrix, matrixPath);
            boolean isNewValue = res.add(simplifiedPath);
            if (!isNewValue) {
                ++failureCounter;
            }
            if (failureCounter > TRESHOLD) {
                System.out.println("Matrix is too pidorskaya, can't build required amount of unique path");
                return res;
            }
        }

        return res;
    }
}
