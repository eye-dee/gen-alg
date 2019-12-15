import model.Matrix
import service.BresenhamPathCreator
import service.CrossingGenerationService
import service.FitnessFunction
import service.GenerationService
import service.MatrixPathGenerator
import service.MatrixPathSimplifier
import service.MatrixPathValidator
import service.MutationGenerationService
import service.RouletteSelection

object Launcher {
}

fun main() {
    val GENERATION_SIZE = 14

    val matrix = Matrix(20)
    matrix.showMatrix()
    val matrixPathGenerator = MatrixPathGenerator()
    val matrixPathSimplifier = MatrixPathSimplifier(BresenhamPathCreator(), MatrixPathValidator())
    val generationService = GenerationService(matrixPathGenerator, matrixPathSimplifier)
    val fitnessFunction = FitnessFunction()
    val rouletteSelection = RouletteSelection()
    val crossingGenerationService = CrossingGenerationService()
    val mutationGenerationService = MutationGenerationService()
    var generation = generationService.newGeneration(matrix, GENERATION_SIZE)

    for (i in 1..500) {
        println("#$i iteration")

        val toMutateAndCross = rouletteSelection.select(generation, generation.size / 2)

        val mutatedAndCross = crossingGenerationService.cross(
            mutationGenerationService.mutate(toMutateAndCross, matrix)
        )

        generation = generation
            .union(mutatedAndCross)

        generation = rouletteSelection.select(generation, GENERATION_SIZE)
        generation.map { fitnessFunction.evaluateFitness(it) }
            .max()
            ?.also { println("best fitness: $it") }
    }

    generation.maxBy { fitnessFunction.evaluateFitness(it) }
        ?.let {
            it.showPath()
            matrix.showMatrixWithPath(it)
            println(fitnessFunction.evaluateFitness(it))
        }
}
