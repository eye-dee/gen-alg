package service

import model.Matrix
import model.MatrixPath
import model.MatrixPoint
import service.ListUtils.getRandomElement
import kotlin.random.Random

class MutationGenerationService {

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
        val randomElement = getRandomElement(matrixPath.points.subList(1, matrixPath.points.size - 1))

        var newElement = randomNearestPoint(randomElement)
        var attempt = 0;
        while ((matrixPath.contains(newElement) ||
                newElement.x >= matrix.dimension ||
                newElement.x < 0 ||
                newElement.y >= matrix.dimension ||
                newElement.y < 0 ||
                matrix.get(newElement.x, newElement.y) != 1) && attempt < 10) {
            newElement = randomNearestPoint(randomElement)
            attempt++
        }
        if (!matrixPath.contains(newElement) &&
            newElement.x < matrix.dimension &&
            newElement.x >= 0 &&
            newElement.y < matrix.dimension &&
            newElement.y > 0 &&
            matrix.get(newElement.x, newElement.y) == 1) {
            val indexToRemove = matrixPath.points
                .toMutableList()
                .indexOf(randomElement)

            return MatrixPath(
                matrixPath.points.subList(0, indexToRemove)
                    .union(listOf(newElement))
                    .union(matrixPath.points.subList(indexToRemove + 1, matrixPath.points.size))
                    .toList()
            )
        }
        return null
    }

    private fun randomNearestPoint(randomElement: MatrixPoint) =
        MatrixPoint(randomElement.x - 1 + Random.nextInt(3), randomElement.y - 1 + Random.nextInt(3))
}
