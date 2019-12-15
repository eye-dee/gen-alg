package service

import model.MatrixPath
import model.MatrixPoint
import service.ListUtils.getRandomElement
import service.ListUtils.last

class CrossingGenerationService {

    fun cross(paths: Set<MatrixPath>): Set<MatrixPath> {
        val res: MutableSet<MatrixPath> = HashSet()

        for (path in paths) {
            res.addAll(paths.filter { it != path }
                .flatMap { crossPaths(path, it) })
        }
        res.addAll(paths)
        return res
    }

    private fun crossPaths(first: MatrixPath, second: MatrixPath): List<MatrixPath> {
        val firstAndLastPoint = listOf(first.points[0], last(first.points))

        val firstOrNull =
            first.points
                .subtract(firstAndLastPoint)
                .toList()
                .subList(1, first.points.size - 2)
                .intersect(second.points)
                .takeIf { it.isNotEmpty() }
                ?.let { getRandomElement(it.toList()) }

        if (firstOrNull != null) {

            val leftCross: MutableList<MatrixPoint> = ArrayList()
            val rightCross: MutableList<MatrixPoint> = ArrayList()

            val leftIndexOf = first.points.indexOf(firstOrNull)
            val rightIndexOf = second.points.indexOf(firstOrNull)

            leftCross.addAll(first.points.subList(0, leftIndexOf))
            leftCross.addAll(second.points.subList(rightIndexOf, second.points.size))
            rightCross.addAll(second.points.subList(0, rightIndexOf))
            rightCross.addAll(first.points.subList(leftIndexOf, first.points.size))
            return listOf(MatrixPath(leftCross), MatrixPath(rightCross))
        }

        return emptyList()
    }
}
