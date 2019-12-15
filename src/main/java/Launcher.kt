import model.Matrix
import model.MatrixPath
import service.BresenhamPathCreator
import service.CrossingGenerationService
import service.FitnessFunction
import service.GenerationService
import service.MatrixPathGenerator
import service.MatrixPathSimplifier
import service.MatrixPathValidator
import service.RouletteSelection

object Launcher {
}

fun main() {
    val matrix = Matrix(20)
    matrix.showMatrix()
    val matrixPathGenerator = MatrixPathGenerator()
    val matrixPathSimplifier = MatrixPathSimplifier(BresenhamPathCreator(), MatrixPathValidator())
    val generationService = GenerationService(matrixPathGenerator, matrixPathSimplifier)
    val fitnessFunction = FitnessFunction()
    val rouletteSelection = RouletteSelection()
    val crossingGenerationService = CrossingGenerationService()
    var generation = generationService.newGeneration(matrix, 6)

    for (i in 1..100) {
        println("#$i iteration")
        val cross = crossingGenerationService.cross(generation)

        val nextSize = cross.size - 1
        if (nextSize == 1) {
            generation = cross
            break
        }

        generation = rouletteSelection.select(cross, nextSize)
    }

    rouletteSelection.select(generation, 1)
        .first()
        .let {
            it.showPath()
            matrix.showMatrixWithPath(it)
            println(fitnessFunction.evaluateFitness(it))
        }
}
