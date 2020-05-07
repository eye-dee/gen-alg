package service

import model.Matrix
import model.MatrixPath
import model.MatrixPoint
import kotlin.random.Random

class MutationGenerationService {

    private val matrixPathValidator = MatrixPathValidator()
    private val bresenhamPathCreator = BresenhamPathCreator()

    fun mutate(paths: Set<MatrixPath>, matrix: Matrix): Set<MatrixPath> {
        val res: MutableSet<MatrixPath> = HashSet()
        res.addAll(paths)

        res.addAll(paths.mapNotNull { takeRandomAndMove(it, matrix) })

        return res
    }

    private fun takeRandomAndMove(matrixPath: MatrixPath, matrix: Matrix): MatrixPath? {
        if (matrixPath.points.size < 3) {
            return null
        }
        val randomElementIndex = Random.nextInt(1, matrixPath.points.size - 2)
        val randomElement = matrixPath.points[randomElementIndex]

        var newElement = randomNearestPoint(randomElement)
        var attempt = 0;

        while (
            (newElement.x >= matrix.dimension
                || newElement.x < 0
                || newElement.y >= matrix.dimension
                || newElement.y < 0
                || matrix.get(newElement.x, newElement.y) != 1
                || newElement.x == matrix.end.x
                || newElement.y == matrix.end.y
                || !matrixPathValidator.validateMatrixPath(
                matrix,
                bresenhamPathCreator.createPath(matrixPath.points[randomElementIndex - 1], newElement)
            )
                || !matrixPathValidator.validateMatrixPath(
                matrix,
                bresenhamPathCreator.createPath(newElement, matrixPath.points[randomElementIndex + 1])
            ))
            && attempt < 10
        ) {
            newElement = randomNearestPoint(randomElement)
            attempt++
        }
        if (attempt < 10) {
            val indexToRemove = matrixPath.points
                .toMutableList()
                .indexOf(randomElement)

            val points = matrixPath.points.subList(0, indexToRemove)
                .union(listOf(newElement))
                .union(matrixPath.points.subList(indexToRemove + 1, matrixPath.points.size))
                .toList()

            if (points[points.size - 1].x != matrix.dimension - 1 || points[points.size - 1].y != matrix.dimension - 1) {
                println("asdasd")
            }

            return MatrixPath(
                points
            )
        }
        return null
    }

    private fun randomNearestPoint(randomElement: MatrixPoint) =
        MatrixPoint(randomElement.x - 1 + Random.nextInt(3), randomElement.y - 1 + Random.nextInt(3))
}
