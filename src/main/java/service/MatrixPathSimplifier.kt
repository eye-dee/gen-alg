package service

import model.Matrix
import model.MatrixPath
import model.MatrixPoint
import java.util.ArrayList

class MatrixPathSimplifier(
    private val bresenhamPathCreator: BresenhamPathCreator,
    private val matrixPathValidator: MatrixPathValidator
) {

    fun simplify(matrix: Matrix?, matrixPath: MatrixPath): MatrixPath {
        val newPath: MutableList<MatrixPoint> = ArrayList()
        newPath.add(matrixPath.points[0])
        while (newPath[newPath.size - 1] !== matrixPath.points[matrixPath.points.size - 1]) {
            val currentPoint = newPath[newPath.size - 1]
            for (i in matrixPath.points.size - 1 downTo 1) {
                val bresenhamPath = bresenhamPathCreator.createPath(currentPoint, matrixPath.points[i])
                if (matrixPathValidator.validateMatrixPath(matrix, bresenhamPath)) {
                    newPath.add(matrixPath.points[i])
                    break
                }
            }
        }
        return MatrixPath(newPath)
    }
}
