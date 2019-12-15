package service

import model.MatrixPath

class RouletteSelection {

    private val fitnessFunction = FitnessFunction()

    fun select(matrixPaths: Collection<MatrixPath>, n: Int): Set<MatrixPath> {
        val fitness = matrixPaths.map { fitnessFunction.evaluateFitness(it) }
        val sum = fitness.sum()
        val roulette = matrixPaths
            .zip(fitness)
            .map { Item(it.first, it.second / sum) }
            .sortedBy { it.probability }

        val probabilityService = ProbabilityCollection(roulette)
        val res: MutableSet<MatrixPath> = HashSet()
        while (res.size < n) {
            val next = probabilityService.nextRandom()
            res.add(next.t)
        }

        return res
    }
}
