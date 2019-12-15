package service

import service.ListUtils.last
import kotlin.random.Random

data class Item<T>(val t: T, val probability: Double)

class ProbabilityCollection<T>(private val list: List<Item<T>>) {

    private val sumList: List<Item<T>>

    init {
        var sum = 0.0
        val temp: MutableList<Item<T>> = ArrayList()
        list.forEach {
            sum += it.probability
            temp.add(Item(it.t, sum))
            Unit
        }

        sumList = temp.toList()
    }

    fun nextRandom(): Item<T> =
        sumList.binarySearchBy(key = Random.nextDouble(), selector = { it.probability })
            .let {
                if (-it >= sumList.size) last(sumList) else sumList.get(-it)
            }
}
