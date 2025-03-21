import org.example.Edge
import org.example.dijkstra
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class DijkstraTests {
    companion object {
        @JvmStatic
        fun provideGraphs(): List<Triple<Map<Int, List<Edge>>, Int, Map<Int, Int>>> {
            return listOf(
                // Обычный граф
                Triple(
                    mapOf(
                        0 to listOf(Edge(1, 4), Edge(2, 1)),
                        1 to listOf(Edge(3, 1)),
                        2 to listOf(Edge(1, 2), Edge(3, 5)),
                        3 to listOf()
                    ),
                    0,
                    mapOf(0 to 0, 1 to 3, 2 to 1, 3 to 4)
                ),

                // Несвязанный граф
                Triple(
                    mapOf(
                        0 to listOf(Edge(1, 2)),
                        1 to listOf(Edge(2, 3)),
                        2 to listOf(),
                        3 to listOf(Edge(4, 1)),
                        4 to listOf()
                    ),
                    0,
                    mapOf(0 to 0, 1 to 2, 2 to 5)
                ),

                // Граф с одной вершиной
                Triple(
                    mapOf(0 to listOf()),
                    0,
                    mapOf(0 to 0)
                )
            )
        }
    }

    @ParameterizedTest
    @MethodSource("provideGraphs")
    fun testDijkstra(graphData: Triple<Map<Int, List<Edge>>, Int, Map<Int, Int>>) {
        val (graph, start, expected) = graphData
        val result = dijkstra(graph, start)
        assertEquals(expected, result)
    }

    @Test
    fun testNegativeWeights() {
        val graph = mapOf(
            0 to listOf(Edge(1, -1)),
            1 to listOf(Edge(2, 2)),
            2 to listOf(Edge(3, 3)),
            3 to listOf()
        )

        assertThrows(IllegalArgumentException::class.java) {
            dijkstra(graph, 0)
        }
    }
}