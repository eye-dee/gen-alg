package service

import model.MatrixPath

class FitnessFunction {

    fun evaluateFitness(matrixPath: MatrixPath) = matrixPath.points
        .windowed(2) {
            val (first, second) = it
            first.distanceTo(second)
        }
        .sum()

}
