package model

import java.util.HashSet
import java.util.stream.Collectors

data class MatrixPath(val points: List<MatrixPoint>) {
    private val pointSet: Set<MatrixPoint>

    init {
        pointSet = HashSet(points)
    }

    fun showPath() {
        val stringPath = points.stream()
            .map { (x, y) -> "($x, $y)" }
            .collect(Collectors.joining(","))
        println(stringPath)
    }

    operator fun contains(matrixPoint: MatrixPoint): Boolean {
        return pointSet.contains(matrixPoint)
    }
}
