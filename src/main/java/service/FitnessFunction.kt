package service

import model.MatrixPath

class FitnessFunction {

    fun evaluateFitness(matrixPath: MatrixPath) = matrixPath.points
        .zipWithNext { first, second -> first.distanceTo(second) }
        .sum()

}
