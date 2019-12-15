package service

import model.MatrixPath
import model.MatrixPoint
import service.ListUtils.getRandomElement

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
        if (first.points.size < 4) {
            return emptyList()
        }

        val firstOrNull =
            first.points
                .subList(2, first.points.size - 2)
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
