package org.example

import java.util.PriorityQueue

data class Edge(val to: Int, val weight: Int)

data class Node(val vertex: Int, val distance: Int) : Comparable<Node> {
    override fun compareTo(other: Node): Int = distance - other.distance
}


fun dijkstra(graph: Map<Int, List<Edge>>, start: Int): Map<Int, Int> {
    val distances = mutableMapOf<Int, Int>().withDefault { Int.MAX_VALUE }
    val priorityQueue = PriorityQueue<Node>()

    distances[start] = 0
    priorityQueue.add(Node(start, 0))

    while (priorityQueue.isNotEmpty()) {
        val (currentVertex, currentDistance) = priorityQueue.poll()

        if (currentDistance > distances.getValue(currentVertex)) continue

        for (edge in graph.getOrDefault(currentVertex, emptyList())) {
            if (edge.weight < 0) {
                throw IllegalArgumentException("Граф содержит отрицательные веса рёбер, Дейкстра не поддерживает их.")
            }

            val newDistance = currentDistance + edge.weight
            if (newDistance < distances.getValue(edge.to)) {
                distances[edge.to] = newDistance
                priorityQueue.add(Node(edge.to, newDistance))
            }
        }
    }

    return distances
}