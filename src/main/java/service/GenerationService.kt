package service

import model.Matrix
import model.MatrixPath
import java.util.HashSet

class GenerationService(
    private val matrixPathGenerator: MatrixPathGenerator,
    private val matrixPathSimplifier: MatrixPathSimplifier
) {

    fun newGeneration(matrix: Matrix?, size: Int): Set<MatrixPath> {
        val res: MutableSet<MatrixPath> = HashSet()
        var failureCounter = 0
        while (res.size < size) {
            val matrixPath = matrixPathGenerator.generatePath(matrix)
            val simplifiedPath = matrixPathSimplifier.simplify(matrix, matrixPath)
            val isNewValue = res.add(matrixPath)
            if (!isNewValue) {
                ++failureCounter
            }
            if (failureCounter > TRESHOLD) {
                println("Matrix is too pidorskaya, can't build required amount of unique path")
                return res
            }
        }
        return res
    }

    companion object {
        private const val TRESHOLD = 1000000
    }
}
