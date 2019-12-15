package model

import kotlin.math.sqrt

data class MatrixPoint(val x: Int, val y: Int) {
    fun distanceTo(matrixPoint: MatrixPoint) = sqrt(
        ((this.x - matrixPoint.x) * (this.x - matrixPoint.x)).toDouble() +
            ((this.y - matrixPoint.y) * (this.y - matrixPoint.y)).toDouble()
    )
}
