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

        var curIndex = 1
        while (newPath[newPath.size - 1] !== matrixPath.points[matrixPath.points.size - 1]) {
            val currentPoint = newPath[newPath.size - 1]
            var nextPoint: MatrixPoint? = null

            while (curIndex < matrixPath.points.size) {
                val bresenhamPath = bresenhamPathCreator.createPath(currentPoint, matrixPath.points[curIndex])
                if (matrixPathValidator.validateMatrixPath(matrix, bresenhamPath)) {
                    nextPoint = matrixPath.points[curIndex]
                    curIndex++
                } else {
                    break
                }
            }
            if (nextPoint != null) {
                newPath.add(nextPoint)
            } else {
                newPath.add(matrixPath.points[curIndex - 1])
            }
        }
        return MatrixPath(newPath)
    }
}
