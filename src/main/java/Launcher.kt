import model.Matrix
import service.BresenhamPathCreator
import service.FitnessFunction
import service.GenerationService
import service.MatrixPathGenerator
import service.MatrixPathSimplifier
import service.MatrixPathValidator
import service.RouletteSelection

object Launcher {
}

fun main() {
    val matrix = Matrix(10)
    matrix.showMatrix()
    val matrixPathGenerator = MatrixPathGenerator()
    val matrixPathSimplifier = MatrixPathSimplifier(BresenhamPathCreator(), MatrixPathValidator())
    val generationService = GenerationService(matrixPathGenerator, matrixPathSimplifier)
    val fitnessFunction = FitnessFunction()
    val rouletteSelection = RouletteSelection()

    val generation = generationService.newGeneration(matrix, 10)

    rouletteSelection.select(generation, 3)
        .forEach {
            it.showPath()
            matrix.showMatrixWithPath(it)
            println(fitnessFunction.evaluateFitness(it))
        }

}
